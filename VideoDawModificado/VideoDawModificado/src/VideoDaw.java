import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class VideoDaw {

    private String cif;
    private String direccion;
    private LocalDate fechaAlta;
    LinkedList<Cliente> socios = new LinkedList<>();
    LinkedList<Articulo> inventarioProductos = new LinkedList<>();
    private int contadorArticulos = 0;
    private int contadorClientes = 0;

    public VideoDaw(String cif, String direccion, LocalDate fechaAlta) {
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;

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
                "CIF: " + this.cif + ", Direccion: " + this.direccion + "\nFecha de Alta: " + this.fechaAlta;
    }

    /**
     * Metodo que recibe un String igual a una opción, y según esa opción, completa un listado u otro.
     * Listado sólo de películas, videojuegos o de todos los productos
     * @autor alex
     * @param opcion
     * @return String que muestra un listado de elementos
     */
    public String mostrarPeliculasRegistradas(String opcion){
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
}
