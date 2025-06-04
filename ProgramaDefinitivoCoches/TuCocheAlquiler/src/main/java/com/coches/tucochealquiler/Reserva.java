package com.coches.tucochealquiler;

import java.time.LocalDate;

public class Reserva {

        private int id_reserva;
        private int idcliente;
        private int idvehiculo;
        private int idparking;
        private LocalDate inicio;
        private LocalDate fin;
        private double costo;

    public Reserva(int idcliente, int idvehiculo, int idparking, LocalDate inicio, LocalDate fin, double costo) {
        this.idcliente = idcliente;
        this.idvehiculo = idvehiculo;
        this.idparking = idparking;
        this.inicio = inicio;
        this.fin = fin;
        this.costo = costo;
    }

    public Reserva(int id_reserva, int idcliente, int idvehiculo, int idparking, LocalDate inicio, LocalDate fin, double costo) {
        this.id_reserva = id_reserva;
        this.idcliente = idcliente;
        this.idvehiculo = idvehiculo;
        this.idparking = idparking;
        this.inicio = inicio;
        this.fin = fin;
        this.costo = costo;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public int getIdvehiculo() {
        return idvehiculo;
    }

    public int getIdparking() {
        return idparking;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public double getCosto() {
        return costo;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id_reserva=" + id_reserva +
                ", idcliente=" + idcliente +
                ", idvehiculo=" + idvehiculo +
                ", idparking=" + idparking +
                ", inicio=" + inicio +
                ", fin=" + fin +
                ", costo=" + costo +
                '}';
    }
}

