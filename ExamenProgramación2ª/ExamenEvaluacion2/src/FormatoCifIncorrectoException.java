public class FormatoCifIncorrectoException extends Exception {
  public FormatoCifIncorrectoException() {

  }

  public String getMessage(){
    return "El formato de cif no es correcto";
  }
}
