public class FechaPosteriorActualException extends Exception {
    public FechaPosteriorActualException() {

    }

    public String getMessage(){
        return "Fecha Incorrecta, posterior a la fecha Actual";
    }
}
