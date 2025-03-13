public class DniYaExistenteException extends Exception {
    public DniYaExistenteException() {


    }

    public String getMessage(){
        return "El dni ya existe en la base de datos";
    }


}
