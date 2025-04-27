package com.videoDBD;

public enum GenerosPeliculas {

    AVENTURAS("Aventuras"), ACCION("Accion"), CIENCIAFICCION("Ciencia-Ficción"), DRAMA("Drama"),
    COMEDIA("Comedia"), INFANTIL("Infantil"), ANIMACION("Animación");

    private String generoP;

    private GenerosPeliculas(String generoP){
        this.generoP = generoP;
    }

    public String getGeneroP(){
        return this.generoP;
    }
}
