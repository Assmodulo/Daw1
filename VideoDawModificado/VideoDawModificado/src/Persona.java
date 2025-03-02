import java.time.LocalDate;

public abstract class Persona{
    protected String nombre;
    protected String dni;
    protected String direccion;
    protected LocalDate fechaNacimiento;

    public Persona(String nombre, String dni, String direccion, LocalDate fechaNacimiento){
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return  "Nombre: " + this.nombre + ", Dni: " + this.dni + "\n" +
                "Direccion: " + this.direccion + ", Fecha de Nacimiento: " + MyUtils.formatearFecha(this.fechaNacimiento);
    }
}
