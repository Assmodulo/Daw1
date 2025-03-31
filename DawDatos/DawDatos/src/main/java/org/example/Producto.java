package org.example;

import java.io.Serializable;

public class Producto implements Serializable {


    private static final long serialVersionUID = 7725742635403537803L;
    private int id;
    private String referencia;
    private String nombre;
    private String descripcion;
    private int idTipo;
    private int cantidad;
    private double precio;
    private int descuento;
    private int iva;
    private boolean aplicarDescuento;


    public Producto(int id, String referencia, String nombre, String descripcion, int idTipo, int cantidad,
                    double precio, int descuento, int iva) {
        this.id = id;
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idTipo = idTipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.iva = iva;
    }


    //Defino los setters que creo que podrían llegar a ser necesarios

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public void setAplicarDescuento(boolean aplicarDescuento) {
        this.aplicarDescuento = aplicarDescuento;
    }

    //Defino los getters que se podrían necesitar, en este caso  podrían ser necesarios todos

    public int getId() {
        return id;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDescuento() {
        return descuento;
    }

    public int getIva() {
        return iva;
    }

    public boolean isAplicarDescuento() {
        return aplicarDescuento;
    }


    //Definimos el método toString


    @Override
    public String toString() {
        return "Producto:" +
                "ID: " + id + "\n" + ", REFERENCIA: " + referencia + "\n" +
                ", NOMBRE: " + nombre + ", REFERENCIA:: " + descripcion + "\n" +
                ", TIPO: " + idTipo + ", CANTIDAD: " + cantidad + "\n" +
                ", PRECIO: " + precio +", DESCUENTO: " + descuento + "\n" +
                ", IVA: " + iva + ", DTO APLICABLE?:" + aplicarDescuento ;
    }
}
