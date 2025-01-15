import java.util.Scanner; //Importamos Scanner pues es necesario para introducir datos

public class ProgramaAgenda {

    public static void main(String[] args) {
        Scanner teclado; //Defino una variable scanner, no inicializo, dejo eso para cada vez que se ejecute el bucle
        String opcion;//Aquí se almacena la opcion elegida por teclado
        Agenda agenda = new Agenda();//Un objeto de la clase agenda, ya que según el ejercicio sólo debe de haber una, como los inmortales
        String nombre, numero, cumpleanios, email;//Variables del tipo string que creo voy a necesitar
        int posicion;//Una variable de tipo int


        //Dos variable objeto de cada una de las clase, las voy a necesitar para crear instancias
        ContactosPersonas cp;
        ContactosEmpresas ce;

        System.out.println("AGENDA TELEFÓNICA");

        //Menú clásico
        do{
            System.out.println("SELECCIONE LO QUE QUIERE REALIZAR\n" +
                    "1.-AÑADIR CONTACTO\n2.-ELIMINAR CONTACTO\n3.-BUSCAR CONTACTO\n4.-LISTAR CONTACTOS\n5.-OBTENER POSICION" +
                    " CONTACTO\n6.-SALIR");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();

            //Evaluo opción y segun su valor se hace una cosa u otra
            switch(opcion){
                case "1":
                        System.out.println("AÑADIR CONTACTO");
                    if (agenda.getContadorContactos() < agenda.getLongitudArray()) {
                        nombre = insertarDato("NOMBRE");
                        numero = insertarDato("NUMERO");
                        if(elegirTipoContacto() == 1){
                            cumpleanios = insertarDato("CUMPLEAÑOS: FORMATO DD/MM");
                            cp = new ContactosPersonas(nombre, numero, cumpleanios);
                            if (agenda.addContacto(cp)) {
                                System.out.println("CONTACTO AÑADIDO CORRECTAMENTE");
                            }
                        }else{
                            email = insertarDato("EMAIL");
                            ce = new ContactosEmpresas(nombre, numero, email);
                            if (agenda.addContacto(ce)) {
                                System.out.println("CONTACTO AÑADIDO CORRECTAMENTE");
                            }
                        }
                    }else {
                        System.out.println("HAS LLENADO LA AGENDA, DESAZTE DE ALGÚN AMIGO");
                    }
                    break;
                case "2":
                    System.out.println("ELIMINAR CONTACTO");
                    if (agenda.getContadorContactos() > 0) {
                        System.out.println(agenda.listadoSimple());
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

    //Metodo para introducir un dato por teclado, devuelve un String

    public static String insertarDato(String dato){
        Scanner sc = new Scanner(System.in);
        String introducido;

        System.out.println("INDIQUE EL: " + dato);
        introducido = sc.nextLine();
        return introducido;
    }

    //Ahora que se trabaja con herencia, cuando creo un contacto puedo elegir entre dos tipos, devuelve un int
    //según el valor del int será un tipo de contacto u otro
    public static int elegirTipoContacto(){
        Scanner sc = new Scanner(System.in);
        String opcion ="";
        int eleccion = -1;

        do{
            System.out.println("ES EL CONTACTO DE UNA EMPRESA O DE UN PARTICULA?: E/P");
            opcion = sc.nextLine().toUpperCase();
        }while(!opcion.equals("E") && !opcion.equals("P"));
        if(opcion.equals("E")){
            eleccion = 0;
        }else if(opcion.equals("P")){
            eleccion = 1;
        }
        return eleccion;
    }

}