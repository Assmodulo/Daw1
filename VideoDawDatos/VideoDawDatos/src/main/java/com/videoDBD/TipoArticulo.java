package com.videoDBD;

public enum TipoArticulo {

    PELICULA("Pelicula"), VIDEOJUEGO("Videojuego");
    private String tipo;

    private TipoArticulo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }
}
