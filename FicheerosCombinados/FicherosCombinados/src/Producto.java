public class Producto {

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

    /*
        Según lo que yo entiendo, necesito crear más constructores, un tipo para cada tipo de fichero
        Un constructor que sea para los datos que tengo en el archivo CSV y otro para los datos que tengo
        en el archivo DAT
     */

    //Constructor para los datos de un archivo CSV
    public Producto(String referencia, String nombre, String descripcion, String tipo){
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    //Constructor para los datos de un archivo DAT
    public Producto(int cantidad, double precio, int descuento, int iva, boolean aplicarDto) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.iva = iva;
        this.aplicarDto = aplicarDto;
    }



    //Este seria el constructor para crear los objetos completos después de combinar los datos existentes
    //en los dos ficheros

    public Producto(String referencia, String nombre, String descripcion, String tipo, int cantidad, double precio,
                    int descuento, int iva, boolean aplicarDto) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.iva = iva;
        this.aplicarDto = aplicarDto;
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
        return "Producto " +
                ",referencia " + referencia + '\'' +
                ", nombre " + nombre + '\'' +
                ", descripcion " + descripcion + '\'' +
                ", tipo " + tipo + '\'' +
                ", cantidad " + cantidad +
                ", precio " + precio +
                ", descuento " + descuento +
                ", iva " + iva +
                ", aplicarDto " + aplicarDto;
    }
}
