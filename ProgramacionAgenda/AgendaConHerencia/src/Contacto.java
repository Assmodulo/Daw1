public abstract class Contacto {
    //Definimos los atributos comunes a todas las clases hijas
    private String nombre;
    private String telefono;

    //Definimos el constructor de la clase padre
    public Contacto(String nombre, String telefono) {
        this.nombre = nombre.toUpperCase();
        this.telefono = telefono;
    }

    /*Definimos los getters, los setters no los necesitamos*/
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    /*Método toString de la clase padre*/
    @Override
    public String toString() {
        return "CONTACTO:" +
                "NOMBRE: " + nombre +
                ", TELEFONO: " + telefono;
    }
}
