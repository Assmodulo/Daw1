public class Contacto {
    private String nombre;
    private String telefono;

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre.toUpperCase();
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "CONTACTO:" +
                "NOMBRE: " + nombre +
                ", TELEFONO: " + telefono;
    }
}
