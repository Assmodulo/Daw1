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

        do{
            sc = new Scanner(System.in);
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

                    break;
                case "4":
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

    public static Producto crearProducto(){
        Scanner sc = new Scanner(System.in);
        Producto p;
        boolean correcto = false;
        System.out.println("Se le van a solicitar los datos del producto");
        System.out.println("Ingrese su codigo");
        String codigo = sc.nextLine();
        System.out.println("Indique el nombre del producto");
        String nombre = sc.nextLine();
        int cantidad = 0;
        double precio = 0;
        do {
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
}