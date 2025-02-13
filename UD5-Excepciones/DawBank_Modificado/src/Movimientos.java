
import java.time.LocalDateTime;

public class Movimientos {

    private static int cont = 0;

    private int idMov;
    private LocalDateTime fecha;
    private String tipo;
    private double cantidad;

    public Movimientos() {

    }

    public Movimientos(String tipo, double cantidad)throws CantidadNegativaException, AvisarHaciendaException {
        this.idMov = cont +1;
        this.fecha = LocalDateTime.now();
        this.tipo = tipo;
        setCantidad(cantidad);

        //Como cada vez que se genera un movimiento el siguiente va a tener una id consecutiva ahora le tengo que
        //indicar que la id debe de aumentar
        Movimientos.cont++;

    }

    public int getIdMov(){

        return idMov;
    }

    public LocalDateTime getFecha(){
        return fecha;
    }

    public String getTipo(){
        return tipo;
    }

    public String getCantidad(){
        return String.valueOf(cantidad);
    }

    private void setCantidad(double cantidad) throws CantidadNegativaException, AvisarHaciendaException{
        if (cantidad <= 0 ){
            throw new CantidadNegativaException();
        }else if (cantidad > 3000){
            throw new AvisarHaciendaException();
        }else{
            this.cantidad = cantidad;
        }
    }

    @Override
    public String toString() {
        return "ID Mov: " + this.idMov + ", Fecha/Hora: " + this.fecha + ", Tipo: " + this.tipo + ", Cantidad: " + this.cantidad;
    }
}
