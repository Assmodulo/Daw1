public class ValoresNoPertmitidosNegativosException extends RuntimeException {

    int valor;
    public ValoresNoPertmitidosNegativosException(int nuevoValor) {

      super("Valor no permitido negativo");
      this.valor = nuevoValor;
    }

    @Override
    public String toString() {
      return "ValoresNoPertmitidosNegativosException: " + valor;
    }

    @Override
    public String getMessage() {
      return "Este valor no está permitido " + valor;
    }
}
