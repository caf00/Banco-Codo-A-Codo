package com.alejandro.banco.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteDto {
    private Long id;
    @NotNull(message = "El campo dni no puede estar vacío.")
    @Digits(integer = 8, fraction = 0, message = "El campo dni debe tener 8 caracteres.")
    private Long dni;
    @NotBlank(message = "El campo nombre no puede estar vacío.")
    @Size(max = 100, min = 3, message = "El campo nombre debe tener como mínimo 3 caracteres.")
    private String nombre;
    private double saldo;

    public ClienteDto(Long id, Long dni, String nombre, double saldo) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public ClienteDto(Long dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public ClienteDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
