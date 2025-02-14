public class CuentaException extends Exception {
    public CuentaException()
    {

    }

    @Override
    public String getMessage() {
        return "Saldo final u operación no permitida";
    }

    @Override
    public String toString() {
        return "Fallo en la operativa de la cuenta";
    }
}
