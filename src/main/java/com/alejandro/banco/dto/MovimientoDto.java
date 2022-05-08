package com.alejandro.banco.dto;

public class MovimientoDto {
    private Long dni;
    private String nombre;
    private Double saldo;
    private Double monto;
    private String tipo;

    public MovimientoDto(Long dni, String nombre, Double saldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.saldo = saldo;
    }
    public Long getDni() {
        return dni;
    }
    public String getNombre() {
        return nombre;
    }
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public Double getMonto() {
        return monto;
    }
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "MovimientoDto{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                ", monto=" + monto +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
