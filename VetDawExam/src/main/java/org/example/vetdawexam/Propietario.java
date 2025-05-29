package org.example.vetdawexam;

import java.io.Serializable;

public class Propietario implements Serializable {

    private static final long serialVersionUID = -8153489772847614122L;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String email;



    public Propietario(String dni, String nombre, String apellido, String telefono, String direccion, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("%-10S | %-30S | %-30S | %-12S | %-50S | %-45S ",
                this.dni, this.nombre, this.apellido, this.telefono, this.direccion, this.email);
    }
}
