import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Programa de listado y guardado en fichero de productos");

        //Espacio para la declaración de variables que crea que vaya a necesitar
        Scanner sc;
        String opcion="", codigo  ="";

        LinkedList<Producto> productos = new LinkedList<>();
        Producto p;

        boolean eof = false;    //Definimos el booleano que se necesita para certificar el fin del fichero
        try(FileInputStream fichero = new FileInputStream("./resources/Almacen.dat");
            DataInputStream lector = new DataInputStream(fichero)){

            while(!eof){
                String c = lector.readUTF();
                String nom = lector.readUTF();
                int cant = lector.readInt();
                double prec = lector.readDouble();
                p = new Producto(c,nom,cant,prec);
                productos.add(p);

            }
        }catch(EOFException e){
            System.out.println("EOF. Se han leido todas las entradas del fichero");
            System.out.println(e.getMessage());
            eof = true;
        }catch(IOException e){
            System.out.println("Error al leer el fichero");
            System.out.println(e.getMessage());
        }


        do{
            sc = new Scanner(System.in);
            System.out.println(listadoOpciones());
            System.out.println("Ingrese un opcion: ");
            opcion = sc.nextLine();

            switch(opcion){
                case "1":
                    System.out.println("Creando producto");
                    productos.add(crearProducto());
                    System.out.println("Producto Creado");
                    break;
                case "2":
                    System.out.println("Listado de productos Existentes");
                    System.out.println(listadoProductos(productos));
                    break;
                case "3":
                    System.out.println("Eliminación de un producto");
                    System.out.println("Se le mostrara un listado simple con los codigos y nombres de productos");
                    System.out.println(listadoSimple(productos));
                    System.out.println("Inserte el código del producto a eliminar");
                    do {
                        eliminarProductos(productos);
                    } while (!eliminarProductos(productos));
                    if(eliminarProductos(productos)){
                        System.out.println("Producto Eliminado");
                    }else{
                        System.out.println("No se ha encontrado el producto para eliminar");
                    }
                    break;
                case "4":
                    System.out.println("Opción de guardado de listado");
                    try(FileOutputStream fichero = new FileOutputStream("./resources/Almacen.dat",false);
                        DataOutputStream writer = new DataOutputStream(fichero)){
                        for(Producto producto: productos){
                            writer.writeUTF(producto.getCodigo());
                            writer.writeUTF(producto.getNombre());
                            writer.writeInt(producto.getCantidad());
                            writer.writeDouble(producto.getPrecio());
                        }
                    }catch(IOException e){
                        System.out.println("Error al escribir el fichero");
                    }
                    break;
                case "5":
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while(!opcion.equals("5"));

        System.out.println("Hasta la próxima");
    }

    public static String listadoOpciones(){
        return "1. Registrar un Poducto\n2. Listado de Productos\n3. Dar de baja un producto\n4. Guardar datos de productos\n5. Salir";
    }

    public static Producto crearProducto(){
        Scanner sc = new Scanner(System.in);
        Producto p;
        boolean correcto = false;
        System.out.println("Se le van a solicitar los datos del producto");
        System.out.println("Ingrese su codigo");
        String codigo = sc.nextLine().toUpperCase();
        System.out.println("Indique el nombre del producto");
        String nombre = sc.nextLine();
        int cantidad = 0;
        double precio = 0;
        do {
            sc = new Scanner(System.in);
            try {
                System.out.println("Indique la cantidad del producto");
                cantidad = sc.nextInt();
                System.out.println("Indique el precio del producto");
                precio = sc.nextDouble();
                correcto = true;
            } catch (InputMismatchException e) {
                System.out.println("Alguno de los datos se ha introducido de forma incorrecta");
                e.getMessage();
            }
        } while (!correcto);
        p = new Producto(codigo, nombre, cantidad, precio);
        return p;
    }

    public static String listadoProductos(LinkedList<Producto> productos){
        String listado ="";

        for(Producto p : productos){
            listado += p.toString() +"\n";
        }
        return listado;
    }

    public static String listadoSimple(LinkedList<Producto> productos){
        String listado ="";
        for(Producto p : productos){
            listado += p.getCodigo() + ", " + p.getNombre();
        }
        return listado;
    }

    public static boolean eliminarProductos(LinkedList<Producto> productos){
        Scanner sc = new Scanner(System.in);
        String codigo = sc.nextLine().toUpperCase();
        Producto productoEliminar = null;
        boolean encontrado = false;
        for(Producto p : productos){
            if(p.getCodigo().equals(codigo)){
                productoEliminar = p;
                encontrado = true;
            }
        }
        if (productoEliminar != null) {
            productos.remove(productoEliminar);
        }
        return encontrado;
    }

    public static String stringToFile(LinkedList<Producto> productos){
        String listado ="";
        for(Producto p : productos){
            listado += p.getCodigo() + "," + p.getNombre() + "," + p.getCantidad() + "," + p.getPrecio();
        }
        return listado;
    }
}