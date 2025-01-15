
public class ContactosEmpresas extends Contacto{
    /*Definimos atributo propio de esta clase hija*/
    private String email;

    /*Al definir metodos heredados, definimos todos los de la clase padre, incluido el constructor*/
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

    /*Modificamos el método toString de la clase padre para que muestre también los atributos de la clase hija*/
    @Override
    public String toString() {
        return super.toString() + ", EMAIL: " + this.email;
    }
}
