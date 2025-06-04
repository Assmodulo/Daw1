package org.example.tucochealquileres;

import java.time.LocalDate;

public class Vehiculos {
    int vehiculoId;
    String matricula;
    String marca;
    String modelo;
    String color;
    String estado;
    LocalDate fechaDeAdquisicion;
    int tipoId;
    int parkingId;

    //constructor con los id para poder almacenarlos en java
    public Vehiculos(int vehiculoId, String matricula, String marca, String modelo, String color, int tipoId, String estado, LocalDate fechaDeAdquisicion, int parkingId) {
        this.vehiculoId = vehiculoId;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipoId = tipoId;
        this.estado = estado;
        this.fechaDeAdquisicion = fechaDeAdquisicion;
        this.parkingId = parkingId;
    }

    //constructor sin los id para hacer el insert porque son autoincrementales
    public Vehiculos(String matricula, String marca, String modelo, String color, String estado, LocalDate fechaDeAdquisicion) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.estado = estado;
        this.fechaDeAdquisicion = fechaDeAdquisicion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaDeAdquisicion() {
        return fechaDeAdquisicion;
    }

    public void setFechaDeAdquisicion(LocalDate fechaDeAdquisicion) {
        this.fechaDeAdquisicion = fechaDeAdquisicion;
    }

    //getter de parkingId
    public int getParkingId() {
        return parkingId;
    }
    //no hay setter de parkingId debido a que es autoincremental en la base de datos

    //getter de tipoId
    public int getTipoId() {
        return tipoId;
    }
    //no hay setter de tipoId debido a que es autoincremental en la base de datos

    //getter de vehiculoId
    public int getVehiculoId() {
        return vehiculoId;
    }
    //no hay setter de vehiculoId debido a que es autoincremental en la base de datos


    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }
}
