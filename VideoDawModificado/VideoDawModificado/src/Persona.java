import java.time.LocalDate;

public abstract class Persona {
    protected String nombre;
    protected String dni;
    protected int direccion;
    protected LocalDate fechaNacimiento;

    public Persona(String nombre, String dni, int direccion, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public int getDireccion() {
        return direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return  "Nombre: " + nombre + ", Dni: " + dni + "\n" +
                "Direccion: " + direccion + ", Fecha de Nacimiento: " + fechaNacimiento;
    }
}
