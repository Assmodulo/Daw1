import java.time.LocalDate;
import java.time.LocalDateTime;

public class Videojuegos extends Articulo{
    private boolean isAlquilada = false;
    private LocalDateTime fechaAlquiler;
    private GenerosVidejuegos genero;

    public Videojuegos(String codigo, String titulo, GenerosVidejuegos genero) {
        super(codigo, titulo);
        this.genero = genero;
        this.isAlquilada = false;
        this.fechaAlta = LocalDate.now();
        this.fechaBaja = null;
    }

    /**
     * Constructor creado para volver a generar los objetos al cargarse desde el fichero correspondiente
     * @param codigo String generado automáticamente
     * @param titulo String con el titulo de la película
     * @param genero Elemento de un Enum con los posibles generos de una película
     * @param fechaAlata Objeto LocalDate que indica la fecha de alta de un videoclub
     * @param fechaBaja Objeto LocalDate que indica la fecha de baja de un videoclub
     * @param fechaAlquiler Objeto LocalDateTime para indicar las ultima fecha en la que se ha alquilado un articulo
     * @param isAlquilada Boolean que indica si una película está alquilada o no
     */
    public Videojuegos(String codigo, String titulo, GenerosVidejuegos genero, LocalDate fechaAlata, LocalDate fechaBaja,
                    LocalDateTime fechaAlquiler,boolean isAlquilada) {
        super(codigo, titulo);
        this.codigo = codigo;
        this.titulo = titulo;
        this.genero = genero;
        this.fechaAlta = fechaAlata;
        this.fechaBaja = fechaBaja;
        this.fechaAlquiler = fechaAlquiler;
        this.isAlquilada = isAlquilada;

    }

    public void setFechaAlquiler(LocalDateTime fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
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
        return super.toString() + "\n Genero: " + this.genero + "Estado: " + this.isAlquilada();
    }
}
