package com.videoDBD;

import java.time.LocalDateTime;

public class Alquiler {


    private String codArticulo;
    private String codSocio;
    private LocalDateTime f_alquiler;
    private LocalDateTime f_devolucion;

    public Alquiler(String codArticulo, String codSocio, LocalDateTime f_alquiler) {

        this.codArticulo = codArticulo;
        this.codSocio = codSocio;
        this.f_alquiler = f_alquiler;
    }

    public Alquiler( String codArticulo, String codSocio, LocalDateTime f_alquiler, LocalDateTime f_devolucion) {
        this.codArticulo = codArticulo;
        this.codSocio = codSocio;
        this.f_alquiler = f_alquiler;
        this.f_devolucion = f_devolucion;
    }


    public String getCodArticulo() {
        return codArticulo;
    }

    public String getCodSocio() {
        return codSocio;
    }

    public LocalDateTime getF_alquiler() {
        return f_alquiler;
    }

    public LocalDateTime getF_devolucion() {
        return f_devolucion;
    }

    @Override
    public String toString() {
        String f_devolucion = "";

        if(this.getF_devolucion() != null){
            f_devolucion = MyUtils.formatearFechaHora(this.f_devolucion);
        }else{
            f_devolucion = "NO DEVULETO";
        }

        return String.format("%-8S | %-8S | %-10S | %-10S |",
                 this.codArticulo, this.codSocio, MyUtils.formatearFechaHora(this.f_alquiler),
                f_devolucion);
    }
}
