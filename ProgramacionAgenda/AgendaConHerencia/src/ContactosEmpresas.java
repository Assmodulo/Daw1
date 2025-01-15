
public class ContactosEmpresas extends Contacto{
    private String email;

    public ContactosEmpresas(String nombre, String telefono, String email) {
        super(nombre, telefono);
        this.email = email;
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
        return super.toString() + ", EMAIL: " + this.email;
    }
}
