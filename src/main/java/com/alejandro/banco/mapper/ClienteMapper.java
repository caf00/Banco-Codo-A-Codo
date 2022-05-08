package com.alejandro.banco.mapper;

import com.alejandro.banco.dto.ClienteDto;
import com.alejandro.banco.entity.Cliente;

public class ClienteMapper {

    public Cliente dtoToClient(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        if (clienteDto.getId() != null) cliente.setDni(clienteDto.getId());
        cliente.setDni(clienteDto.getDni());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setSaldo(clienteDto.getSaldo());
        return cliente;
    }

    public ClienteDto clientToDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setDni(cliente.getDni());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setSaldo(cliente.getSaldo());
        return clienteDto;
    }
}

