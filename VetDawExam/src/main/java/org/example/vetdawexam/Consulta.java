package org.example.vetdawexam;

import java.time.LocalDateTime;

public class Consulta {

    private int idConsulta;
    private LocalDateTime fechaConsulta;
    private int duracion;
    private String observaciones;
    private String mascotaPasaporte;
    private String mascotaPropietarioDni;

    public Consulta(int idConsulta, LocalDateTime fechaConsulta, int duracion, String observaciones, String mascotaPasaporte, String mascotaPropietarioDni) {
        this.idConsulta = idConsulta;
        this.fechaConsulta = fechaConsulta;
        this.duracion = duracion;
        this.observaciones = observaciones;
        this.mascotaPasaporte = mascotaPasaporte;
        this.mascotaPropietarioDni = mascotaPropietarioDni;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getMascotaPasaporte() {
        return mascotaPasaporte;
    }

    public String getMascotaPropietarioDni() {
        return mascotaPropietarioDni;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "idConsulta=" + idConsulta +
                ", fechaConsulta=" + fechaConsulta +
                ", duracion=" + duracion +
                ", observaciones='" + observaciones + '\'' +
                ", mascotaPasaporte='" + mascotaPasaporte + '\'' +
                ", mascotaPropietarioDni='" + mascotaPropietarioDni + '\'' +
                '}';
    }
}
