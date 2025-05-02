package com.videodbd.videodawinterfaz;

public class Videoclub {

    private String cif;
    private String nombre;
    private String direccion;
    private String telefono;


    public Videoclub(String codigo, String nombre, String direccion, String telefono){
        this.cif = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Videoclub(){}

    public String getCif() {
        return cif;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }


    @Override
    public String toString() {
        return String.format("%-9S | %-50S | %-50S | %-9S",this.cif, this.nombre, this.direccion, this.telefono);
    }
}

