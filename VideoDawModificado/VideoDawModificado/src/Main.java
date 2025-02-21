import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
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
        LinkedList<VideoDaw> listadovideoclubs = new LinkedList<VideoDaw>();

        //Antes de empezar a trabajar con ninguna otra cosa debo de asegurarme de que hay creado al menos un videoclub
        // o de que hay uno seleccionado

        //Defino las diferentes variables para los diferentes objetos
        Pelicula p;
        Videojuegos v;
        Cliente c;
        Alquiler a;

        //Defino una variabla para almacenar la opción elegida
        String opcion ="";
        //Defino una variable numérica para los casos en los que me venga mejor un int para elegir una opcion
        int opcionNumerica;

        System.out.println("Bienvenidos a la maravillosa franquicia de videoclubs VideoDaw");


    }

    /**
     * Metodo que nos retorna las posibles opciones que podemos elegir
     * @return String
     */
    static String opcionesMenu(){
        return "Elija una de las siguiente opciones\n1.- Crear o registrar Videoclub en la franquicia\n2.- Seleccionar " +
                "videoclub\n3.- Registrar articulo en videoclub\n4.- Crear y registrar clientes en el videoclub\n" +
                "5.- Alquilar Producto\n6.- Devolver Producto\n7.- Dar de baja Cliente\n8.- Dar de baja Artículo\n" +
                "8.- Salir";
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
        BufferedReader lector = new BufferedReader(ficheroVideoclubs);){
            String linea = lector.readLine();
            while (linea != null) {
                String[] datos = linea.split(",");
                v = new VideoDaw(datos[0],datos[1],LocalDate.parse(datos[2]));
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
}