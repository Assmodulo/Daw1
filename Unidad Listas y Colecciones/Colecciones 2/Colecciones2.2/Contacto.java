public class Contacto implements Comparable<Contacto> {
    //declaramos las variables de la clase
    private String nombre;
    private String telefono;
    private String correo;

    //Constructor de la clase
    public Contacto(String nombre, String telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    //Como solo voy a necesitar el nombre para las comprobaciones solo necesito su getter
    public String getNombre() {
        return nombre;
    }


    @Override
    public String toString() {
        return  "NOMBRE  " + this.nombre +
                ", TELÉFONO  " + this.telefono +
                ", EMAIL " + this.correo;
    }

    //Ya que lo hemos visto, añado el metodo compareTo para ordenar la agenda por el nombre del contacto
    @Override
    public int compareTo(Contacto o) {
        if(this.nombre.compareTo(o.nombre) > 0) {
            return 1;
        }else if(this.nombre.compareTo(o.nombre) < 0) {
            return -1;
        }else{
            return 0;
        }
    }
}
