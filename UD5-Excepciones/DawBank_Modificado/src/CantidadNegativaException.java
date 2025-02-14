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
//Al final tal y como había programado la solicitud de cantidad nunca va a saltar esta exception, pero la dejo aquí