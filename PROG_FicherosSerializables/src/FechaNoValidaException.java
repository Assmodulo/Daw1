public class FechaNoValidaException extends RuntimeException {
    public FechaNoValidaException() {

    }

    @Override
    public String getMessage() {
        return "Fecha Incorrecta";
    }

    @Override
    public String toString() {
        return "La fecha no puede ser posterior a la fecha actual";
    }
}
