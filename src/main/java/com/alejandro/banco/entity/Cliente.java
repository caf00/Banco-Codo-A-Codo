package com.alejandro.banco.entity;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private Long dni;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Double saldo;
    public Cliente(Long id, Long dni, String nombre) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.saldo = 0.0;
    }
    public Cliente() {
        this.saldo = 0.0;
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

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void depositar(Double saldo) {
        this.saldo+= saldo;
    }

    public void extraer(Double saldo) {
        this.saldo-= saldo;
    }
}
