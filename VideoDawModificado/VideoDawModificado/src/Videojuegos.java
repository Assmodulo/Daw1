import java.time.LocalDate;
import java.time.LocalDateTime;

public class Videojuegos extends Articulo{
    private boolean isAlquilada = false;
    private LocalDateTime fechaAlquiler;
    private GenerosVidejuegos genero;

    public Videojuegos(String codigo, String titulo, LocalDate fechaAlta, LocalDate fechaBaja, GenerosVidejuegos genero) {
        super(codigo, titulo, fechaAlta, fechaBaja);
        this.genero = genero;
        this.isAlquilada = false;
    }

    public void setFechaAlquiler() {
        this.fechaAlquiler = LocalDateTime.now();
    }

    public void setIsAlquilada() {
        this.isAlquilada = true;
    }

    public String isAlquilada() {
        String alquilada = "";
        if(isAlquilada){
            alquilada = "Alquilado";
        }else{
            alquilada = "Disponible";
        }
        return alquilada;
    }

    public LocalDateTime getFechaAlquiler() {
        return fechaAlquiler;
    }

    public GenerosVidejuegos getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Genero: " + this.genero;
    }
}
