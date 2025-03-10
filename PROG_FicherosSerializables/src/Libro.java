import java.io.Serializable;
import java.time.LocalDate;

public class Libro implements Serializable {
    private static final long serialVersionUID = 8085897440139735073L;
    private String isbn;
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;



    public Libro(String isbn, String titulo, String autor, String fechaPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = Main.tranformarFechar(fechaPublicacion);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion.toString();
    }

    @Override
    public String toString() {
        return "Libro\nIsbn: " + this.getIsbn() +"\nTitulo: " + this.getAutor() + "\nAutor: " + this.getAutor() + "\nFechaPublicacion: " + this.getFechaPublicacion();
    }
}
