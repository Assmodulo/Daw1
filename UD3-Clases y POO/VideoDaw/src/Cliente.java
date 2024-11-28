import java.time.LocalDate;

public class Cliente {
    private String dni;
    private String nombre;
    private String numSocio;
    private String direccion;
    private LocalDate fechaNacimiento;
    private LocalDate fechaBaja;
    private Pelicula[] peliculas = new Pelicula[10];

    private static int cantidadPeliculas = 0;

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String direccion, LocalDate fechaNacimiento, int numSocio) {
        this.numSocio = MyUtils.formatoCodigo("C", numSocio + 1);
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumSocio() {
        return numSocio;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
}
