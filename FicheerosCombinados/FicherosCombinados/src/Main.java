import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        System.out.println("Bienvenido al almacen");


        String nombreFicheroCsv = "FicherosCombinados/Recursos/productos.csv";
        String nombreFicheroBinario = "FicherosCombinados/Recursos/almacen.dat";

        LinkedList<Producto> inventarioAlmacen = new LinkedList<Producto>();

        ArrayList<Producto> datosCsv;


        datosCsv = leerDatosFicheroCsv(nombreFicheroCsv);


        int i = 0;
        boolean eof = false;
        try(FileInputStream fichero = new FileInputStream(nombreFicheroBinario);
            DataInputStream lector = new DataInputStream(fichero)){

            while(!eof){
                Producto p = datosCsv.get(i);

                int cantidad = lector.readInt();
                double precio = lector.readDouble();
                int descuento = lector.readInt();
                int iva = lector.readInt();
                boolean aplicarDescuenta = lector.readBoolean();

                p.setCantidad(cantidad);
                p.setPrecio(precio);
                p.setDescuento(descuento);
                p.setIva(iva);
                p.setAplicarDto(aplicarDescuenta);

                inventarioAlmacen.add(p);

                if(i < datosCsv.size() - 1){
                    i++;
                }
            }

        }catch(IOException e){
            System.out.println("Error al leer el fichero");
            eof = true;
        }


        //Declaro las variables que necesito
        Scanner sc = new Scanner(System.in);

        String opcion = "", referencia;

        do{
            System.out.println("Opciones disponibles en nuestro almacen");

            System.out.println(devolverOpcionesMenu());
            opcion = sc.nextLine();

            switch(opcion){

                case "1":

                    System.out.println("Selecciones por que campo quiere ordenar la lista para mostrar");


                    inventarioAlmacen.sort(new ComparadorReferencia());

                    System.out.println(seleccionarOrdenacionLista(inventarioAlmacen));

                    break;
                case "2":
                    System.out.println("Eliminar producto por referencia");

                    System.out.println(mostrarListadoProductosPorReferencia(inventarioAlmacen));

                    System.out.println("Inserte la parte numérica de la referencia: 000");

                    referencia = insertarParteNumericaReferencia();

                    if(eliminarElementoPorReferencia(referencia, inventarioAlmacen)){
                        System.out.println("El producto con la referencia " + referencia + " se ha eliminado");
                    }else{
                        System.out.println("El producto con la referencia " + referencia + " no ha podido eliminarse");
                    }

                    break;
                case "3":

                    System.out.println("Guardar y salir");

                    //Como probé el comparator, cuando ejecute este método para guardar y salir, tengo que asegurarme
                    //de volver a ordenar los elementos por referencia

                    guardadoEnFicheros(inventarioAlmacen, nombreFicheroCsv, nombreFicheroBinario);

                    break;

                case "4":

                    System.out.println("Registro de productos");

                    do {
                        System.out.println("Inserte un número de referencia usando el siguiente formato:\n" +
                                " 000 - mínimo 1 digito máximo 3");

                        referencia = crearCodigoReferencia();

                    } while (validarCodigoReferencia(referencia, inventarioAlmacen));

                    System.out.println("Se le van a solicitar una serie de datos. Siga las instrucciones.");
                    crearNuevoArticulo(referencia, inventarioAlmacen);
                    break;

                default:
                    System.out.println("Opción incorrecta");
                    break;
            }

        }while(!opcion.equals("3"));


    }

    public static ArrayList<Producto> leerDatosFicheroCsv(String nombreFicheroCsv) {

        ArrayList<Producto> intermedio = new ArrayList<>();
        Producto producto;

        try(FileReader fichero = new FileReader(nombreFicheroCsv);
            BufferedReader lector = new BufferedReader(fichero)){

                String linea = lector.readLine();
                while (linea != null) {
                    String[] datos = linea.split("/");
                    producto = new Producto(datos[0], datos[1], datos[2], datos[3]);
                    intermedio.add(producto);
                    linea = lector.readLine();
                }
        }catch (IOException e){
            System.out.println("Error al leer el fichero");
        }

        return intermedio;
    }

    public static String devolverOpcionesMenu(){
        return """
                Elija una de las opciones disponibles
                1. Mostrar Productos en el Inventario.
                2. Eliminar Producto por referencia.
                3. Guardar y Salir (inventario.dat).
                4. Registrar producto en el Inventario
                """;
    }

    public static String mostrarListadoProductosPorReferencia(LinkedList<Producto> lista){
        String listado = "Estos son nuestros productos y su referencia\n";

        for(Producto p : lista){
            listado += p.getReferencia() + ", " + p.getNombre() + "\n";
        }
        return listado;
    }

    public static String insertarParteNumericaReferencia(){
        String referencia = "";
        Scanner lector = new Scanner(System.in);
        Pattern patron = Pattern.compile("[0-9]{3}");
        Matcher match;
        do{
            referencia = lector.nextLine();
            match = patron.matcher(referencia);
        }while(!match.matches());
        return "ref-" + referencia;
    }

    public static boolean eliminarElementoPorReferencia(String referencia, LinkedList<Producto> listado){
        boolean eliminado = false;
        Producto producto = null;

        for(Producto p : listado){
            if(p.getReferencia().equals(referencia)){
                producto = p;
            }
        }

        if(producto != null){
            listado.remove(producto);
            eliminado = true;
        }

        return eliminado;
    }

    public static void guardadoEnFicheros(LinkedList<Producto> lista, String direccionCsv, String direccionBinario){

        //Creo que poniendo esto aquí ya siempre me va a dejar los datos ordenados por referencia. Así también, si doy de
        //baja un elemento y luego vuelvo a usar esa referencia para dar de alta un nuevo producto, van a estar siempre
        //ordenados por referencia
        lista.sort(new ComparadorReferencia());

        try(FileWriter fichero = new FileWriter(direccionCsv,false);
        BufferedWriter escritor1 = new BufferedWriter(fichero);
            FileOutputStream fichero2 = new FileOutputStream(direccionBinario,false);
        DataOutputStream escritor2 = new DataOutputStream(fichero2)
        ){
            for(Producto p : lista){
                escritor1.write(p.getReferencia() + "/" + p.getNombre() + "/" + p.getDescripcion() +
                        "/" + p.getTipo() +"\n");
                escritor2.writeInt(p.getCantidad());
                escritor2.writeDouble(p.getPrecio());
                escritor2.writeInt(p.getDescuento());
                escritor2.writeInt(p.getIva());
                escritor2.writeBoolean(p.isAplicarDto());
            }

        }catch(IOException e){
            System.out.println("Error al guardar el fichero" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String crearCodigoReferencia(){
        Scanner sc = new Scanner(System.in);
        String codigoReferencia = "";

        Pattern patron = Pattern.compile("[0-9]{3}");
        Matcher match;

        do{
            codigoReferencia = sc.nextLine();
            match = patron.matcher(codigoReferencia);
        }while(!match.matches());

        codigoReferencia = "ref-" + codigoReferencia;

        return codigoReferencia;
    }

    public static boolean validarCodigoReferencia(String referencia, LinkedList<Producto> listado){
        boolean encontrado = false;

        for(Producto p : listado){
            if(p.getReferencia().equals(referencia)){
                encontrado = true;
            }
        }

        return encontrado;
    }

    public static boolean crearNuevoArticulo(String referencia, LinkedList<Producto> listado){
        boolean registrado = false, datosCorrectos = false;

        Scanner sc = new Scanner(System.in);

        String nombre, descripcion, tipo;

        int cantidad=0, descuento=0, iva=0;

        double precio=0;

        Producto p;

        nombre = insertarDato("Nombre", 1);
        descripcion = insertarDato("Descripcion", 2);
        tipo = insertarDato("Tipo", 3);

        do{
            System.out.println("Inserte los datos en el siguiente orden: Cantidad, Precio, Descuento e Iva");
            System.out.println();
            sc = new Scanner(System.in);
            try {
                cantidad = sc.nextInt();
                precio = sc.nextDouble();
                descuento = sc.nextInt();
                iva = sc.nextInt();
                datosCorrectos = true;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }while(!datosCorrectos);

        p = new Producto (referencia, nombre, descripcion, tipo, cantidad, precio, descuento, iva);


        registrado = true;
        listado.add(p);


        return registrado;
    }

    public static String insertarDato(String dato, int opcion){
        Pattern patronNombre = Pattern.compile("[A-z a-z0-9]{5,15}");
        Pattern patronDescripcion = Pattern.compile("[A-z a-z0-9]{5,25}");
        Matcher match;

        String opcionTipo;

        int opcionT;

        String datoInsertado = "";

        Scanner sc = new Scanner(System.in);

        if(opcion == 1){

            do{
                System.out.println("Inserte " + dato + "\n");

                datoInsertado = sc.nextLine();

                datoInsertado = datoInsertado.substring(0, 1).toUpperCase() + datoInsertado.substring(1).toLowerCase();

                match = patronNombre.matcher(datoInsertado);
            }while(!match.matches());

        }else if(opcion == 2){

            do{
                System.out.println("Inserte " + dato + "\n");

                datoInsertado = sc.nextLine();

                datoInsertado = datoInsertado.substring(0, 1).toUpperCase() + datoInsertado.substring(1).toLowerCase();

                match = patronDescripcion.matcher(datoInsertado);
            }while(!match.matches());

        }else if(opcion == 3){

            do{
                System.out.println("Elija " + dato + "\n");

                System.out.println(devolverTipos());

                opcionTipo = sc.nextLine();

                opcionT = Integer.parseInt(opcionTipo) - 1;
            }while(opcionT < 0 || opcionT > Tipos.values().length - 1);

            datoInsertado = Tipos.values()[opcionT].getTipo();
        }


        return datoInsertado;
    }


    public static String devolverTipos(){
        String listado = "Estos son los tipo a elegir\n";

        for(int i = 0; i < Tipos.values().length; i++){
            listado += (i+1) + " " + Tipos.values()[i].getTipo() + "\n";
        }
        return listado;
    }

    public static String seleccionarOrdenacionLista(LinkedList<Producto> listado){

        String ordenacion = "No se ha elegido ninguna opción o no era una opción soportada";

        Scanner sc;

        String opcion;


            System.out.println("""
                    1. Referencia
                    2. Nombre
                    3. Tipo
                    """);

            sc = new Scanner(System.in);
            opcion = sc.nextLine();


            //En esta primera voy a probar con el comparator normal, para practicarlo, que hace tiempo que no lo uso
            if(opcion.equals("1")){
                listado.sort(new ComparadorReferencia());

                for(Producto p : listado){
                    ordenacion += "\n" + p;
                }
            }else if(opcion.equals("2")){
                //En esta siguiente, viendo las indicaciones del temario voy a probar las expresiones lambda, aunque no
                //se muy bien como van

                //Estas cosas las he sacado del temario, y habia que volver a ponerlas a prueba otra vez
                listado.sort((p1, p2) -> p1.getNombre().compareTo(p2.getNombre()));
                listado.forEach(System.out::println);
            }else if(opcion.equals("3")){
                //En esta última voy a probar a realizar una comparación secundaria después de la primer. Voy a comparar
                //por tipo, después que lo ordene por orden de referencia

                Comparator<Producto> comparador = (p1, p2) -> p1.getTipo().compareTo(p2.getTipo());
                Comparator<Producto> comparadorSecundario = comparador.thenComparing((p3,p4)-> p3.getReferencia().compareTo(p4.getReferencia()));
                listado.sort(comparadorSecundario);

                listado.forEach(System.out::println);
            }
            //Si bien es cierto, que tal y como tengo estructurado el toString, es un puto cristo verlo como dios manda

        //Después de hacer esto, tengo que comprobar si, después de mostrar un listado de estos, al guardar y salir me modifica
        //tambien la forma en la que se guardan los datos en los ficheros


        return ordenacion;
    }
}