import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        System.out.println("Bienvenido al almacen");

        String direccionGeneral = "./Recursos/";
        String nombreFicheroCsv = "productos.csv";
        String nombreFicheroBinario ="almacen.dat";

        LinkedList<Producto> inventarioAlmacen = new LinkedList<Producto>();

        ArrayList<Producto> datosCsv;


        datosCsv = leerDatosFicheroCsv(direccionGeneral,nombreFicheroCsv);


        int i = 0;
        boolean eof = false;
        try(FileInputStream fichero = new FileInputStream(direccionGeneral + nombreFicheroBinario);
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
        }

        for(Producto p : inventarioAlmacen){
            System.out.println(p);
        }


    }

    public static ArrayList<Producto> leerDatosFicheroCsv(String direccion, String nombreFicheroCsv) {

        ArrayList<Producto> intermedio = new ArrayList<>();
        Producto producto;

        try(FileReader fichero = new FileReader(direccion + nombreFicheroCsv);
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
}