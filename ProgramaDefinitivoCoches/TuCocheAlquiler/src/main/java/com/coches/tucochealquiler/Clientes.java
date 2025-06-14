package com.coches.tucochealquiler;

import java.time.LocalDate;

public class Clientes {
    int clienteId;
    String nombre;
    String apellido;
    String dni;
    LocalDate fechaNacimiento;
    String telefono;
    String direccion;
    String ciudad;
    String codigoPostal;
    String email;
    boolean activo;
    boolean permisoValido;


    //constructor que recoge el id del cliente
    public Clientes(int clienteId, String nombre, String apellido, String dni, LocalDate fechaNacimiento, String telefono,
                    String direccion, String ciudad, String codigoPostal, String email, boolean activo, boolean permisoValido) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.email = email;
        this.activo = activo;
        this.permisoValido = permisoValido;
    }
    //constructor para el insert sin el id de cliente porque en la base de datos es autoincremental
    public Clientes(String nombre, String apellido, String dni, LocalDate fechaNacimiento, String email, String telefono, String direccion, String ciudad, String codigoPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }


    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    //getter del id del cliente
    public int getClienteId() {
        return clienteId;
    }
    //no hay setter del id porque al ser autoincremental no lo deberiamos de poder modificar

}
