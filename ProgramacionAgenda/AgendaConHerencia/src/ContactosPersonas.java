import java.time.LocalDate;

public class ContactosPersonas extends Contacto{
    private String cumpleanios;

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


    @Override
    public String toString() {
        return super.toString() + ", CUMPLEAÑOS: " + this.cumpleanios;
    }
}
