import java.util.LinkedList;

public class Agenda {
    private LinkedList<Contacto> contactos;

    public Agenda() {
        this.contactos = new LinkedList<>();
    }

    public boolean agregarContacto(Contacto contacto) {
        boolean add = false;
        contactos.add(contacto);
        return add;
    }
}
