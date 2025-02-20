public class FechaPosteriorActualException extends RuntimeException {
    public FechaPosteriorActualException() {

    }

    @Override
    public String getMessage() {
        return "Fecha introducida posterior a la fecha actual. Por lo tanto incorrecta";
    }
}
