public class EdadException extends Exception {

    private int edad;
    public EdadException(int edad)
    {
        super("Error en la edad");
        this.edad = edad;
    }

    @Override
    public String getMessage() {
      return "Error en la edad introducida: Menor de cero (edad <=0)" ;
    }
}
