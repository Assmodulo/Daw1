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



    //Genero un constructor con los atributos que creo que voy a necesitar
    //Numero codigo que paso por argumento será el valor de peliculas totales de videoBank + 1
    public Pelicula(String titulo, String genero, int numeroCodigo) {
        this.cod = MyUtils.formatoCodigo("P", numeroCodigo + 1);
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

    public LocalDate getFechaRegistro() {

        return this.fechaRegistro;
    }

    public LocalDate getFechaBaja() {

        return this.fechaBaja;
    }

    public LocalDateTime getFechaAlquiler() {

        return this.fechaAlquiler;
    }

    public boolean isAlquilada() {
        return isAlquilada;
    }

    public String getGenero() {
        return genero;
    }


    //Genero los setters de los atributos que creo que se podrían modificar
    public void setFechaBaja() {
        this.fechaBaja = LocalDate.now();
    }

    public void setFechaAlquiler(LocalDateTime fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public void setAlquilada(boolean alquilada) {

        this.isAlquilada = alquilada;
    }

    public String mostrarInforPelicula(){
        return "Codigo: " + this.cod + " Titulo : " + this.titulo + " Genero : " + this.genero +
                " Fecha Registro : " + MyUtils.formatearFecha(this.fechaRegistro) + " Alquilada: " + this.isAlquilada();
    }
}
