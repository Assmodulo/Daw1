public class Gato {
    private String nombre;
    private int edad;

    public Gato(String nombre, int edad){
        try {
            setNombre(nombre);
            setEdad(edad);
        } catch (LongitudNombreException e1) {
            System.out.println(e1.getMessage());
            System.out.println("Se le asignará a su gato un nombre por defecto");
        } catch (EdadException e2) {
            System.out.println(e2.getMessage());
            System.out.println("Se le asignará a su gato una edad por defecto");
        }finally{
            System.out.println("Se han añadido los datos de un gato");
            this.nombre = "Gatete";
            this.edad = 1;
        }
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
