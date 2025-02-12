public class AvisarHaciendaException extends Exception {
    public AvisarHaciendaException()
    {

    }

    @Override
    public String getMessage() {
        return "Ingreso por encima de los límites normales";
    }

    @Override
    public String toString() {
        return "No se estará haciendo usted demasiado rico?";
    }
}
