import java.util.Comparator;

public class Producto{

    //Atributos Objetos Producto
    private String referencia;
    private String nombre;
    private String descripcion;
    private String tipo;
    private int cantidad;
    private double precio;
    private int descuento;
    private int iva;
    private boolean aplicarDto;


    //Este sería mi constructor inicial
    public Producto(String referencia, String nombre, String descripcion, String tipo, int cantidad, double precio,
                    int descuento, int iva) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.iva = iva;
        this.aplicarDto = false;
    }


    //Constructor para los datos de un archivo CSV
    public Producto(String referencia, String nombre, String descripcion, String tipo){
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }


    //Los Getters, en principio podría necesitarlos todos

    public String getReferencia() {
        return referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
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

    public boolean isAplicarDto() {
        return aplicarDto;
    }


    //Ahora voy a poner los Setters, los que yo crea que necesite


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public void setAplicarDto(boolean aplicarDto) {
        this.aplicarDto = aplicarDto;
    }

    //Defino el toString


    @Override
    public String toString() {
        return "PRODUCTO\n " +
                ",REFERENCIA: " + referencia  +
                ", NOMBRE: " + nombre +
                ", DESCRIPCIÓN: " + descripcion +
                ", TIPO: " + tipo + "\n" +
                ", CANTIDAD: " + cantidad +
                ", PRECIO: " + precio + "€" + "\n" +
                ", DESCUENTO: " + descuento + "%" +
                ", IVA: " + iva + "%" +
                ", APLICAR DTO.: " + aplicarDto;
    }
}
