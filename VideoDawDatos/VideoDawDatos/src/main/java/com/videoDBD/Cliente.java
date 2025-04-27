package com.videoDBD;

import java.time.LocalDate;

public class Cliente {

    private String cod_cliente;
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate f_nacimiento;
    private LocalDate f_alta;
    private LocalDate f_baja;
    private String telefono;

    public static int numeroTotalSociosFranquicia = 1;


    //Constructor inicial, para el momento en el que un cliente se da de alta por primera vez
    public Cliente(String cod_cliente, String dni, String nombre, String apellidos, LocalDate f_nacimiento, String telefono) {
        this.cod_cliente = cod_cliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.f_nacimiento = f_nacimiento;
        this.f_alta = LocalDate.now();
        this.f_baja = null;
        this.telefono = telefono;
    }

    //Constructor para recuperar los datos de clientes dados de alta actualmente en un videoclub
    public Cliente(String cod_cliente, String dni, String nombre, String apellidos, LocalDate f_nacimiento, LocalDate f_alta, String telefono) {
        this.cod_cliente = cod_cliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.f_nacimiento = f_nacimiento;
        this.f_alta = f_alta;
        this.f_baja = null;
        this.telefono = telefono;
    }

    //Constructor que se usa para clientes que se han dado de baja y pasan a la tabla de clientes dados de baja
    public Cliente(String cod_cliente, String dni, String nombre, String apellidos, LocalDate f_nacimiento, LocalDate f_alta, LocalDate f_baja, String telefono) {
        this.cod_cliente = cod_cliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.f_nacimiento = f_nacimiento;
        this.f_alta = f_alta;
        this.f_baja = f_baja;
        this.telefono = telefono;
    }

    public String getCod_cliente() {
        return cod_cliente;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getF_nacimiento() {
        return f_nacimiento;
    }

    public LocalDate getF_alta() {
        return f_alta;
    }

    public LocalDate getF_baja() {
        return f_baja;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        String f_baja = "";

        if(this.getF_baja() != null){
            f_baja = MyUtils.formatearFecha(this.f_baja);
        }else{
            f_baja = "Activo";
        }

        return String.format("%-8S | %-10S | %-25S | %-30S | %-10S | %-10S | %-10S | %-6S",
                this.cod_cliente, this.dni, this.nombre, this.apellidos, MyUtils.formatearFecha(this.f_nacimiento),
                MyUtils.formatearFecha(this.f_alta),f_baja, this.telefono);
    }
}
