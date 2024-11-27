import java.time.LocalDate;

public class VideoDaw {
    private String cif;
    private String direccion;
    private LocalDate fechaAlta;
    private Pelicula[] peliculasRegistradas;
    private Cliente[] clienteRegistrados;

    //Variables contadores para manejar los arrays y para crear los códigos
    private int peliculasTotales = 0, clientesTotales = 0;

    public VideoDaw() {
    }

    public VideoDaw(String cif, String direccion) {
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = LocalDate.now();
        peliculasRegistradas = new Pelicula[10];
        clienteRegistrados = new Cliente[10];
    }

    public String getCif() {
        return cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFechaAlta() {
        return MyUtils.formatearFecha(this.fechaAlta);
    }

    public int getPeliculasTotales() {
        return peliculasTotales;
    }

    public int getClientesTotales() {
        return clientesTotales;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
