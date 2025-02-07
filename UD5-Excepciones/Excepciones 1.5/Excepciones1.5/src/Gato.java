public class Gato {
    private String nombre;
    private int edad;

    public Gato() {}

    public Gato(String nombre, int edad) throws LongitudNombreException, EdadException{
            setNombre(nombre);
            setEdad(edad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws LongitudNombreException {
        if (nombre.length() > 2) {
            this.nombre = nombre;
        } else {
            throw new LongitudNombreException(this.nombre);
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) throws EdadException {
        if (edad > 0) {
            this.edad = edad;
        } else {
            throw new EdadException(this.edad);
        }
    }

    @Override
    public String toString() {
        return "Gato{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
