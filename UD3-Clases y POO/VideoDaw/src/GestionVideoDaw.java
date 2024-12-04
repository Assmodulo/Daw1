import java.time.LocalDate;
import java.time.LocalDateTime;
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
                    if(fechaNacim != null){
                        cliente = new Cliente(dni, nombre, direccion, fechaNacim,videoclub.getClientesTotales());
                        videoclub.guardarDatosCliente(cliente);
                    }else{
                        System.out.println("NO SE PUEDE DAR DE ALTA EL CLIENTE PUES ES MENOR DE EDAD");
                    }

                    break;
                case "4":
                    System.out.println("ESTE ES EL LISTADO DE CLIENTES");
                    String listadoClientes = videoclub.clientesDadosAlta();
                    System.out.println(listadoClientes);
                    String socioSeleccionado;
                    cliente = null;
                    do {
                        System.out.println("SELECCIONE EL SOCIO QUE VA A ALQUILAR INDICANDO SU CÓDIGO NUMÉRICO, SIN LETRA");
                        socioSeleccionado = teclado.nextLine();
                        if (MyUtils.validarEleccionSocio(socioSeleccionado)) {
                            cliente = videoclub.devolverCliente("C" + socioSeleccionado);
                            if(cliente == null){
                                System.out.println("NO EXISTE EL CLIENTE");
                            }
                        } else {
                            System.out.println("FORMATO DE CÓDIGO INCORRECTO");
                        }
                    }while(cliente == null);
                    System.out.println(cliente.mostrarDatosCliente());
                    System.out.println("ESTAS SON LAS PELÍCULAS DISPONIBLES EN ESTE MOMENTO");
                    System.out.println(videoclub.peliculasAlquiler());
                    String peliculaSeleccionada;
                    pelicula = null;
                    do {
                        System.out.println("SELECCIONE LA PELICULA QUE VA A ALQUILAR INDICANDO SU CÓDIGO NUMÉRICO, SIN LETRA");
                        peliculaSeleccionada = teclado.nextLine();
                        if (MyUtils.validarEleccionPelicula(peliculaSeleccionada)) {
                            pelicula = videoclub.devolverPelicula("P" + peliculaSeleccionada);
                            if(pelicula == null){
                                System.out.println("NO EXISTE EL CODIGO DE PELICULA");
                            }
                        } else {
                            System.out.println("FORMATO DE CÓDIGO INCORRECTO");
                        }
                    }while(pelicula == null);
                    System.out.println(pelicula.mostrarInforPelicula());
                    videoclub.generarAlquiler(cliente, pelicula);
                    System.out.println("SE HA REALIZADO EL ALQUILER DE UNA PELÍCULA");
                    break;
                case "5":
                    System.out.println("DEVOLUCIÓN DE PELÍCULAS");
                    System.out.println(videoclub.listadoPeliculasAlquiladas());
                    pelicula = null;
                    peliculaSeleccionada = null;
                    if(videoclub.listadoPeliculasAlquiladas().isEmpty()){
                        System.out.println("EN ESTE MOMENTO NO HAY NINGUNA PELÍCULA ALQUILADA");
                    }else{
                        do {
                            System.out.println("SELECCIONE LA PELICULA QUE VA A ALQUILAR INDICANDO SU CÓDIGO NUMÉRICO, SIN LETRA");
                            peliculaSeleccionada = teclado.nextLine();
                            if (MyUtils.validarEleccionPelicula(peliculaSeleccionada)) {
                                pelicula = videoclub.devolverPelicula("P" + peliculaSeleccionada);
                                if(pelicula == null){
                                    System.out.println("NO EXISTE EL CODIGO DE PELICULA");
                                }
                            } else {
                                System.out.println("FORMATO DE CÓDIGO INCORRECTO");
                            }
                        }while(pelicula == null);
                        if(MyUtils.validarEleccionPelicula(pelicula.getFechaAlquiler())){
                            System.out.println("SE HA EXCEDIDO DEL PERIODO DE 48 HORAS");
                        }
                        videoclub.devolverPeliculaAlquilada(pelicula);
                    }
                    break;
                case "6":
                    System.out.println("ESTE ES EL LISTADO DE CLIENTES DADOS DE ALTA AHORA MISMO");
                    String listadoClientesAlta = videoclub.clientesDadosAlta();
                    System.out.println(listadoClientesAlta);
                    String socioSeleccionado2;
                    cliente = null;
                    if (!listadoClientesAlta.isEmpty()) {
                        do {
                            System.out.println("SELECCIONE EL SOCIO QUE VA A DAR DE BAJA, INTRODUCIENDO SU NUMERO DE CODIGO");
                            socioSeleccionado2 = teclado.nextLine();
                            if (MyUtils.validarEleccionSocio(socioSeleccionado2)) {
                                cliente = videoclub.devolverCliente("C" + socioSeleccionado2);
                                if(cliente == null){
                                    System.out.println("NO EXISTE EL CLIENTE");
                                }
                            } else {
                                System.out.println("FORMATO DE CÓDIGO INCORRECTO");
                            }
                        }while(cliente == null);
                        System.out.println(cliente.mostrarDatosCliente());
                        cliente.setFechaBaja();
                    }else{
                        System.out.println("NO SE TIENEN CLIENTES DADOS DE ALTA");
                    }
                    break;
                case "7":
                    System.out.println("ESTE ES EL LISTADO DE PELICULAS REGISTRADAS AHORA MISMO");
                    String listadoPeliculasRegistradas = videoclub.peliculasAlquiler();
                    System.out.println(listadoPeliculasRegistradas);
                    String peliculaSeleccionada2;
                    pelicula = null;
                    if (!listadoPeliculasRegistradas.isEmpty()) {
                        do {
                            System.out.println("SELECCIONE LA PELICULA A DAR DE BAJA, INTRODUCIENDO SU NUMERO DE CODIGO");
                            peliculaSeleccionada2 = teclado.nextLine();
                            if (MyUtils.validarEleccionPelicula(peliculaSeleccionada2)) {
                                pelicula = videoclub.devolverPelicula("P" + peliculaSeleccionada2);
                                if(pelicula == null){
                                    System.out.println("NO EXISTE LA PELICULA");
                                }
                            } else {
                                System.out.println("FORMATO DE CÓDIGO INCORRECTO");
                            }
                        }while(pelicula == null);
                        System.out.println(pelicula.mostrarInforPelicula());
                        pelicula.setFechaBaja();
                    }else {
                        System.out.println("NO SE TIENEN CLIENTES DADOS DE ALTA");
                    }
                    break;
                case "8":
                    System.out.println("HASTA LA PROXIMA");
                    break;
                default:
                    break;
            }
        } while (!opcion.equals("8"));

    }
}