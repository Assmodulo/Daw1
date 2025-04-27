package com.videoDBD;



public class VideoclubBuilder {

    private String cif;
    private String nombre;
    private String direccion;
    private String telefono;



    public VideoclubBuilder cif(String cif) {
        this.cif = cif;
        return this;
    }

    public VideoclubBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public VideoclubBuilder telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public VideoclubBuilder direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public Videoclub build() {
        return new Videoclub(cif, nombre, direccion, telefono);
    }
}
