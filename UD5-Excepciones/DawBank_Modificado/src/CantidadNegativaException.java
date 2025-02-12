public class CantidadNegativaException extends Exception {
    public CantidadNegativaException()
    {

    }

  @Override
  public String getMessage() {
    return "Cantidad Negativa en una operación.";
  }

  @Override
  public String toString() {
    return "Intento de operación incorrecta.";
  }
}
