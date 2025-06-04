package org.example.clasesparking;

import java.time.LocalDate;

public class Reservas {

    LocalDate fechaInicio;
    LocalDate fechaFin;
    String estado;
    Double costeTotal;

    public Reservas(LocalDate fechaInicio, LocalDate fechaFin, String estado, Double costeTotal) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.costeTotal = costeTotal;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getCosteTotal() {
        return costeTotal;
    }

    public void setCosteTotal(Double costeTotal) {
        this.costeTotal = costeTotal;
    }

    @Override
    public String toString() {
        return "Reservas{" +
                "fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado='" + estado + '\'' +
                ", costeTotal=" + costeTotal +
                '}';
    }
}
