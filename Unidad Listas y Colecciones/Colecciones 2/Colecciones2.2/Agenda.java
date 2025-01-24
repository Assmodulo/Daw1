import java.util.LinkedList;

public class Agenda {

    //Declaro las variables de la clase agenda
    private LinkedList<Contacto> contactos;
    private int contadorContactos;


    //Constructor de la clase agenda, no necesita parámetros
    public Agenda() {
        this.contactos = new LinkedList<>();
        this.contadorContactos = 0;
    }

    //Método que devuelve la cantidad de elementos que tiene el objeto agenda correspondiente
    public int getContadorContactos() {
        return contadorContactos;
    }

    //Método boolean que sirve para añadir un contacto a la agenda y actualizar el número de contactos disponibles en la agenda
    public boolean agregarContacto() {
        boolean add = false;
        String nombre, telefono, email;

        //Voy llamando a los diferentes métodos para insertar los datos y luego crear una instancia de la clase Contacto
        nombre = MyUtils.insertarNombre();
        //Compruebo que no existe ya un contacto con ese nombre
        if (!contactoExistente(nombre)) {
            telefono = MyUtils.insertarTelefono();
            email = MyUtils.insertarEmail();
            contactos.add(new Contacto(nombre, telefono, email));
            this.contadorContactos++;
            add = true;
        }
        return add;
    }

    //Este método simplemente nos indica si un nombre ha sido encontrado en la agenda devolviendo true o false según el resultado
    public boolean contactoExistente(String nombre){
        boolean existe = false;
        for(Contacto contacto: contactos){
            if(contacto.getNombre().equals(nombre)){
                existe = true;
            }
        }
        return existe;
    }

    //Método que nos devuelve un objeto de la clase contacto en caso de encontrarlo en nuestra lista dado un nombre
    //que recibe por parámetro
    public Contacto buscarContacto(String nombre) {
        Contacto c = null;
            for(Contacto contacto: contactos) {
                if(contacto.getNombre().equals(nombre)) {
                    c = contacto;
                }
            }
        return c;
    }

    //Método que dado un nombre que se recibe por parámetro nos elimina un contacto de la agenda
    public void eliminarContacto(String nombre){
        //Evaluo primero que el contacto existe, si existe lo elimino
        Contacto contacto = buscarContacto(nombre);
        if(contacto != null) {
            this.contactos.remove(contacto);
            this.contadorContactos--;
        }
    }


    //Método que devuelve un String cuyo valor es un listado de los contactos de la agenda
    public String visualizarAgenda(){
        String agenda = "";
        for(Contacto contacto: contactos) {
            agenda += contacto.toString() + "\n";
        }
        return agenda;
    }
}
