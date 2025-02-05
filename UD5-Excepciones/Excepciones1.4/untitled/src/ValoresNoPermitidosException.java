public class ValoresNoPermitidosException extends Exception {

    private int nuevoValor;

    public ValoresNoPermitidosException(int nuevoValor) {
        super("ValoresNoPermitidosException");
        this.nuevoValor = nuevoValor;
    }

    @Override
    public String toString() {
        return "ValoresNoPermitidosException: " + nuevoValor;
    }

    @Override
    public String getMessage() {
        return "Este valor es un valor no permitido para este método";
    }
}
