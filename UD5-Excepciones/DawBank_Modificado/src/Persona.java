import java.time.LocalDate;


public abstract class Persona {
    protected String nombre;
    protected String dni;
    protected LocalDate fechaNacimiento;

    public Persona(String nombre, String dni, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getFechaNacimiento() {
        return MyUtils.formatearFecha(this.fechaNacimiento);
    }

    @Override
    public String toString() {
        return  "Nombre: " + nombre + ", Dni: " + dni + ", Fecha: " + MyUtils.formatearFecha(fechaNacimiento) + "\n";

    }
}
