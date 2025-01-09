import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyUtils {

    static DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Formateador de fecha
    public static String formatearFecha(LocalDate fecha) {

        return formateador.format(fecha);
    }

    public static String calcularEdad(LocalDate fecha){
        String edad;
        int anios = LocalDate.now().getYear() - fecha.getYear();
        edad = String.valueOf(anios);
        return edad;
    }

    /*public static int seleccionarTipoMascota(){

    }*/

}
