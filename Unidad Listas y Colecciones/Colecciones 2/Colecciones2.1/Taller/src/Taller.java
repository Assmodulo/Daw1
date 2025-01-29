import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Taller {
        private Map<String, Coche> coches;

    public Taller(){
        coches = new HashMap<>();
    }
    public boolean anadeElemento(){
        boolean add = false;
        String matricula, marca, color;
        do {
            matricula = MyUtils.insertarDato("matricula").toUpperCase();
        } while (!comprobarFormatoMatricula(matricula));
        if(!validarMatricula(matricula)){
            marca = MyUtils.insertarDato("marca");
            color = MyUtils.insertarDato("color");
            coches.put(matricula, new Coche(marca.toUpperCase(), color.toUpperCase()));
            add = true;
        }
        return add;
    }

    private boolean validarMatricula(String matricula){
        boolean existe = false;
        for(String coche : coches.keySet()){
            if(coche.equals(matricula)){
                existe = true;
            }
        }
        return existe;
    }

    private boolean comprobarFormatoMatricula(String matricula){
        boolean correcto = false;
        Pattern patron = Pattern.compile("[0-9]{4}[A-Z]{3}");
        Matcher matcher = patron.matcher(matricula);
        if(matcher.matches()){
            correcto = true;
        }
        return correcto;
    }

    public boolean eliminarCoche(String matricula){
        boolean eliminado = false;
        matricula = matricula.toUpperCase();
        if (comprobarFormatoMatricula(matricula)) {
            if(validarMatricula(matricula)){
                coches.remove(matricula);
                eliminado = true;
            }
        }
        return eliminado;
    }

    public String visualizarMatriculas(){
        Set<String> matriculas = coches.keySet();
        String listado = "";
        for(String matricula : matriculas){
            listado += matricula + "\n";
        }
        return listado;
    }

    public String visualizaCoches(){
        String listado = "";
        for(Coche coche : coches.values()){
            listado += coche.toString() + "\n";
        }
        return listado;
    }

    public String visualizarTaller(){
        String listado = coches.entrySet().toString();
        return listado;
    }
}
