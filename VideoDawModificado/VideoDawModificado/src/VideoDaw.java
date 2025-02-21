import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class VideoDaw {

    private String cif;
    private String direccion;
    private LocalDate fechaAlta;
    LinkedList<Cliente> socios;
    LinkedList<Articulo> inventarioProductos;
    private int contadorArticulos;
    private int contadorClientes;

    public VideoDaw(String cif, String direccion) throws IOException {
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = LocalDate.now();
        this.socios = new LinkedList<>();
        this.inventarioProductos = new LinkedList<>();
        this.contadorArticulos = 0;
        this.contadorClientes = 0;
        creacionDeFicherosRelacionado(this.cif);
    }

    /**
     * Este constructor solo tiene la función de volver a crear el objeto después de cargar el listado de videoclubs
     * @param cif Un string con el valor del CIF
     * @param direccion Un String con el valor de la dirección
     * @param fechaAlta Un objeto LocalDate con la fecha de Alta del videoclub
     */
    public VideoDaw(String cif, String direccion, LocalDate fechaAlta) {
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.socios = new LinkedList<>();
        this.inventarioProductos = new LinkedList<>();
        this.contadorArticulos = 0;
        this.contadorClientes = 0;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCif() {
        return cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }


    /**
     * Supongo que es el método mostrarInfoVideoclub que indica el enunciado
     * @return String con la información del videoclub
     */
    @Override
    public String toString() {
        return "Establecimiento: \n" +
                "CIF: " + this.cif + ", Direccion: " + this.direccion + "\nFecha de Alta: " + MyUtils.formatearFecha(this.fechaAlta);
    }

    /**
     * Metodo que recibe un String igual a una opción, y según esa opción, completa un listado u otro.
     * Listado sólo de películas, videojuegos o de todos los productos
     * @autor alex
     * @param opcion
     * @return String que muestra un listado de elementos
     */
    public String mostrarProductosRegistradas(String opcion){
        String listado="";

        switch(opcion){
            case "1":
                for(Articulo peliculas : this.inventarioProductos){
                    if(peliculas.getClass() == Pelicula.class){
                        listado += peliculas.toString() + "\n";
                    }
                }
                break;
            case "2":
                for(Articulo videojuegos : this.inventarioProductos){
                    if(videojuegos.getClass() == Videojuegos.class){
                        listado += videojuegos.toString() + "\n";
                    }
                }
                break;
            case "3":
                for(Articulo peliculas : this.inventarioProductos){
                    listado += peliculas.toString() + "\n";
                }
                break;
        }
        return listado;
    }

    /**
     * Metodo para obtener el listado de clientes del videoclub. Solo obtiene la info de los clientes que están activos,
     * es decir, no obtiene los datos de aquellos que están dados de baja
     * @return String: Listado de Clientes activos
     */
    public String mostrarSociosVideoclub(){
        String listado="";
        for(Cliente cliente : this.socios){
            if(cliente.getFechaBaja() != null){
                listado += cliente.toString() + "\n";
            }
        }
        return listado;
    }

    /**
     * Metodo para almacenar un Objeto Cliente en la colección correspondiente
     * @param cliente Un objeto de la clase cliente que se va a almacenar
     */
    public void addCliente(Cliente cliente){
        this.socios.add(cliente);
        this.contadorClientes++;
    }

    /**
     * Metodo para almacenar un Objeto Pelicula o un Objeto Videojuegos en la colección correspondiente
     * @param articulo Un objeto de la clase Pelicula o de la clase Videjuegos
     */
    public void addArticulo(Articulo articulo){
        this.inventarioProductos.add(articulo);
        this.contadorArticulos++;
    }

    /**
     * Método para crear los ficheros de almacenamiento de datos de forma automática al crear un objeto de la clase Videodaw
     * @param cif String con el valor del cif del objeto para poder identificar correctamente a que videoclub pertenece
     */
    private void creacionDeFicherosRelacionado(String cif) throws IOException {
        String ficheroArticulos = cif + "articulos.csv";
        String ficheroClientes = cif + "clientes.csv";

        File fichArticulos = new File("./resources",ficheroArticulos);
        File fichClientes = new File("./resources",ficheroClientes);

        fichArticulos.createNewFile();
        fichClientes.createNewFile();

    }
}
