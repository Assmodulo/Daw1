public class Producto implements Comparable<Producto> {

    /*De forma rápida creo los atributos de la clase, el constructor, getters y setters además del método toString*/
    private String nombre;
    private int cantidad;

    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public int compareTo(Producto producto) {
        if(this.nombre.compareTo(producto.nombre) > 0) {
            return 1;
        }else if(this.nombre.compareTo(producto.nombre) < 0) {
            return -1;
        }else{
            return 0;
        }

    }
}
