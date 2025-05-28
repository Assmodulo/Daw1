package org.example.vetdawexam;

import java.io.Serializable;
import java.time.LocalDate;

public class Mascota implements Serializable {

    private static final long serialVersionUID = 7018612903437133648L;
    private String pasaporte;
    private String nombre;
    private double peso;
    private LocalDate fechaNacimiento;
    private String propietarioDni;
    private int tipoMascota;



    public Mascota(String pasaporte, String nombre, double peso, LocalDate fechaNacimiento, String propietarioDni, int tipoMascota) {
        this.pasaporte = pasaporte;
        this.nombre = nombre;
        this.peso = peso;
        this.fechaNacimiento = fechaNacimiento;
        this.propietarioDni = propietarioDni;
        this.tipoMascota = tipoMascota;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getPropietarioDni() {
        return propietarioDni;
    }

    public int getTipoMascota() {
        return tipoMascota;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "pasaporte='" + pasaporte + '\'' +
                ", nombre='" + nombre + '\'' +
                ", peso=" + peso +
                ", fechaNacimiento=" + fechaNacimiento +
                ", propietarioDni='" + propietarioDni + '\'' +
                ", tipoMascota=" + tipoMascota +
                '}';
    }
}
