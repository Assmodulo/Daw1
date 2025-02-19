import java.time.LocalDate;

public abstract class Articulo {
    protected String codigo;
    protected String titulo;
    protected LocalDate fechaAlta;
    protected LocalDate fechaBaja;

    public Articulo(String codigo, String titulo, LocalDate fechaAlta, LocalDate fechaBaja) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    @Override
    public String toString() {
        return  "Codigo: " + this.codigo + ", Titulo: " + this.titulo + ",\n" +
                "Fecha de Alta: " + this.fechaAlta + ", Fecha de Baja: " + this.fechaBaja;
    }
}
