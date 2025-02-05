public class LongitudNombreException extends Exception {

    private String nombre;
    public LongitudNombreException(String nombre)
    {
        super("Error del nombre");
        this.nombre = nombre;
    }

  @Override
  public String getMessage() {
    return "El nombre debe de ser de 3 caracteres o más";
  }

  @Override
  public String toString() {
      return "Error nombre. Poca longitud";
  }
}
