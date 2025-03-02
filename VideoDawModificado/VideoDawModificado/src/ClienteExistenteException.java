public class ClienteExistenteException extends RuntimeException {
    public ClienteExistenteException() {

    }

    @Override
    public String toString() {
        return "El dni del cliente ya existe. Intentelo de nuevo.";
    }
}
