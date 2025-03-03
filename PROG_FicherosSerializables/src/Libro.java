import java.io.Serializable;

public class Libro implements Serializable {
    private String isbn;
    private String titulo;
    private String autor;
    private String fechaPublicacion;

    public Libro(String isbn, String titulo, String autor, String fechaPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
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
        return fechaPublicacion;
    }

    @Override
    public String toString() {
        return """
                Libro:
                ISBN:$isbn
                Title:$titulo
                Autor:$autor
                Fecha Publicación:$fechaPublicacion
                """;
    }
}
