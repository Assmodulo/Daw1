import java.time.LocalDate;

public abstract class Articulo {
    protected String codigo;
    protected String titulo;
    protected LocalDate fechaAlta;
    protected LocalDate fechaBaja;

    public Articulo(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.fechaAlta = LocalDate.now();
        this.fechaBaja = null;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
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
                "Fecha de Alta: " + MyUtils.formatearFecha(this.fechaAlta) + ", Fecha de Baja: " + this.fechaBaja;
    }
}
