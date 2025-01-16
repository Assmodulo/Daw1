import java.time.LocalDate;

public class ContactosPersonas extends Contacto{
    /*Definimos atributos propios. Podría complicarme para que se tuviese que introducir un dato LocalDate y de ahi
    * obtener el cumpleaños, pero el ejercicio indica que no hay que validar los datos. Supongo que al ser de prueba de
    * herencia no hace falta. Lo mismo pasa con el formato del número de teléfono y el email en la otra clase*/
    private String cumpleanios;

    /*Definimos todos los métodos heredados, incluido el constructor*/
    public ContactosPersonas(String nombre, String telefono, String fecha) {
        super(nombre, telefono);
        this.cumpleanios = fecha;
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public String getTelefono() {
        return super.getTelefono();
    }

    /*Añadimos lo que necesitamos al método toString*/
    @Override
    public String toString() {
        return super.toString() + ", CUMPLEAÑOS: " + this.cumpleanios;
    }
}
