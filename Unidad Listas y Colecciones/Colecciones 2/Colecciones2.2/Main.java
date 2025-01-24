import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Defino una variable de la clase agenda y una variable de la clase Scanner
        Agenda agenda = new Agenda();
        Scanner teclado;

        //Defino dos variables de tipo String que voy a necesitar durante el ejercicio
        String opcion, nombre;

        System.out.println("AGENDA PERSONAL");

        do{
            //Llamo al método que muestra las opciones a elegir
            System.out.println(MyUtils.mostrarOpcionesMenu());
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();

            //Evaluo el valor de opcion y según ese valor llamo a los diferentes métodos de agenda.
            switch(opcion){
                case "1":
                    System.out.println("AÑADIR CONTACTO");

                    if(agenda.agregarContacto()){
                        System.out.println("CONTACTO AGREGADO");
                    }else{
                        System.out.println("CONTACTO NO AGREGADO. EL CONTACTO YA EXISTIA");
                    }
                    System.out.println();
                    break;
                case "2":
                    System.out.println("BUSCAR CONTACTO");
                    nombre = MyUtils.insertarNombre();
                    if(agenda.contactoExistente(nombre)){
                        System.out.println(agenda.buscarContacto(nombre));
                    }else{
                        System.out.println("EL CONTACTO NO EXISTE EN LA AGENDA");
                    }
                    System.out.println();
                    break;
                case "3":
                    System.out.println("ELIMINAR CONTACTO");
                    nombre = MyUtils.insertarNombre();
                    if(agenda.contactoExistente(nombre)){
                        agenda.eliminarContacto(nombre);
                        System.out.println("CONTACTO CON EL NOMBRE DE " + nombre + "ELIMINADO");
                    }else{
                        System.out.println("EL CONTACTO NO EXISTE EN LA AGENDA");
                    }
                    System.out.println();
                    break;
                case "4":
                    System.out.println("VISUALIZAR AGENDA COMPLETA");

                    System.out.println(agenda.visualizarAgenda());
                    System.out.println();
                    break;
                case "5":
                    System.out.println("SU AGENDA TIENE UN TOTAL DE " + agenda.getContadorContactos() + " CONTACTOS");
                    System.out.println();
                    break;
                case "6":
                    System.out.println("SALIENDO DEL PROGRAMA AGENDA");
                    break;
                default:
                    System.out.println("OPCION INCORRECTA");
                    System.out.println();
                    break;
            }
        }while(!opcion.equals("6"));
    }
}
