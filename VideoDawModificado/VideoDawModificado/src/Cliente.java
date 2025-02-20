import java.time.LocalDate;
import java.util.LinkedList;

public class Cliente extends Persona {

    private String codSocio;
    private LocalDate fechaBaja;
    private LinkedList <Articulo> articulosAlquilados = new LinkedList<Articulo>();

    public Cliente(String nombre, String dni, int direccion, LocalDate fechaNacimiento) {
        super(nombre, dni, direccion, fechaNacimiento);
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = LocalDate.now();
    }

    public String getCodSocio() {
        return codSocio;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    //Si necesito crear un método al que le tenga que pasar una linkedlist como parámetro me vale
    public LinkedList<Articulo> getArticulosAlquilados() {
        return articulosAlquilados;
    }

    /**
     * Metodo para devolver un listado de los articulos que han sido o están siendo alquilados
     * @return String con un listado de objetos de tipo Alquiler
     */
    public String listadoAlquileres(){
        String listado = "";
        for(Articulo art: articulosAlquilados){
            listado += art.toString() + "\n";
        }
        return listado;
    }

    @Override
    public String toString() {
        return "Código de Socio: " + this.codSocio + "\n" + super.toString();
    }
}
