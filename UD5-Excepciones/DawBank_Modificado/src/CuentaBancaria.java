import java.util.LinkedList;

public class CuentaBancaria {

    //Defino los atributos de la clase
    private String iban;
    private Cliente titular;
    private double saldo;
    private LinkedList<Movimientos> movimientos;


    //Defino un constructor por defecto para poder declarar variables del tipo CuentaBancaria
    public CuentaBancaria() {

    }

    //Constructor completo de la clase CuentaBancaria. Solo necesita dos parámetros, el resto se definen al crear el
    //objeto
    public CuentaBancaria(String iban, Cliente titular) {
        this.iban = iban;
        this.titular = titular;
        //El saldo inicial siempre va a ser cero
        this.saldo = 0;
        this.movimientos = new LinkedList<>();
    }


    //Defino los getters que me hacen falta, los setters no porque voy a modificar todo mediante métodos
    public String getIban() {
        return iban;
    }

    public String getTitular() {
        return titular.toString();
    }

    public double getSaldo() {
        return saldo;
    }

    //Este método sirve para aumentar el valor de saldo cuando hay un ingreso
    public void incrementarSaldo(double saldo) {
        this.saldo += saldo;
    }

    //Este método actualiza el valor de saldo cuando hay un reintegro
    public void reducirSaldo(double saldo) throws CuentaException {
        if((this.saldo - saldo) <= -49){
            throw new CuentaException();
        }else{
            this.saldo -= saldo;
        }


    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "iban='" + iban + '\'' +
                ", titular=" + this.titular.toString() +
                ", saldo=" + saldo +
                ", movimientos=" + movimientos +
                '}';
    }

    //Metodo para almacenar los movimientos en el array
    public void almacenarMovimientos(Movimientos m){
        this.movimientos.add(m);
    }

    public String recorrerMovimientos(){
        String listadoMovimientos = "";
        for(Movimientos m : movimientos){
            listadoMovimientos += m.toString() + "\n";
        }
        return listadoMovimientos;
    }
}



