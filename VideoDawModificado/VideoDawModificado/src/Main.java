import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static Scanner sc;

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        //Defino una colección en la que voy a almacenar los videoclubs creados
        LinkedList<VideoDaw> listadovideoclubs = new LinkedList<>();

        //Defino las diferentes variables para los diferentes objetos
        Pelicula p;
        Videojuegos v;
        Cliente c;
        Alquiler a;
        VideoDaw video = null;

        //Defino una variable para almacenar la opción elegida
        String opcion;
        //Defino una variable numérica para los casos en los que me venga mejor un int para elegir una opcion
        int opcionNumerica = 0;

        //Siempre al inicio del programa se carga el método creación ficheros para que se puedan realizar todas las
        //acciones posteriores

        try {
            creacionFichero();
            leerFicheroVideoclub(listadovideoclubs);
        } catch (IOException e) {
            System.out.println("Error al crear el fichero");
        }


        System.out.println("Bienvenidos a la maravillosa franquicia de videoclubs VideoDaw");

        do{
            if(listadovideoclubs.isEmpty()){//Lo primero es asegurarme de validar si no hay videoclubs creados, se cree 1
                opcion = "1";
            }else if(video == null){//Si no hay videoclub seleccionado, que automaticamente se vaya a esa sección
                opcion = "2";
            }else{//Si no se cumple nada de lo anterior se procede de forma normal
                sc = new Scanner(System.in);
                System.out.println(opcionesMenu());
                opcion = sc.nextLine();
            }

            switch(opcion){
                case "1":
                    System.out.println("Creación de videoclub");
                        if (creacionVideoDaw(listadovideoclubs)) {
                            System.out.println("Nuevo videoclub creado en la franquicia");
                        }else{
                            System.out.println("No se puede crear un nuevo videoclub. Posiblemente ha introducido un cif que ya" +
                                    "existe o se han encontrado problemas para guardar los datos en el fichero correctamente");
                        }
                    break;
                case "2":
                    System.out.println("Selección de videoclub");
                    String listado = listadoVideoclubs(listadovideoclubs);
                    System.out.println(listado);
                    System.out.println();
                    System.out.println("Indique el cif del Videoclub que quiere seleccionar");
                    String cif = MyUtils.insertarCif();
                    if(seleccionarVideoclub(cif,listadovideoclubs) != null){
                        video = seleccionarVideoclub(cif,listadovideoclubs);
                    }else{
                        System.out.println("No se puede seleccionar un videoclub con ese cif");
                    }
                    break;
                case "3":
                    System.out.println("Inscripción de un artículo");
                    do {
                        System.out.println("1.- Película o 2.- Videojuego");
                        try {
                            opcionNumerica = sc.nextInt();
                        }catch (InputMismatchException e){
                            System.out.println("Introduzca un valor numérico según las opciones mostradas");
                        }
                        switch(opcionNumerica){
                            case 1:
                                p = creacionDeArticuloPelicula(video);
                                video.addArticuloP(p);
                                break;
                            case 2:
                                v = creacionDeArticuloVideojuego(video);
                                try {
                                    video.addArticuloV(v);
                                } catch (IOException e) {
                                    System.out.println("Ha surgido un problema al guardar los datos del articulo en fichero");
                                }
                                break;
                        }
                    } while (opcionNumerica != 1 && opcionNumerica != 2 );
                    break;
                case "4":
                    System.out.println("Afiliación de Clientes");
                    try {
                        c = creacionNuevoCliente(video);
                        video.addCliente(c);
                    } catch (ClienteExistenteException e) {
                        System.out.println(e);
                    }catch (FechaPosteriorActualException e1){
                        System.out.println(e1.toString());
                    }catch(MenorDeEdadException e2){
                        System.out.println(e2.toString());
                    }
                    break;
                case "5":
                    System.out.println("Alquiler de un artículo");
                    String tipo;
                    System.out.println(video.mostrarSociosVideoclub());
                    String codigoElegido = seleccionElementosAlquileres("C");
                    c = video.validacionClienteAlquiler(codigoElegido);
                    if(c != null){
                        System.out.println("Elija el producto a alquilar\n");
                        tipo = getLetra();
                        if(tipo.equals("P")){
                            System.out.println(video.mostrarProductosRegistrados(1));
                            codigoElegido = seleccionElementosAlquileres(tipo);
                            p = video.validacionPeliculaAlquiler(codigoElegido);
                            p.setIsAlquilada(true);
                            p.setFechaAlquiler(LocalDateTime.now());
                            a = new Alquiler(c.getCodSocio(),p.getCodigo());
                            video.almacenarAlquiler(a);
                        }else if(tipo.equals("V")){
                            System.out.println(video.mostrarProductosRegistrados(2));
                            codigoElegido = seleccionElementosAlquileres(tipo);
                            v = video.validacionVideojuegoAlquiler(codigoElegido);
                            v.setIsAlquilada(true);
                            v.setFechaAlquiler(LocalDateTime.now());
                            a = new Alquiler(c.getCodSocio(),v.getCodigo());
                            video.almacenarAlquiler(a);
                        }else{
                            System.out.println("No se ha podido seleccionar un artículo valido");
                        }
                    }else{
                        System.out.println("No se ha podido encontrar el cliente buscado, o bien está dado de baja");
                    }
                    break;
                case "6":
                    System.out.println("Devolución de artículos");
                    System.out.println("Estos son los articulos alquilados actualmente\n" + video.mostrarListadoAlquiler());
                    System.out.println("Seleccione el tipo de artículo. P para película, V para videojuego");
                    tipo = getLetra();
                    codigoElegido = seleccionElementosAlquileres(tipo);
                    video.seleccionArticuloDevolver(codigoElegido);
                    c = video.retornarClienteAlquiler(codigoElegido);
                    break;
                case "7":
                    System.out.println("Dar de baja un cliente");
                    System.out.println("Listado de socios actuales\n" + video.mostrarSociosVideoclub());
                    System.out.println("Introduzca la parte numérica del código de socio que quiere dar de baja");
                    String codigoEliminar = seleccionElementosAlquileres("C");
                    if (video.eliminarCliente(codigoEliminar)) {
                            System.out.println("Estado del socio actualizado");
                        } else {
                            System.out.println("No se ha podido actualizar la baja de" +
                                    " el socio pues no se ha encontrado un elemento con ese" +
                                    "código de socio");
                        }
                    break;
                case "8":
                    System.out.println("Dar de baja un articulo del inventario");
                    String letra;

                    letra = getLetra();


                        if(letra.equals("P")){
                            System.out.println(video.mostrarProductosRegistrados(1));
                            codigoEliminar = seleccionElementosAlquileres(letra);
                            if (video.eliminarArticuloInventario(codigoEliminar)) {
                                System.out.println("Actualizados datos de producto");
                            } else {
                                System.out.println("No se ha podido encontrar un producto con el codigo indicado");
                            }
                        }else if(letra.equals("V")){
                            System.out.println(video.mostrarProductosRegistrados(2));
                            codigoEliminar = seleccionElementosAlquileres(letra);
                            if (video.eliminarArticuloInventario(codigoEliminar)) {
                                System.out.println("Actualizados datos de producto");
                            } else {
                                System.out.println("No se ha podido encontrar un producto con el codigo indicado");
                            }
                        }
                    break;
                case "9":
                    System.out.println("Listados diversos");
                    System.out.println("Elija uno de los listados disponibles:\n" + elegirListados(video));
                    break;
                case "0":
                    try {
                        guardadoDatosFicheros(listadovideoclubs);
                    } catch (IOException e) {
                        System.out.println("Error en el manejo de ficheros");
                    }
                    System.out.println("Saliendo del sistema");
                    break;
            }
        }while(!opcion.equals("0"));

    }


    /**
     * Método que nos permite elegir entre Película y Videoclub
     * @return La letra inical del código de producto
     */
    public static String getLetra() {
        String letra;
        do{
            sc = new Scanner(System.in);
            System.out.println("Introduzca P para película o bien V para Videojuego");
            letra = sc.nextLine().toUpperCase();
        }while(!letra.equals("P") && !letra.equals("V"));
        return letra;
    }

    /**
     * Metodo que nos retorna las posibles opciones que podemos elegir
     * @return String
     */
    static String opcionesMenu(){
        return """
                Elija una de las siguiente opciones
                1.- Crear o registrar Videoclub en la franquicia
                2.- Seleccionar \
                videoclub
                3.- Registrar articulo en videoclub
                4.- Crear y registrar clientes en el videoclub
                5.- Alquilar Producto
                6.- Devolver Producto
                7.- Dar de baja Cliente
                8.- Dar de baja Artículo
                9.- Listados del videoclub
                0.- Salir""";
    }


    /**
     * Método que recoge las operaciones necesarias para crear un nuevo videoclub y valida que no existe un videoclub
     * ya creado con el mismo cif
     * @param listadovideoclubs Una linkedlist con los datos de los videoclubs creados hasta ahora
     * @return el valor de un booleano para indicar si se ha creado o no un videoclub
     * @throws IOException en el caso de encontrar errores a la hora de trabajar con ficheros
     */
    public static boolean creacionVideoDaw(LinkedList<VideoDaw> listadovideoclubs){
        boolean creado = false, encontrado = false;
        VideoDaw nuevoV;

        String cif = MyUtils.insertarCif();
        String direccion;
        for(VideoDaw v : listadovideoclubs){
            if(v.getCif().equals(cif)){
                encontrado = true;
                break;
            }
        }
        if(!encontrado){
            direccion = MyUtils.insertarDireccion();
            nuevoV = new VideoDaw(cif,direccion);
            creado = true;
            listadovideoclubs.add(nuevoV);
        }
        return creado;
    }

    /**
     * Método que me devuelve un listado de los videoclubs almacenados actualmente en la colección
     * @param listadovideoclubs Coleccion que almacena todos los videoclubs creados hasta el momento
     * @return String con un listado de videoclubs
     */
    public static String listadoVideoclubs(LinkedList<VideoDaw> listadovideoclubs){
        StringBuilder listado = new StringBuilder();
            for(VideoDaw v : listadovideoclubs){
                listado.append("\n").append(v.getCif());
            }
        return listado.toString();
    }

    /**
     * Método para seleccionar uno de los videoclubs que hemos creado para trabajar con el a partir de ese momento
     * @param cif String con el cif introducido por el teclado para usarlo como condición de busqueda
     * @param listadovideoclubs Coleccion que almacena los diferentes videoclubs que hemos creado
     * @return Un objeto del tipo VideoDaw seleccionado mediante el cif del mismo
     */
    public static VideoDaw seleccionarVideoclub(String cif, LinkedList<VideoDaw> listadovideoclubs){
        VideoDaw v = null;
        for(VideoDaw video : listadovideoclubs){
            if(video.getCif().equals(cif)){
                v = video;
            }
        }
        return v;
    }

    /**
     * Método que utilizamos para obtener todo lo necesario para crear un objeto Pelicula
     * @param v El objeto VideoDaw con el que estamos trabajando, que nos sirve para obtener Códigos
     * @return un objeto pelicula creado
     */
    public static Pelicula creacionDeArticuloPelicula(VideoDaw v){
        Pelicula p = null;
        sc = new Scanner(System.in);
        GenerosPeliculas genero = MyUtils.devolverGeneroP(seleccionGenero(1));
        String codigo = MyUtils.generadorCodigos("P", v.getContadorArticulos());
        System.out.println("Inserte título del producto");
        System.out.println();
        sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        p = new Pelicula(codigo,titulo,genero);
        return p;
    }

    /**
     * Método que utilizamos para obtener todo lo necesario para crear un objeto Videojuegos
     * @param v El objeto VideoDaw con el que estamos trabajando, que nos sirve para obtener Códigos
     * @return un objeto Videojuegos creado
     */
    public static Videojuegos creacionDeArticuloVideojuego(VideoDaw v){
        Videojuegos vJuego = null;
        sc = new Scanner(System.in);
        GenerosVidejuegos genero = MyUtils.devolverGeneroV(seleccionGenero(2));
        String codigo = MyUtils.generadorCodigos("V", v.getContadorArticulos());
        System.out.println("Inserte título del producto");
        System.out.println();
        sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        vJuego = new Videojuegos(codigo,titulo,genero);
        return vJuego;
    }



    /**
     * Metodo para seleccionar el género de un artículo
     * @param tipoArticulo Un int que nos indica si es una Pelicula o un Videojuego
     * @return Un int que nos va a indicar la posición del enum correspondiente que necesitamos
     */
    public static int seleccionGenero(int tipoArticulo){
        sc = new Scanner(System.in);
        int seleccion = -1;
        do{
            try {
                System.out.println(MyUtils.listadoGeneros(tipoArticulo));
                seleccion = sc.nextInt();
                seleccion--;
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un numero");
            }
        }while(seleccion < 0 || seleccion >= GenerosPeliculas.values().length);
        return seleccion;
    }

    /**
     * Metodo que seguimos para crear un nuevo Cliente en el videoclub
     * @param v Un objeto de tipo VideoDaw
     * @return un Objeto de tipo Cliente
     */
    public static Cliente creacionNuevoCliente(VideoDaw v){
        sc = new Scanner(System.in);
        Cliente cliente = null;
        String dni = MyUtils.insertarDni();
        v.validarDniNuevoCliente(dni);
        String nombre = MyUtils.insertarNombre();
        String direccion = MyUtils.insertarDireccion();
        LocalDate fnacim = MyUtils.insertarFecha();
        String codigo = MyUtils.generadorCodigos("C", v.getContadorClientes());
        cliente = new Cliente(nombre,dni,direccion,fnacim,codigo);
        return cliente;
    }

    /**
     * Metodo que nos deja elejir entre diferentes listados
     * @param v Objeto VideoDaw del cual vamos a obtener los listados
     * @return String con los listados Correspondientes
     */
    public static String elegirListados(VideoDaw v){
        StringBuilder listado = new StringBuilder("Este es el listado seleccionado\n");
        sc = new Scanner(System.in);
        int opcion = 0;
        do{
            try{
                System.out.println("""
                        Elija una de las siguientes opciones
                        1.-Mostrar Peliculas
                        2.-Mostrar Videojuegos
                        3.-Mostrar todos artículos
                        4.-Mostrar Listado Articulos Histórico
                        5.-Mostrar Listado Clientes Alta
                        6.-Mostrar Listado Clientes Histórico""");
                opcion = sc.nextInt();
                switch (opcion){
                    case 1:
                        listado.append(v.mostrarProductosRegistrados(1));
                        break;
                    case 2:
                        listado.append(v.mostrarProductosRegistrados(2));
                        break;
                    case 3:
                        listado.append(v.mostrarProductosRegistrados(3));
                        break;
                    case 4:
                        listado.append(v.mostrarProductosRegistrados(4));
                        break;
                    case 5:
                        listado.append(v.mostrarSociosVideoclub());
                        break;
                    case 6:
                        listado.append(v.mostrarSociosVideoclubHistorico());
                    default:
                        System.out.println("Opcion elegida no valida");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Introduzca un numero");
            }

        }while(opcion <= 0 || opcion > 6);
        return listado.toString();
    }

    public static String seleccionElementosAlquileres(String letra){
        sc = new Scanner(System.in);
        String codigoElegido = letra;
        System.out.println("Indique la parte numerica del codigo elegido");
        codigoElegido += sc.nextLine();

        return codigoElegido;
    }

    /**
     * Método que dada la primera vez que se ejecuta el programa crea la estructura necesaria para el almacenamiento de
     * ficheros. Al inicio del programa siempres se llama a este método para comprobar que está creada la estructura
     * de ficheros.
     * @throws IOException al trabajar con ficheros puede lanzar una IOException
     */
    public static void creacionFichero() throws IOException {
        String nombreFichero = "ListadoVideoclubs.csv";
        String nombreDirectorio = "./resources";
        File directorio = new File(nombreDirectorio);
        File fichero = new File(directorio,nombreFichero);
        directorio.mkdir();
        fichero.createNewFile();
    }

    public static void guardadoDatosFicheros(LinkedList<VideoDaw> videoclubs) throws IOException {

            try(FileWriter file = new FileWriter("./resources/ListadoVideoclubs.csv",false);
            BufferedWriter escritor = new BufferedWriter(file)) {
                for(VideoDaw video : videoclubs){
                    String datosTemporales = videoclubToFile(video);
                    guardarDatosPropiosVideoclub(video);
                    escritor.write(datosTemporales);
                    escritor.newLine();
                }
        }
    }

    /**
     * Metodo que transforma los datos de un objeto VideoDaw recien creado para almacenarlos en el fichero
     * @param v Objeto de tipo VideoDaw
     * @return String con los datos preparados para su almacenamiento en un fichero
     */
    public static String videoclubToFile(VideoDaw v){
        return v.getCif() + "," + v.getDireccion() + "," + MyUtils.formatearFecha(v.getFechaAlta());
    }

    public static void leerFicheroVideoclub(LinkedList<VideoDaw> videoclubs) throws IOException {
        VideoDaw video = null;

        try(FileReader file = new FileReader("./resources/ListadoVideoclubs.csv");
        BufferedReader lector = new BufferedReader(file)) {
            String datosTemporales = lector.readLine();
            while(datosTemporales != null){
                String[] datos = datosTemporales.split(",");
                video = new VideoDaw(datos[0],datos[1],LocalDate.parse(datos[2],MyUtils.formatoFecha));
                leerFicherosPropiosVideoclub(video, videoclubs);
                videoclubs.add(video);
                datosTemporales = lector.readLine();
            }
        }
    }

    public static void leerFicherosPropiosVideoclub(VideoDaw video, LinkedList<VideoDaw> videoclubs) throws IOException {
        //Declaro variables que son el nombre del fichero que me van a servir despues
        String ficheroArticulos = video.getCif() + "_Articulos.csv";
        String ficheroClientes = video.getCif() + "_Clientes.csv";
        String ficheroAlquileres = video.getCif() + "_Alquileres.csv";

        //Declaro variables de las diferentes clases que tengo
        Pelicula p;
        Videojuegos vj;
        Cliente c;
        Alquiler a;

        //String nombre, String dni, String direccion, LocalDate fechaNacimiento, String codSocio,
        //LocalDate fechaBaja Pongo esto aquí para saber como tengo que volver a crear el objeto

        LocalDate fBaja = null;
        LocalDateTime fDevolucion = null;

        try(FileReader file = new FileReader("./resources/" + ficheroClientes);
            FileReader file2 = new FileReader("./resources/" + ficheroArticulos);
            FileReader file3 = new FileReader("./resources/" + ficheroAlquileres);
            BufferedReader lector = new BufferedReader(file);
            BufferedReader lector2 = new BufferedReader(file2);
            BufferedReader lector3 = new BufferedReader(file3);
            ){

            String datosTemporales = lector.readLine();
            while (datosTemporales != null) {
                String[] datos = datosTemporales.split(",");
                if (datos[5].equals("0")) {
                    fBaja = null;
                } else {
                    fBaja = LocalDate.parse(datos[5], MyUtils.formatoFecha);
                }
                c = new Cliente(datos[0], datos[1], datos[2], LocalDate.parse(datos[3], MyUtils.formatoFecha), datos[4], fBaja);
                video.socios.add(c);
                datosTemporales = lector.readLine();
            }


            /*String codigo, String titulo, GenerosPeliculas/Videojuegos genero, LocalDate fechaAlata, LocalDate fechaBaja,
            LocalDateTime fechaAlquiler,boolean isAlquilada Recordatorio de como tengo que construir los objetos*/
            boolean alquilada = false;
            datosTemporales = lector2.readLine();
            while (datosTemporales != null) {
                String[] datos = datosTemporales.split(",");
                String primeraLetra = datos[0].charAt(0) + "";
                if (datos[6].equals("Alquilada")) {
                    alquilada = true;
                }
                if (!datos[5].equals("0")) {
                    fDevolucion = LocalDateTime.parse(datos[5], MyUtils.formatoFechaHora);
                }else{
                    fDevolucion = null;
                }
                if (!datos[4].equals("0")) {
                    fBaja = LocalDate.parse(datos[4], MyUtils.formatoFecha);
                } else {
                    fBaja = null;
                }
                if (primeraLetra.equals("P")) {
                    p = new Pelicula(datos[0], datos[1], GenerosPeliculas.valueOf(datos[2]), LocalDate.parse(datos[3],
                            MyUtils.formatoFecha), fBaja, fDevolucion, alquilada);
                    video.inventarioProductos.add(p);
                } else {
                    vj = new Videojuegos(datos[0], datos[1], GenerosVidejuegos.valueOf(datos[2]), LocalDate.parse(datos[3],
                            MyUtils.formatoFecha), fBaja, fDevolucion, alquilada);
                    video.inventarioProductos.add(vj);
                }
                datosTemporales = lector2.readLine();
            }
            fDevolucion = null;
            datosTemporales = lector3.readLine();
            while (datosTemporales != null) {
                String[] datos = datosTemporales.split(",");
                if (!datos[3].equals("0")) {
                    fDevolucion = LocalDateTime.parse(datos[3], MyUtils.formatoFechaHora);
                } else {
                    fDevolucion = null;
                }
                a = new Alquiler(datos[0], datos[1],
                        LocalDateTime.parse(datos[2], MyUtils.formatoFechaHora),
                        fDevolucion);
                video.listadoAlquileres.add(a);
                datosTemporales = lector3.readLine();


            }
        } catch (Exception e) {

        }

        /*try (FileReader file = new FileReader("./resources/" + ficheroClientes)) {
            try (FileReader file2 = new FileReader("./resources/" + ficheroArticulos)) {
                try (FileReader file3 = new FileReader("./resources/" + ficheroAlquileres)) {
                    try (BufferedReader lector = new BufferedReader(file)) {
                        try (BufferedReader lector2 = new BufferedReader(file2)) {
                            try (BufferedReader lector3 = new BufferedReader(file3)) {

                                String datosTemporales = lector.readLine();
                                while (datosTemporales != null) {
                                    String[] datos = datosTemporales.split(",");
                                    if (datos[5].equals("0")) {
                                        fBaja = null;
                                    } else {
                                        fBaja = LocalDate.parse(datos[5], MyUtils.formatoFecha);
                                    }
                                    c = new Cliente(datos[0], datos[1], datos[2], LocalDate.parse(datos[3], MyUtils.formatoFecha), datos[4], fBaja);
                                    video.socios.add(c);
                                    datosTemporales = lector.readLine();
                                }


            String codigo, String titulo, GenerosPeliculas/Videojuegos genero, LocalDate fechaAlata, LocalDate fechaBaja,
            LocalDateTime fechaAlquiler,boolean isAlquilada Recordatorio de como tengo que construir los objetos
                                boolean alquilada = false;
                                datosTemporales = lector2.readLine();
                                while (datosTemporales != null) {
                                    String[] datos = datosTemporales.split(",");
                                    String primeraLetra = datos[0].charAt(0) + "";
                                    if (datos[6].equals("Alquilada")) {
                                        alquilada = true;
                                    }
                                    if (!datos[5].equals("0")) {
                                        fDevolucion = LocalDateTime.parse(datos[5], MyUtils.formatoFechaHora);
                                    }else{
                                        fDevolucion = null;
                                    }
                                    if (!datos[4].equals("0")) {
                                        fBaja = LocalDate.parse(datos[4], MyUtils.formatoFecha);
                                    } else {
                                        fBaja = null;
                                    }
                                    if (primeraLetra.equals("P")) {
                                        p = new Pelicula(datos[0], datos[1], GenerosPeliculas.valueOf(datos[2]), LocalDate.parse(datos[3],
                                                MyUtils.formatoFecha), fBaja, fDevolucion, alquilada);
                                        video.inventarioProductos.add(p);
                                    } else {
                                        vj = new Videojuegos(datos[0], datos[1], GenerosVidejuegos.valueOf(datos[2]), LocalDate.parse(datos[3],
                                                MyUtils.formatoFecha), fBaja, fDevolucion, alquilada);
                                        video.inventarioProductos.add(vj);
                                    }
                                    datosTemporales = lector2.readLine();
                                }
                                fDevolucion = null;
                                datosTemporales = lector3.readLine();
                                while (datosTemporales != null) {
                                    String[] datos = datosTemporales.split(",");
                                    if(!datos[3].equals("0")) {
                                        fDevolucion = LocalDateTime.parse(datos[3], MyUtils.formatoFechaHora);
                                    }else{
                                        fDevolucion = null;
                                    }
                                    a = new Alquiler(datos[0],datos[1],
                                            LocalDateTime.parse(datos[2],MyUtils.formatoFechaHora),
                                            fDevolucion);
                                    video.listadoAlquileres.add(a);
                                    datosTemporales = lector3.readLine();
                                }
                            }
                        }
                    }
                }
            }
        }*/

    }

    public static void guardarDatosPropiosVideoclub(VideoDaw video) throws IOException {

        //Declaro variables que son el nombre del fichero que me van a servir despues
        String ficheroArticulos = video.getCif() + "_Articulos.csv";
        String ficheroClientes = video.getCif() + "_Clientes.csv";
        String ficheroAlquileres = video.getCif() + "_Alquileres.csv";

        //Creo nuevos objetos File que me van a servir después para crear estos ficheros en caso de que
        //No existan
        File fich1 = new File(ficheroArticulos);
        File fich2 = new File(ficheroClientes);
        File fich3 = new File(ficheroAlquileres);
        fich1.createNewFile();
        fich2.createNewFile();
        fich3.createNewFile();

        //Una variable string que voy a necesitar despues
        String datosTemporales = "";

        try(FileWriter file = new FileWriter("./resources/" + ficheroClientes);BufferedWriter escritor = new BufferedWriter(file)) {
            for (Cliente cliente : video.socios) {
                datosTemporales = clienteToFile(cliente);
                escritor.write(datosTemporales);
                escritor.newLine();
            }
        }

        try(FileWriter file1 = new FileWriter("./resources/" + ficheroArticulos);BufferedWriter escritor1 = new BufferedWriter(file1)) {
            for(Articulo articulo : video.inventarioProductos){
                if(articulo instanceof Pelicula){
                    datosTemporales = peliculaToFile((Pelicula)articulo);
                    escritor1.write(datosTemporales);
                }else{
                    datosTemporales = videojuegoToFile((Videojuegos)articulo);
                    escritor1.write(datosTemporales);
                }
                escritor1.newLine();
            }
        }


        try(FileWriter file2 = new FileWriter("./resources/"+ ficheroAlquileres);BufferedWriter escritor2 = new BufferedWriter(file2)) {
            for(Alquiler alquiler : video.listadoAlquileres){
                datosTemporales = alquileresToFile(alquiler);
                escritor2.write(datosTemporales);
                escritor2.newLine();
            }
        }
    }

    public static String clienteToFile(Cliente cliente) {
        String fechaDeBaja = "0";
        if(cliente.getFechaBaja() != null){
            fechaDeBaja = MyUtils.formatearFecha(cliente.getFechaBaja());
        }

        return cliente.getNombre() + "," + cliente.getDni() + "," + cliente.getDireccion() + "," +
                MyUtils.formatearFecha(cliente.getFechaNacimiento()) + "," + cliente.getCodSocio() + "," + fechaDeBaja;

    }

    public static String peliculaToFile(Pelicula pelicula) {
        String fechaAlquiler;
        String fechaBaja;
        if(pelicula.getFechaAlquiler() == null){
            fechaAlquiler = "0";
        }else{
            fechaAlquiler = MyUtils.formatearFechaHora(pelicula.getFechaAlquiler());
        }

        if(pelicula.getFechaBaja() == null){
            fechaBaja = "0";
        }else{
            fechaBaja = MyUtils.formatearFecha(pelicula.getFechaBaja());
        }
        return pelicula.getCodigo() + "," + pelicula.getTitulo() + "," + pelicula.getGenero().toString() + "," +
                MyUtils.formatearFecha(pelicula.getFechaAlta()) + "," + fechaBaja
        + "," + fechaAlquiler + "," + pelicula.isAlquilada();
    }

    public static String videojuegoToFile(Videojuegos videojuego) {
        String fechaAlquiler;
        if(videojuego.getFechaAlquiler() == null){
            fechaAlquiler = "0";
        }else{
            fechaAlquiler = MyUtils.formatearFechaHora(videojuego.getFechaAlquiler());
        }
        String fechaBaja;
        if(videojuego.getFechaBaja() == null){
            fechaBaja = "0";
        }else{
            fechaBaja = MyUtils.formatearFecha(videojuego.getFechaBaja());
        }

        return videojuego.getCodigo() + "," + videojuego.getTitulo() + "," + videojuego.getGenero().toString() + "," +
                MyUtils.formatearFecha(videojuego.getFechaAlta()) + "," + fechaBaja
                + "," + fechaAlquiler + "," + videojuego.isAlquilada();
    }

    public static String alquileresToFile(Alquiler alquiler) {
        String fecha;
        if(alquiler.getFechaDevolucion() == null){
            fecha = "0";
        }else{
            fecha = MyUtils.formatearFechaHora(alquiler.getFechaDevolucion());
        }

        return alquiler.getCodSocio() + "," + alquiler.getCodProducto() + "," + MyUtils.formatearFechaHora(alquiler.getFechaAlquiler())
        + "," + fecha;
    }
}