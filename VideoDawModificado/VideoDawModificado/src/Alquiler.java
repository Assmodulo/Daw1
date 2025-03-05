import java.time.LocalDateTime;


public class Alquiler {
    private String codSocio;
    private String codProducto;
    private LocalDateTime fechaAlquiler;
    private LocalDateTime fechaDevolucion;

    public Alquiler(String codSocio, String codProducto) {
        this.codSocio = codSocio;
        this.codProducto = codProducto;
        this.fechaAlquiler = LocalDateTime.now();
    }

    public Alquiler(String codSocio, String codProducto, LocalDateTime fechaAlquiler, LocalDateTime fechaDevolucion) {
        this.codSocio = codSocio;
        this.codProducto = codProducto;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setFechaDevolucion(){
        if(LocalDateTime.now().minusHours(48).isAfter(this.fechaAlquiler)){
            this.fechaDevolucion = LocalDateTime.now();
            throw new ExcedidoTiempoAlquilerException();
        }else{
            this.fechaDevolucion = LocalDateTime.now();
        }
    }

    public String getCodSocio() {
        return codSocio;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public LocalDateTime getFechaAlquiler() {
        return fechaAlquiler;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "codSocio='" + this.codSocio + '\'' +
                ", CodProducto='" + this.codProducto + '\'' +
                ", fechaAlquiler=" + this.fechaAlquiler +
                ", fechaDevolucion=" + this.fechaDevolucion +
                '}';
    }
}
