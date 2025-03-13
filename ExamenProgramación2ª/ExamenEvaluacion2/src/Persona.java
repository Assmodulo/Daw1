import java.time.LocalDate;

public class Persona {

    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected String dni;
    protected String direccion;

    public Persona(String nombre, LocalDate fechaNacimiento, String dni, String direccion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
