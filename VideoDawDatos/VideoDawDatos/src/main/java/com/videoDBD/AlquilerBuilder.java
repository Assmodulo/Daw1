package com.videoDBD;

import java.time.LocalDateTime;

public class AlquilerBuilder {



    private String codArticulo;
    private String codSocio;
    private LocalDateTime f_alquiler;
    private LocalDateTime f_devolucion;



    public AlquilerBuilder codArticulo(String codArticulo){
        this.codArticulo = codArticulo;
        return this;
    }

    public AlquilerBuilder codSocio(String codSocio){
        this.codSocio = codSocio;
        return this;
    }

    public AlquilerBuilder f_alquiler(LocalDateTime f_alquiler){
        this.f_alquiler = f_alquiler;
        return this;
    }

    public AlquilerBuilder f_devolucion(LocalDateTime f_devolucion){
        this.f_devolucion = f_devolucion;
        return this;
    }

    public Alquiler build(){
        return new Alquiler(this.codArticulo, this.codSocio, this.f_alquiler);
    }

    public Alquiler buildCompleta(){
        return new Alquiler(this.codArticulo, this.codSocio, this.f_alquiler, this.f_devolucion);
    }
}
