import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pelicula {

    //Genero los atributos
    private String cod;
    private String titulo;
    private LocalDate fechaRegistro;
    private LocalDate fechaBaja;
    private LocalDateTime fechaAlquiler;
    private boolean isAlquilada;
    private String genero;


    //Genero un constructor por defecto porque es posible que lo necesite
    public Pelicula() {

    }

    //Genero un constructor con los atributos que creo que voy a necesitar
    //Numero codigo que paso por argumento será el valor de peliculas totales de videoBank + 1
    public Pelicula(String titulo, String genero, int numeroCodigo) {
        this.cod = MyUtils.formatoCodigo(numeroCodigo + 1);
        this.titulo = titulo;
        this.genero = genero;
        this.fechaRegistro = LocalDate.now();
        this.isAlquilada = false;

    }

    //Genero los getters, en principio los de todos los atributos, pues puede que los necesite
    public String getCod() {
        return cod;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFechaRegistro() {

        return MyUtils.formatearFecha(this.fechaRegistro);
    }

    public String getFechaBaja() {

        return MyUtils.formatearFecha(this.fechaBaja);
    }

    public String getFechaAlquiler() {

        return MyUtils.formatearFHora(this.fechaAlquiler);
    }

    public boolean isAlquilada() {
        return isAlquilada;
    }

    public String getGenero() {
        return genero;
    }


    //Genero los setters de los atributos que creo que se podrían modificar
    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public void setFechaAlquiler(LocalDateTime fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public void setAlquilada(boolean alquilada) {
        isAlquilada = alquilada;
    }
}
