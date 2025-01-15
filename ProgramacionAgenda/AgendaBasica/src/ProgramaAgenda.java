import java.util.Scanner;

public class ProgramaAgenda {

    public static void main(String[] args) {
        Scanner teclado;
        String opcion;
        Contacto c;
        Agenda agenda = new Agenda();
        String nombre, numero;
        int posicion;
        System.out.println("AGENDA TELEFÓNICA");

        do{
            System.out.println("SELECCIONE LO QUE QUIERE REALIZAR\n" +
                    "1.-AÑADIR CONTACTO\n2.-ELIMINAR CONTACTO\n3.-BUSCAR CONTACTO\n4.-LISTAR CONTACTOS\n5.-OBTENER POSICION" +
                    " CONTACTO\n6.-SALIR");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();
            switch(opcion){
                case "1":
                        System.out.println("AÑADIR CONTACTO");
                    if (agenda.getContadorContactos() < agenda.getLongitudArray()) {
                        nombre = insertarDato("NOMBRE");
                        numero = insertarDato("NUMERO");
                        c = new Contacto(nombre, numero);
                        if(agenda.addContacto(c)) {
                            System.out.println("EL CONTACTO HA SIDO AÑADIDO A LA LISTA");
                        }else{
                            System.out.println("O BIEN EL NOMBRE DE CONTACTO YA EXISTIA O NO HA PODIDO AÑADIRSE POR ALGUNA RAZÓN");
                        }
                    } else {
                        System.out.println("HAS LLENADO LA AGENDA, DESZTE DE ALGÚN AMIGO");
                    }
                    break;
                case "2":
                    System.out.println("ELIMINAR CONTACTO");
                    if (agenda.getContadorContactos() > 0) {
                        nombre = insertarDato("NOMBRE").toUpperCase();
                        if (agenda.eliminarContacto(nombre)) {
                            System.out.println("CONTACTO ELIMINADO CON EXITO");
                        } else {
                            System.out.println("EL CONTACTO NO SE HA PODIDO ELIMINAR");
                        }
                    } else {
                        System.out.println("NO HAY CONTACTOS EN LA AGENDA");
                    }
                    break;
                case "3":
                    if (agenda.getContadorContactos() > 0) {
                        System.out.println("BUSCAR CONTACTO");
                        nombre = insertarDato("NOMBRE").toUpperCase();
                        if(agenda.existeContacto(nombre)){
                            posicion = agenda.posicionContacto(nombre);
                            System.out.println(agenda.devolverDatosPosicion(posicion));
                        }else{
                            System.out.println("NO EXISTE CONTACTO");
                        }
                    } else {
                        System.out.println("NO HAY CONTACTOS EN LA AGENDA");
                    }
                    break;
                case "4":
                    String listado = agenda.listarContactos();
                    if (!listado.isEmpty()) {
                        System.out.println("LISTA DE CONTACTOS\n" + listado);
                    } else {
                        System.out.println("NO HAY CONTACTOS QUE MOSTRAR");
                    }
                    break;
                case "5":
                    System.out.println("POSICION DEL CONTACTO");
                    if (agenda.getContadorContactos() > 0) {
                        posicion = agenda.posicionContacto(insertarDato("NOMBRE").toUpperCase());

                        if (posicion > -1) {
                            System.out.println("LA POSICION DEL CONTACTO ES: " + posicion);
                        }
                    } else {
                        System.out.println("NO HAY CONTACTOS EN LA AGENDA");
                    }
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

    public static String insertarDato(String dato){
        Scanner sc = new Scanner(System.in);
        String introducido;

        System.out.println("INDIQUE EL: " + dato);
        introducido = sc.nextLine();
        return introducido;
        }
    
}