package org.example.clasesparking;

import java.time.LocalDate;

public class Vehiculos {

    String matricula;
    String marca;
    String modelo;
    String color;
    LocalDate añoDeFabricacion;
    String estado;
    LocalDate fechaDeAdquisicion;

    public Vehiculos(String matricula, String marca, String modelo, String color, LocalDate añoDeFabricacion, String estado, LocalDate fechaDeAdquisicion) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.añoDeFabricacion = añoDeFabricacion;
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

    public LocalDate getAñoDeFabricacion() {
        return añoDeFabricacion;
    }

    public void setAñoDeFabricacion(LocalDate añoDeFabricacion) {
        this.añoDeFabricacion = añoDeFabricacion;
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

    @Override
    public String toString() {
        return "Vehiculos{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", añoDeFabricacion=" + añoDeFabricacion +
                ", estado='" + estado + '\'' +
                ", fechaDeAdquisicion=" + fechaDeAdquisicion +
                '}';
    }
}
