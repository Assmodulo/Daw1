public class IsbnYaExistenteException extends RuntimeException {
    public IsbnYaExistenteException() {

    }

  @Override
  public String getMessage() {
    return "ISBN Duplicado";
  }

  @Override
  public String toString() {
    return "ISBN existente dentro de nuestra biblioteca";
  }
}
