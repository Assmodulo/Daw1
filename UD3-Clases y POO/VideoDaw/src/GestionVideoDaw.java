import java.time.LocalDate;
import java.util.Scanner;

public class GestionVideoDaw {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        //Como lo primero que dice el enunciado es crear un videoclub en la franquicia tengo que tener un array para
        //guardar los diferentes videoclubs

        VideoDaw[] videoclubs = new VideoDaw[10];
        int vClubsTotales = 0;

        String opcion; //Para almacenar opciones del menú

        //Variables de las diferentes clases con el constructor por defecto para tenerlas ya definidas
        VideoDaw videoclub = new VideoDaw();
        Pelicula pelicula = new Pelicula();
        Cliente cliente = new Cliente();

        do {
            System.out.println("ELIIJA SU OPCIÓN\n1.- REGISTRAR VIDEOCLUB\n2.- REGISTRAR PELÍCULA EN VIDEOCLUB\n" +
                    "3.- REGISTRAR CLIENTE EN VIDEOCLUB\n4.- ALQUILAR PELÍCULA\n5.- DEVOLVER PELÍCULA\n" +
                    "6.- DAR DE BAJA CLIENTE\n7.- DAR DE BAJA PELÍCULA\n8.- SALIR");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("REGISTRANDO UN VIDEOCLUB");
                    videoclub = new VideoDaw(MyUtils.obtenerCif(), MyUtils.obtenerDireccion());
                    videoclubs[vClubsTotales] = videoclub;
                    vClubsTotales++;
                    break;
                case "2":
                    String titulo;
                    System.out.println("REGISTRANDO PELÍCULA");
                    System.out.println("TITULO DE LA PELÍCULA");
                    titulo = teclado.nextLine();
                    //Creo el objeto, le paso videoclub.getPeliculasTotales para que se cree así el código de registro
                    //de la película
                    pelicula = new Pelicula(titulo, MyUtils.asignarGenero(), videoclub.getPeliculasTotales());
                    videoclub.guardarDatosPelicula(pelicula);
                    break;
                case "3":
                    System.out.println("REGISTRANDO CLIENTE");
                    String dni, nombre, direccion;
                    LocalDate fechaNacim;
                    System.out.println();
                    //Ahora con este do while, comprobaré el dni con los dni de los clientes ya guardados y cuando
                    //el método vea que ese dni no existe nos dejará proseguir con la introducción de los datos
                    System.out.println("AHORA LE VAMOS A SOLICITAR SU DNI");
                    do{
                        dni = MyUtils.formatoDni();
                    }while(videoclub.comprobarClientes(dni));
                    System.out.println("AHORA NECESITAMOS SU NOMBRE");
                    nombre = teclado.nextLine();
                    System.out.println("AHORA NECESITAMOS SU DIRECCION");
                    direccion = teclado.nextLine();
                    System.out.println("AHORA NECESITAMOS SU FECHA DE NACIMIENTO");
                    fechaNacim = MyUtils.insertarFPorTeclado();
                    cliente = new Cliente(dni, nombre, direccion, fechaNacim,videoclub.getClientesTotales());
                    videoclub.guardarDatosCliente(cliente);
                    break;
                case "4":
                    System.out.println("ESTE ES EL LISTADO DE CLIENTES");
                    String listadoClientes = videoclub.clientesDadosAlta(videoclub);
                    System.out.println(listadoClientes);
                    System.out.println("SELECCIONE EL SOCIO QUE VA A ALQUILAR INDICANDO SU CÓDIGO COMPLETO");
                    String clienteSeleccionado;
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                default:
                    break;
            }
        } while (!opcion.equals("8"));

    }
}