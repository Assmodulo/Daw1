import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

public class Cliente extends Persona {

    private String codSocio;
    private LocalDate fechaBaja;
    private LinkedList <Alquiler> articulosAlquilados = new LinkedList<Alquiler>();

    public Cliente(String nombre, String dni, String direccion, LocalDate fechaNacimiento, String codSocio)throws IOException {
        super(nombre, dni, direccion, fechaNacimiento);
        this.codSocio = codSocio;
        this.fechaBaja = null;
        creacionFicheroRelacionado(this.codSocio);
    }

    public Cliente(String nombre, String dni, String direccion, LocalDate fechaNacimiento, String codSocio,
                   LocalDate fechaBaja) {
        super(nombre, dni, direccion, fechaNacimiento);
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.codSocio = codSocio;
        this.fechaBaja = fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = LocalDate.now();
    }

    public String getCodSocio() {
        return codSocio;
    }

    public String estadoCliente() {
        if(this.fechaBaja == null) {
            return "Activo";
        }else{
            return "Dado de Baja";
        }
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    //Si necesito crear un método al que le tenga que pasar una linkedlist como parámetro me vale
    public LinkedList<Alquiler> getArticulosAlquilados() {
        return articulosAlquilados;
    }

    public void almacenarAlquileres(Alquiler alquiler) {
        this.articulosAlquilados.add(alquiler);
        escribirArchivoAlquileres();
    }

    /**
     * Metodo para devolver un listado de los articulos que han sido o están siendo alquilados
     * @return String con un listado de objetos de tipo Alquiler
     */
    public String listadoAlquileres(){
        String listado = "";
        for(Alquiler alquiler: articulosAlquilados){
            listado += alquiler.toString() + "\n";
        }
        return listado;
    }

    @Override
    public String toString() {
        return "Código de Socio: " + this.codSocio + "\n" + super.toString() + ",Estado: " + this.estadoCliente();
    }

    private void creacionFicheroRelacionado(String codSocio) throws IOException {
        String fichAlquileresCliente = codSocio + "_alquileresSocio.csv";

        File fichCliente = new File("./resources/" + this.codSocio+"/"+fichAlquileresCliente);
        fichCliente.createNewFile();
    }


    public void actualizarDatosAlquiler(String codigoArticulo){
        for(Alquiler alquiler: articulosAlquilados){
            if(alquiler.getCodProducto().equals(codigoArticulo)){
                alquiler.setFechaDevolucion();
            }
        }
        escribirArchivoAlquileres();
    }

    public void escribirArchivoAlquileres(){
        try(FileWriter fichero = new FileWriter("./resources/" + this.codSocio,false);
            BufferedWriter escritor = new BufferedWriter(fichero)){
            for(Alquiler alquiler: articulosAlquilados){
                escritor.write(alquiler.getCodProducto() + "," + alquiler.getCodSocio() + "," +
                        MyUtils.formatearFechaHora(alquiler.getFechaAlquiler()) + "," +
                        MyUtils.formatearFechaHora(alquiler.getFechaDevolucion()));
            }
        }catch(IOException e){
            System.out.println("Error al escribir al archivo");
        }
    }
}
