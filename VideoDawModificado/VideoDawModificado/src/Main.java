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

        //Antes de comenzar a hacer nada hay que asegurarse, por si es la primera vez que arranca el programa, de que se
        //cree la estructura de directorios y ficheros necesaria

        try {
            creacionFichero();
        } catch (IOException e) {
            System.out.println("Error al crear fichero");
        }

        //Después de esto me tengo que asegurar de que por si acaso se lean los datos de ficheros existentes, en este caso
        //el listado de videoclubs almacenados
        if (leerDatosdeFichero(listadovideoclubs)) {
            System.out.println("Se han cargado datos de videoclubs previamente guardados");
        } else {
            System.out.println("No se han podido cargar archivos. Bien por inexistencia, o no había elementos guardados");
        }

        //Creo un método que lea todos los ficheros correspondientes a todos los videoclubs, ese método está en videodaw
        System.out.println(lecturaFicherosVideoclubs(listadovideoclubs));

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
                    try {
                        if (creacionVideoDaw(listadovideoclubs)) {
                            System.out.println("Nuevo videoclub creado en la franquicia");
                        }else{
                            System.out.println("No se puede crear un nuevo videoclub. Posiblemente ha introducido un cif que ya" +
                                    "existe o se han encontrado problemas para guardar los datos en el fichero correctamente");
                        }
                    } catch (IOException e) {
                        System.out.println("Error al crear un nuevo Videoclub");
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
                                try {
                                    video.addArticuloP(p);
                                } catch (IOException e) {
                                    System.out.println("Ha surgido un problema al guardar los datos del articulo en fichero");
                                }
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
                    }catch (IOException e){
                        System.out.println("Problemas en el manejo de ficheros");
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
                            c.almacenarAlquileres(a);
                        }else if(tipo.equals("V")){
                            System.out.println(video.mostrarProductosRegistrados(2));
                            codigoElegido = seleccionElementosAlquileres(tipo);
                            v = video.validacionVideojuegoAlquiler(codigoElegido);
                            v.setIsAlquilada(true);
                            v.setFechaAlquiler(LocalDateTime.now());
                            a = new Alquiler(c.getCodSocio(),v.getCodigo());
                            video.almacenarAlquiler(a);
                            c.almacenarAlquileres(a);
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
                    c.actualizarDatosAlquiler(codigoElegido);
                    break;
                case "7":
                    System.out.println("Dar de baja un cliente");
                    System.out.println("Listado de socios actuales\n" + video.mostrarSociosVideoclub());
                    System.out.println("Introduzca la parte numérica del código de socio que quiere dar de baja");
                    String codigoEliminar = seleccionElementosAlquileres("C");
                    try {
                        if (video.eliminarCliente(codigoEliminar)) {
                            System.out.println("Estado del socio actualizado");
                        } else {
                            System.out.println("No se ha podido actualizar la baja de" +
                                    " el socio pues no se ha encontrado un elemento con ese" +
                                    "código de socio");
                        }
                    } catch (IOException e) {
                        System.out.println("Problemas en el manejo de ficheros para eliminar el cliente");
                    }
                    break;
                case "8":
                    System.out.println("Dar de baja un articulo del inventario");
                    String letra;

                    letra = getLetra();

                    try {
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
                    } catch (IOException e) {
                        System.out.println("Problemas al actualizar el fichero de artículos");
                    }
                    break;
                case "9":
                    System.out.println("Listados diversos");
                    System.out.println("Elija uno de los socios disponibles:\n" + elegirListados(video));
                    break;
                case "0":
                    System.out.println("Saliendo del sistema");
                    break;
            }
        }while(!opcion.equals("0"));


    }

    /**
     * Método que nos permite elegir entre Película y Videoclub
     * @return La letra inical del código de producto
     */
    private static String getLetra() {
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
     * Metodo para cargar los datos de un fichero para almacenarlos en el listado de videoclubs
     * @param listadovideoclubs Una colección de objetos Videoclub
     * @return Un booleano que nos indica si se ha podido leer un archivo o no
     */
    static Boolean leerDatosdeFichero(LinkedList<VideoDaw> listadovideoclubs){
        boolean ficheroLeido = true;
        VideoDaw v;

        try(FileReader ficheroVideoclubs = new FileReader("./resources/ListadoVideoclubs.csv");
        BufferedReader lector = new BufferedReader(ficheroVideoclubs)){
            String linea = lector.readLine();
            while (linea != null) {
                String[] datos = linea.split(",");
                v = new VideoDaw(datos[0],datos[1],LocalDate.parse(datos[2],MyUtils.formatoFecha));
                listadovideoclubs.add(v);
                linea = lector.readLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("No se encontro el fichero");
            ficheroLeido = false;
        }catch (IOException e){
            System.out.println("Error al leer el fichero");
            ficheroLeido = false;
        }
        return ficheroLeido;
    }


    /**
     * Método que recoge las operaciones necesarias para crear un nuevo videoclub y valida que no existe un videoclub
     * ya creado con el mismo cif
     * @param listadovideoclubs Una linkedlist con los datos de los videoclubs creados hasta ahora
     * @return el valor de un booleano para indicar si se ha creado o no un videoclub
     * @throws IOException en el caso de encontrar errores a la hora de trabajar con ficheros
     */
    public static boolean creacionVideoDaw(LinkedList<VideoDaw> listadovideoclubs) throws IOException {
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
            String datosParaFichero = videoclubToFile(nuevoV);
            almacenarNuevoVidoclub(datosParaFichero);
        }
        return creado;
    }

    /**
     * Metodo que transforma los datos de un objeto VideoDaw recien creado para almacenarlos en el fichero
     * @param v Objeto de tipo VideoDaw
     * @return String con los datos preparados para su almacenamiento en un fichero
     */
    public static String videoclubToFile(VideoDaw v){
        return v.getCif() + "," + v.getDireccion() + "," + MyUtils.formatearFecha(v.getFechaAlta());
    }

    /**
     * Método que recién creado un nuevo objeto VideoDaw de forma correcta almacena los datos en un fichero
     * @param cadenaDatos String con los datos del nuevo videoclub preparados para guardar en el fichero
     * @throws IOException Lanza exception del tipo IO
     */
    public static void almacenarNuevoVidoclub(String cadenaDatos) throws IOException {
        try(FileWriter ficheroVideoclubs = new FileWriter("./resources/ListadoVideoclubs.csv",true);
        BufferedWriter escritorVideoclubs = new BufferedWriter(ficheroVideoclubs)){
            escritorVideoclubs.write(cadenaDatos);
            escritorVideoclubs.newLine();
        }
    }

    /**
     * Método que dada la primera vez que se ejecuta el programa crea la estructura necesaria para el almacenamiento de
     * ficheros
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
     * Metodo que recorre la colección de Videoclubs y va cargando todos sus archivos al inicio del programa
     * @param listadovideoclubs Coleccion que almacena todos los videoclubs creados
     * @return String con mensaje de confirmacion
     */
    public static String lecturaFicherosVideoclubs(LinkedList<VideoDaw> listadovideoclubs){
        String confirmacion="Listado de datos de videoclubs que se han cargado\n";
        for(VideoDaw v : listadovideoclubs){
            v.leerDatosdeFicherosPropios();
            confirmacion += v.getCif() + "\n";
        }
        confirmacion += "\nArchivos del videoclub cargados.";
        return confirmacion;
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
        try {
            cliente = new Cliente(nombre,dni,direccion,fnacim,codigo);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
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
}