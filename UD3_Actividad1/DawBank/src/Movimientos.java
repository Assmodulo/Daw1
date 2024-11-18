import java.time.LocalDateTime;

public class Movimientos {

    private static int cont = 1;

    private int idMov;
    private String fecha;
    private String tipo;
    private double cantidad;

    public Movimientos() {

    }

    public Movimientos(String tipo, double cantidad) {
        this.idMov = cont;
        this.fecha = LocalDateTime.now().toString();
        this.tipo = tipo;
        this.cantidad = cantidad;

        //Como cada vez que se genera un movimiento el siguiente va a tener una id consecutiva ahora le tengo que
        //indicar que la id debe de aumentar
        cont++;

    }

    public String getIdMov(){
        return String.valueOf(idMov);
    }

    public String getFecha(){
        return fecha;
    }

    public String getTipo(){
        return tipo;
    }

    public String getCantidad(){
        return String.valueOf(cantidad);
    }

    public String infoMov(){
        String info = "Mov: " + this.getIdMov() + "\n" +
                "Fecha: " + this.getFecha() + "\n" +
                "Tipo: " + this.getTipo() + "\n" +
                "Cantidad: " + this.getCantidad();

        return info;
    }
}
