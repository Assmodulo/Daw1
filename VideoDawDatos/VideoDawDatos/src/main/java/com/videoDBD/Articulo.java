package com.videoDBD;

import java.time.LocalDate;

public class Articulo {

    private String cod_articulo;
    private String tipo;
    private String nombre;
    private String genero;
    private LocalDate f_alta;
    private LocalDate f_baja;
    private boolean alquilada;

    public static int cantidadTotalArticulos = 1;

    public Articulo(String cod_articulo, String tipo, String nombre, String genero) {
        this.cod_articulo = cod_articulo;
        this.tipo = tipo;
        this.nombre = nombre;
        this.genero = genero;
        this.f_alta = LocalDate.now();
        this.f_baja = null;
        this.alquilada = false;
    }

    public Articulo(String cod_articulo, String tipo, String nombre, String genero, LocalDate f_alta, boolean alquilada) {
        this.cod_articulo = cod_articulo;
        this.tipo = tipo;
        this.nombre = nombre;
        this.genero = genero;
        this.f_alta = f_alta;
        this.f_baja = null;
        this.alquilada = alquilada;
    }

    public Articulo(String cod_articulo, String tipo, String nombre, String genero, LocalDate f_alta, LocalDate f_baja, boolean alquilada) {
        this.cod_articulo = cod_articulo;
        this.tipo = tipo;
        this.nombre = nombre;
        this.genero = genero;
        this.f_alta = f_alta;
        this.f_baja = f_baja;
        this.alquilada = alquilada;
    }

    public String getCod_articulo() {
        return cod_articulo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public LocalDate getF_alta() {
        return f_alta;
    }

    public LocalDate getF_baja() {
        return f_baja;
    }

    public boolean isAlquilada() {
        return alquilada;
    }

    @Override
    public String toString() {

        String f_baja = "";

        if(this.getF_baja() != null){
            f_baja = MyUtils.formatearFecha(this.f_baja);
        }else{
            f_baja = "Activo";
        }

        return String.format("%-8S | %-10S | %-30S | %-25S | %-10S | %-10S | %-6S",
                this.cod_articulo, this.tipo, this.nombre, this.genero, MyUtils.formatearFecha(this.f_alta),
                f_baja, this.alquilada);
    }
}
