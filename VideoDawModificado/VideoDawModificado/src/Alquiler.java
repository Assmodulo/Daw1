import java.time.LocalDateTime;

public class Alquiler {
    private String codSocio;
    private String CodProducto;
    private LocalDateTime fechaAlquiler;
    private LocalDateTime fechaDevolucion;

    public Alquiler(String codSocio, String codProducto) {
        this.codSocio = codSocio;
        this.CodProducto = codProducto;
        this.fechaAlquiler = LocalDateTime.now();
    }

    public void setFechaDevolucion(){
        this.fechaDevolucion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "codSocio='" + this.codSocio + '\'' +
                ", CodProducto='" + this.CodProducto + '\'' +
                ", fechaAlquiler=" + this.fechaAlquiler +
                ", fechaDevolucion=" + this.fechaDevolucion +
                '}';
    }
}
