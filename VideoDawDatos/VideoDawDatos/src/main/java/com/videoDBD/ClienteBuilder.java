package com.videoDBD;

import java.time.LocalDate;

public class ClienteBuilder {

    private String cod_cliente;
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate f_nacimiento;
    private LocalDate f_alta;
    private LocalDate f_baja;
    private String telefono;

    public ClienteBuilder cod_cliente(String codigo){
        this.cod_cliente = codigo;
        return this;
    }

    public ClienteBuilder dni(String dni){
        this.dni = dni;
        return this;
    }

    public ClienteBuilder nombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public ClienteBuilder apellidos(String apellidos){
        this.apellidos = apellidos;
        return this;
    }

    public ClienteBuilder f_nacim(LocalDate f_nacimiento){
        this.f_nacimiento = f_nacimiento;
        return this;
    }

    public ClienteBuilder f_alta(LocalDate f_alta){
        this.f_alta = f_alta;
        return this;
    }

    public ClienteBuilder f_baja(LocalDate f_baja){
        this.f_baja = f_baja;
        return this;
    }

    public ClienteBuilder telefono(String telefono){
        this.telefono = telefono;
        return this;
    }

    public Cliente build(){
        return new Cliente(cod_cliente,dni,nombre,apellidos,f_nacimiento,telefono);
    }

    public Cliente buildIntermedia(){
        return new Cliente(cod_cliente, dni, nombre, apellidos, f_nacimiento, f_alta, telefono);
    }

    public Cliente buildCompleto(){
        return new Cliente(cod_cliente, dni, nombre, apellidos, f_nacimiento, f_alta, f_baja, telefono);
    }
}
