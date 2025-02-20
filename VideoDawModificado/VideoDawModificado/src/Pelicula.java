import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pelicula extends Articulo{
    private boolean isAlquilada = false;
    private LocalDateTime fechaAlquiler;
    private GenerosPeliculas genero;

    public Pelicula(String codigo, String titulo, LocalDate fechaAlta, LocalDate fechaBaja, GenerosPeliculas genero) {
        super(codigo, titulo, fechaAlta, fechaBaja);
        this.genero = genero;
        this.isAlquilada = false;
    }

    public void setFechaAlquiler() {
        this.fechaAlquiler = LocalDateTime.now();
    }

    public void setIsAlquilada(boolean alquilada) {
        this.isAlquilada = alquilada;
    }


    /**
     * Este método es para que al mostrar por pantalla el estado de un videojuego, en vez de mostrar true o false
     * nos indique el estado
     * @return String que indica el estado, alquilado o disponible
     */
    public String isAlquilada() {
        String alquilada = "";
        if(this.isAlquilada){
            alquilada = "Alquilada";
        }else{
            alquilada = "Disponible";
        }
        return alquilada;
    }

    public LocalDateTime getFechaAlquiler() {
        return fechaAlquiler;
    }

    public GenerosPeliculas getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Genero: " + this.genero + ", Estado: " + this.isAlquilada();
    }
}
