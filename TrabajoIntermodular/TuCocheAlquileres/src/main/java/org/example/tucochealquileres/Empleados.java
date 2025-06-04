package org.example.tucochealquileres;

import java.time.LocalDate;

public class Empleados {
    int empleadoId;
    String nombre;
    String apellido;
    String dni;
    String email;
    String telefono;
    String puesto;
    LocalDate fechaContratacion;
    Boolean activo;
    int parkingId;

    public Empleados(int empleadoId, String nombre, String apellido, String dni, String email, String telefono, String puesto, LocalDate fechaContratacion, Boolean activo, int parkingId) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
        this.activo = activo;
        this.parkingId = parkingId;
    }

    public Empleados(String nombre, String apellido, String dni, String email, String telefono, String puesto, LocalDate fechaContratacion, Boolean activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
        this.activo = activo;
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

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    //getter de empleadoId
    public int getEmpleadoId() {
        return empleadoId;
    }
    //no hay setter de empleadoId porque en la base de datos es autoincremental

    //getter de parkingId
    public int getParkingId() {
        return parkingId;
    }
    //no hay setter de parking id debido a que en la base de datos es autoincremental


}
