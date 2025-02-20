public class MenorDeEdadException extends RuntimeException {
    public MenorDeEdadException() {

    }

    @Override
    public String getMessage() {
        return "El usuario que se intenta registrar es menor de edad";
    }
}
