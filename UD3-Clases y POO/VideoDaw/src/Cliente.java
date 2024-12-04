import java.time.LocalDate;

public class Cliente {
    private String dni;
    private String nombre;
    private String numSocio;
    private String direccion;
    private LocalDate fechaNacimiento;
    private LocalDate fechaBaja;
    private Pelicula[] peliculasAlquiladas;

    private static int cantidadPeliculas = 0;

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String direccion, LocalDate fechaNacimiento, int numSocio) {
        this.numSocio = MyUtils.formatoCodigo("C", numSocio + 1);
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.peliculasAlquiladas = new Pelicula[10];
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

    //En principio he considerado que la dirección de un cliente si podría cambiar
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaBaja() {
        this.fechaBaja = LocalDate.now();
    }

    public String mostrarDatosCliente(){
        return "Nombre: " + this.getNombre() + "Num.Socio: " + this.getNumSocio() + "Dni: " + this.getDni() +
                "Dirección: " + this.getDireccion() + "F. Nacimiento: " + this.getFechaNacimiento();
    }

    public void crearAlquiler(Pelicula p){
        this.peliculasAlquiladas[cantidadPeliculas] = p;
        cantidadPeliculas++;
    }
}
