import java.time.LocalDate;

public class GestionVideoDaw {
    public static void main(String[] args) {

        //Como lo primero que dice el enunciado es crear un videoclub en la franquicia tengo que tener un array para
        //guardar los diferentes videoclubs

        VideoDaw[] videoclubs = new VideoDaw[10];
        int vClubsTotales = 0;

        String opcion = "", codigo; //opcion para almacenar opciones del menú, codigo para almacenar datos introducidos

        //Variables de las diferentes clases
        VideoDaw videoclub = new VideoDaw();
        Pelicula pelicula;
        Cliente cliente;

        do {
            //Antes de cualquier cosa tengo que evaluar que haya creado o seleccionado un videoclub
            if(vClubsTotales == 0){
                opcion = "1";
            }else{
                System.out.println(MyUtils.devolverOpcionesMenu());
                opcion = MyUtils.obtenerDatoSolicitado("OPCION DEL MENU");
            }
            switch (opcion) {
                case "1":
                    System.out.println("REGISTRANDO UN VIDEOCLUB");
                    videoclub = new VideoDaw(MyUtils.obtenerCif(), MyUtils.obtenerDatoSolicitado("DIRECCION"));
                    videoclubs[vClubsTotales] = videoclub;
                    vClubsTotales++;
                    break;
                case "2":
                    String titulo, genero;
                    System.out.println("REGISTRANDO PELÍCULA");
                        titulo = MyUtils.obtenerDatoSolicitado("TITULO");
                        do{
                            System.out.println("ESTOS SON LOS GÉNEROS DISPONIBLES\n" + MyUtils.listadoGeneros());

                            opcion = MyUtils.obtenerDatoSolicitado("GENERO");
                        }while(Integer.parseInt(opcion) <= 1 && Integer.parseInt(opcion) >= Generos.values().length);

                        genero = MyUtils.devolverGenero(Integer.parseInt(opcion) - 1);

                        pelicula = new Pelicula(titulo, genero, videoclub.getPeliculasTotales());
                        System.out.println("NUEVA PELÍCULA GUARDADA\n" + pelicula.mostrarInforPelicula());

                        videoclub.guardarDatosPelicula(pelicula);
                    break;
                case "3":
                    System.out.println("REGISTRANDO CLIENTE");
                    String dni, nombre, direccion;
                    LocalDate fechaNacim;
                    System.out.println();
                    //Primero hay que evaluar los parámetros que pueden hacer que no se puede registrar un cliente
                    //Si alguno de ellos no es correcto, el programa sale de la opción de registro

                        System.out.println("AHORA LE VAMOS A SOLICITAR SU DNI");
                        dni = MyUtils.formatoDni();
                        if(!videoclub.comprobarClientes(dni)){
                            System.out.println("AHORA NECESITAMOS SU FECHA DE NACIMIENTO");
                            fechaNacim = MyUtils.insertarFPorTeclado();
                            if(fechaNacim != null){
                                nombre = MyUtils.obtenerDatoSolicitado("NOMBRE");
                                direccion = MyUtils.obtenerDatoSolicitado("DIRECCION");
                                cliente = new Cliente(dni, nombre, direccion, fechaNacim,videoclub.getClientesTotales());
                                videoclub.guardarDatosCliente(cliente);
                                System.out.println("\n" + cliente.mostrarDatosCliente());
                            }else{
                                System.out.println("NO SE PUEDE DAR DE ALTA EL CLIENTE PUES ES MENOR DE EDAD");
                            }
                        }
                    break;
                case "4":
                    //Esta es la parte más complicada de todas, pues cada objeto de clase cliente o película lleva sus propias
                    //validaciones. Aparte de eso, hay que validar que haya creado un videoclub primero, o clientes o películas
                    if (videoclub.getClientesTotales() > 0 && !videoclub.clientesDadosAlta().isEmpty()) {
                        System.out.println("ESTE ES EL LISTADO DE CLIENTES:\n " + videoclub.clientesDadosAlta());
                        cliente = null;
                        do {
                            System.out.println("SELECCIONE EL SOCIO QUE VA A ALQUILAR INDICANDO SU CÓDIGO NUMÉRICO, SIN LETRA");
                            codigo = MyUtils.obtenerDatoSolicitado("CODIGO");
                            if (MyUtils.validarEleccion(codigo)) {
                                cliente = videoclub.devolverCliente("C" + codigo);
                                if(cliente == null){
                                    System.out.println("NO EXISTE EL CLIENTE");
                                }else if (cliente.getFechaBaja() != null) {
                                    System.out.println("EL CODIGO DE CLIENTE NO ES VALIDO. DADO DE BAJA");
                                    cliente = null;
                                }
                            } else {
                                System.out.println("FORMATO DE CÓDIGO INCORRECTO");
                            }
                        }while(cliente == null);
                        System.out.println(cliente.mostrarDatosCliente() + "\n");
                        if(videoclub.getPeliculasTotales() > 0 && !videoclub.peliculasAlquiler().isEmpty()){
                            System.out.println("ESTAS SON LAS PELÍCULAS DISPONIBLES EN ESTE MOMENTO:\n" + videoclub.peliculasAlquiler());
                            pelicula = null;
                            do {
                                System.out.println("SELECCIONE LA PELICULA QUE VA A ALQUILAR INDICANDO SU CÓDIGO NUMÉRICO, SIN LETRA");
                                codigo = MyUtils.obtenerDatoSolicitado("CODIGO");
                                if (MyUtils.validarEleccion(codigo)) {
                                    pelicula = videoclub.devolverPelicula("P" + codigo);
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
                        }else{
                            System.out.println("O BIEN LAS PELICULAS ESTAN TODAS ALQUILADAS O DADAS DE BAJA EN ESTE CLUB");
                        }
                    } else {
                        System.out.println("NO EXISTEN CLIENTES DADOS DE ALTA");
                    }
                    break;
                case "5":
                    System.out.println("DEVOLUCIÓN DE PELÍCULAS");
                    System.out.println(videoclub.listadoPeliculasAlquiladas());
                    pelicula = null;
                    if(videoclub.listadoPeliculasAlquiladas().isEmpty()){
                        System.out.println("EN ESTE MOMENTO NO HAY NINGUNA PELÍCULA ALQUILADA");
                    }else{
                        do {
                            System.out.println("SELECCIONE LA PELICULA QUE VA A DEVOLVER INDICANDO SU CÓDIGO NUMÉRICO, SIN LETRA");
                            codigo = MyUtils.obtenerDatoSolicitado("CODIGO");
                            if (MyUtils.validarEleccion(codigo)) {
                                pelicula = videoclub.seleccionarPeliculaAlquilada("P" + codigo);
                                if(pelicula == null){
                                    System.out.println("NO EXISTE EL CODIGO DE PELICULA");
                                }
                            } else {
                                System.out.println("FORMATO DE CÓDIGO INCORRECTO");
                            }
                        }while(pelicula == null);
                        if(MyUtils.comprobarFechaDevolucion(pelicula.getFechaAlquiler())){
                            System.out.println("SE HA EXCEDIDO DEL PERIODO DE 48 HORAS");
                        }
                        videoclub.devolverPeliculaAlquilada(pelicula);
                    }
                    break;
                case "6":
                    System.out.println("ESTE ES EL LISTADO DE CLIENTES DADOS DE ALTA AHORA MISMO");
                    String listadoClientesAlta = videoclub.clientesDadosAlta();
                    System.out.println(listadoClientesAlta);
                    cliente = null;
                    if (!listadoClientesAlta.isEmpty()) {
                        do {
                            System.out.println("SELECCIONE EL SOCIO QUE VA A DAR DE BAJA, INTRODUCIENDO SU NUMERO DE CODIGO");
                            codigo = MyUtils.obtenerDatoSolicitado("CODIGO");
                            if (MyUtils.validarEleccion(codigo)) {
                                cliente = videoclub.devolverCliente("C" + codigo);
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
                    pelicula = null;
                    if (!listadoPeliculasRegistradas.isEmpty()) {
                        do {
                            System.out.println("SELECCIONE LA PELICULA A DAR DE BAJA, INTRODUCIENDO SU NUMERO DE CODIGO");
                            codigo = MyUtils.obtenerDatoSolicitado("CODIGO");
                            if (MyUtils.validarEleccion(codigo)) {
                                pelicula = videoclub.devolverPelicula("P" + codigo);
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
                        System.out.println("NO SE TIENEN PELICULAS DADAS DE ALTA");
                    }
                    break;
                case "8":
                    System.out.println("ELIJA EL VIDEOCLUB CON EL QUE QUIERE TRABAJAR");
                    videoclub = elegirVideoclub(videoclubs, vClubsTotales);
                    break;
                case "9":
                    System.out.println("HASTA LA PROXIMA");
                    break;
                default:
                    break;
            }
        } while (!opcion.equals("9"));
    }

    private static VideoDaw elegirVideoclub(VideoDaw[] v, int totalVideoclubs){
        VideoDaw videoclub = null;
        String listadoVideoclubs = "";
            for(int i = 0; i < totalVideoclubs; i++){
                listadoVideoclubs += (i + 1) + " " + v[i].mostrarInfoVideoclub() + "\n";
            }
            System.out.println(listadoVideoclubs);
            String opcion;
            do{
                opcion = MyUtils.obtenerDatoSolicitado("NUMERO DE VIDEOCLUB LISTADO");
            }while(Integer.parseInt(opcion) < 1 && Integer.parseInt(opcion) > totalVideoclubs);
            videoclub = v[Integer.parseInt(opcion) - 1];
            System.out.println("HA SELECCIONADO EL SIGUIENTE VIDEOCLUB\n" + videoclub.mostrarInfoVideoclub());
            System.out.println();
        return videoclub;
    }
}