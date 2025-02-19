import java.time.LocalDate;

public abstract class Articulo {
    protected String codigo;
    protected String titulo;
    protected LocalDate fechaAlta;
    protected LocalDate fechaBaja;

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
