import java.util.Scanner;

public class Agenda {

    static Contacto [] agenda = new Contacto[10];
    static int contadorContactos = 0;

    public static void main(String[] args) {
        Scanner teclado;
        String opcion;

        System.out.println("AGENDA TELEFÓNICA");

        do{
            System.out.println("SELECCIONE LO QUE QUIERE REALIZAR\n" +
                    "1.-AÑADIR CONTACTO\n2.-ELIMINAR CONTACTO\n3.-BUSCAR CONTACTO\n4.-LISTAR CONTACTOS\n5.-OBTENER POSICION" +
                    "CONTACTO\n6.-SALIR");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();
            switch(opcion){
                case "1":

                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    System.out.println("HASTA LUEGO");
                    break;
                default:
                    System.out.println("OPCION INVALIDA");
                    break;
            }

        }while(!opcion.equals("6"));
    }

    public static boolean addContacto(Contacto contacto) {
        boolean add = false;
        boolean encontrado = false;

        if (contadorContactos < agenda.length) {
            for (int i = 0; i < contadorContactos; i++) {
                if (agenda[i].getNombre().equals(contacto.getNombre())) {
                    encontrado = true;
                }
            }
            if(!encontrado) {
                agenda[contadorContactos] = contacto;
                contadorContactos++;
                add = true;
            }
        }
        return add;
    }

    public static boolean eliminarContacto(String nombre) {
        boolean eliminado = false;
        if (contadorContactos > 0) {
            for (int i = 0; i < contadorContactos; i++) {
                if (agenda[i].getNombre().equals(nombre)) {
                    agenda[i] = null;
                    contadorContactos--;
                    eliminado = true;
                }
            }
        }
        return eliminado;
    }

    public static boolean existeContacto(String nombre) {
        boolean existe = false;
        if (contadorContactos > 0) {
            for (int i = 0; i < contadorContactos; i++) {
                if (agenda[i].getNombre().equals(nombre)) {
                    existe = true;
                }
            }
        }
        return existe;
    }

    //Para el metodo de listar contactos yo prefiero un metodo String antes que un void
    public static String listarContactos(){
        String lista = "";
        for (int i = 0; i < contadorContactos; i++) {
            lista += (i+1) + agenda[i].getNombre() + " " + agenda[i].getTelefono() + "\n";
        }
        return lista;
    }

    public static int posicionContacto(String nombre) {
        int posicion = -1;
        for (int i = 0; i < contadorContactos; i++) {
            if (agenda[i].getNombre().equals(nombre)) {
                posicion = i;
            }
        }
        return posicion;
    }

    public static String insertarDato(String dato){
        Scanner sc = new Scanner(System.in);
        String introducido;

        System.out.println("INDIQUE EL NOMBRE DEL CONTACTO");
        introducido = sc.nextLine();
        return introducido;
        }
    
}