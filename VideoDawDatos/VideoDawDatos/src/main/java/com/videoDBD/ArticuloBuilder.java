package com.videoDBD;

import java.time.LocalDate;

public class ArticuloBuilder {

    private String cod_articulo;
    private String tipo;
    private String nombre;
    private String genero;
    private LocalDate f_alta;
    private LocalDate f_baja;
    private boolean alquilada;

    public ArticuloBuilder cod_articulo(String cod_articulo) {
        this.cod_articulo = cod_articulo;
        return this;
    }

    public ArticuloBuilder tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public ArticuloBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ArticuloBuilder genero(String genero) {
        this.genero = genero;
        return this;
    }

    public ArticuloBuilder f_alta(LocalDate f_alta) {
        this.f_alta = f_alta;
        return this;
    }

    public ArticuloBuilder f_baja(LocalDate f_baja) {
        this.f_baja = f_baja;
        return this;
    }

    public ArticuloBuilder alquilada(boolean alquilada) {
        this.alquilada = alquilada;
        return this;
    }

    public Articulo build(){
        return new Articulo(cod_articulo, tipo, nombre, genero);
    }

    public Articulo buildIntermedia(){
        return new Articulo(cod_articulo, tipo, nombre, genero, f_alta, alquilada);
    }

    public Articulo buildCompleta(){
        return new Articulo(cod_articulo, tipo, nombre, genero, f_alta, f_baja, alquilada);
    }
}
