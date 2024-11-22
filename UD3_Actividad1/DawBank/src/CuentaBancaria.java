public class CuentaBancaria {

    //Defino los atributos de la clase
    private String iban;
    private String titular;
    private double saldo;
    private Movimientos[] movimientos;

    //Defino una variable que me sirve para llevar la cuenta de los elementos totales almacenados en el array
    //movimientos. Antes era static pero voy a probar con public, que es propia de cada cuenta creada, que lleva la
    //cuenta de los movimientos de cada cuenta
    public int elementosTotales = 0;

    //Defino un constructor por defecto para poder declarar variables del tipo CuentaBancaria
    public CuentaBancaria() {

    }

    //Constructor completo de la clase CuentaBancaria. Solo necesita dos parámetros, el resto se definen al crear el
    //objeto
    public CuentaBancaria(String iban, String titular) {
        this.iban = iban;
        this.titular = titular;
        //El saldo inicial siempre va a ser cero
        this.saldo = 0;
        /*En principio cree el array de 100, pero para probar la redimensión del array en caso de que llegue al máximo
        Lo defino de 10
         */
        this.movimientos = new Movimientos[10];
        this.elementosTotales = 0;
    }


    //Defino los getters que me hacen falta, los setters no porque voy a modificar todo mediante métodos
    public String getIban() {
        return iban;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    //Este método sirve para aumentar el valor de saldo cuando hay un ingreso
    public void incrementarSaldo(double saldo) {
        this.saldo += saldo;
    }

    //Este método actualiza el valor de saldo cuando hay un reintegro
    public void reducirSaldo(double saldo) {
        this.saldo -= saldo;
    }

    private void aumentarElementos() {
        this.elementosTotales++;
    }

    //Este es el equivalente al método toString
    public String mostrarInformacion(){
        System.out.println("ESTOS SON LOS DATOS DE LA CUENTA BANCARIA EN CUESTIÓN");

        String info = "Nº Cuenta : " + getIban() + "\n"+
                "Titular: " + getTitular() + "\n" +
                "Saldo: " + getSaldo() + "\n";


        return info;
    }

    //Metodo para almacenar los movimientos en el array
    public void almacenarMovimientos(Movimientos m){
        this.checkArrayLength(this.elementosTotales);
        this.movimientos[this.elementosTotales] = m;
        this.aumentarElementos();
    }

    public void recorrerMovimientos(Movimientos m){
        for(int i = 0; i < this.elementosTotales; i++){
            m = this.movimientos[i];
            System.out.println(m.infoMov());
        }
    }

    //Este método lo he creado para checkear el número de elementos totales introducidos
    //Si ese número es igual a la length del array movimientos, crear un array auxiliar, copia los datos a ese array
    //Despues redimensiona el array existente a su longitud actual +10 y luego vuelca el contenido de auxiliar
    //de nuevo en el array original redimensionado
    private void checkArrayLength(int elementos){
        if(elementos >= this.movimientos.length - 1){
            Movimientos[] movimientosAuxiliares = new Movimientos[this.movimientos.length];
            System.arraycopy(this.movimientos, 0, movimientosAuxiliares, 0, movimientos.length);
            this.movimientos = new Movimientos[this.movimientos.length + 10];
            System.arraycopy(movimientosAuxiliares, 0, this.movimientos, 0, movimientosAuxiliares.length);
        }
    }
}
