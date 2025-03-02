public class ExcedidoTiempoAlquilerException extends RuntimeException {
    public ExcedidoTiempoAlquilerException() {


    }


  @Override
  public String toString() {
    return "Se ha excedido el tiempo de 48 horas de alquiler. Se le cobrará recargo";
  }
}
