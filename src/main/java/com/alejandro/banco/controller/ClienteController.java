package com.alejandro.banco.controller;

import com.alejandro.banco.dto.ClienteDto;
import com.alejandro.banco.entity.Cliente;
import com.alejandro.banco.mapper.ClienteMapper;
import com.alejandro.banco.repository.ClienteRepository;
import com.alejandro.banco.dto.MovimientoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ClienteController {

    private ClienteRepository clienteRepository;
    private ClienteMapper clienteMapper;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("cliente", new ClienteDto());
        return "registro";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("cliente") ClienteDto clienteDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registro";
        }
        if(clienteRepository.existsByDni(clienteDto.getDni())) {
            bindingResult.rejectValue("dni", "error.dni", "El dni ya existe.");
            return "registro";
        }
        Cliente cliente = clienteMapper.dtoToClient(clienteDto);
        clienteRepository.save(cliente);
        model.addAttribute("condicion", 1);
        model.addAttribute("msj", "Cliente guardado exitosamente");
        return "index";
    }

    @GetMapping("/busqueda")
    public String cliente(@RequestParam(value="dni", required = false) Long dni, Model model) {
        Optional<Cliente> cliente = this.buscarCliente(dni);
        if(!cliente.isPresent()) {
            model.addAttribute("condicion", 0);
            model.addAttribute("msj", "No se encontró el cliente :(");
            return "index";
        }
        ClienteDto clienteDto = clienteMapper.clientToDto(cliente.get());
        model.addAttribute("cliente", clienteDto);
        return "cliente";
    }

    @GetMapping("/movimiento/{dni}")
    public String movimiento(@PathVariable Long dni, Model model) {
        Optional<Cliente> cliente = this.buscarCliente(dni);
        MovimientoDto movimientoDto = new MovimientoDto(
                cliente.get().getDni(),
                cliente.get().getNombre(),
                cliente.get().getSaldo()
        );
        model.addAttribute("movimiento", movimientoDto);
        return "movimiento";
    }

    @PostMapping("/movimiento")
    public String guardarMovimiento(@ModelAttribute("movimiento")
                                                MovimientoDto movimientoDto,
                                                RedirectAttributes attr) {
        Cliente cliente = this.clienteRepository.findByDni(movimientoDto.getDni()).get();
        if("dep".equals(movimientoDto.getTipo())) {
            cliente.depositar(movimientoDto.getMonto());
        }else {
            cliente.extraer(movimientoDto.getMonto());
        }
        clienteRepository.save(cliente);
        attr.addAttribute("dni", cliente.getDni());
        return "redirect:/busqueda";
    }

    @Transactional
    @GetMapping("/baja/{dni}")
    public String baja(@PathVariable Long dni, Model model) {
        clienteRepository.deleteByDni(dni);
        model.addAttribute("condicion", 1);
        model.addAttribute("msj", "Cliente eliminado");
        return "index";
    }

    private Optional<Cliente> buscarCliente(Long dni) {
        return clienteRepository.findByDni(dni);
    }
}
