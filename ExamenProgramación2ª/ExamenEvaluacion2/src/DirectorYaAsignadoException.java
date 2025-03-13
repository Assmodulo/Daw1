public class DirectorYaAsignadoException extends Exception {
    public DirectorYaAsignadoException() {

    }

    public String getMessage(){
      return "Ya hay un director asignado en nuestra Empresa";
    }
}
