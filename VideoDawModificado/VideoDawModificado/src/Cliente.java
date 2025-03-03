import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class Cliente extends Persona implements Serializable {

    private String codSocio;
    private LocalDate fechaBaja;

    public Cliente(String nombre, String dni, String direccion, LocalDate fechaNacimiento, String codSocio)throws IOException {
        super(nombre, dni, direccion, fechaNacimiento);
        this.codSocio = codSocio;
        this.fechaBaja = null;
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


    /**
     * Metodo para devolver un listado de los articulos que han sido o están siendo alquilados
     * @return String con un listado de objetos de tipo Alquiler
     */


    @Override
    public String toString() {
        return "Código de Socio: " + this.codSocio + "\n" + super.toString() + ",Estado: " + this.estadoCliente();
    }

}
