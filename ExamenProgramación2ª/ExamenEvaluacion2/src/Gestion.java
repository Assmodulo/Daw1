import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestion {

    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    String opcion;

    Empresa e = null;
    Director director = null;

    boolean correcto = false;

    Persona persona = null;


        do {
            try {
                e = creacionEmpresa();
                correcto = true;
            } catch (FechaPosteriorActualException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Alguno de los datos introducidos no es correcto.");
            }catch (FormatoCifIncorrectoException ex){
                System.out.println(ex.getMessage());
            }
        } while (!correcto);

        System.out.println("DAWEMPRESA");

    do{
        System.out.println(opcionesMenu());
        opcion = sc.nextLine();

        switch (opcion){
            case "1":
                System.out.println("Registrar trabajador en la Empresa");
                try {
                    registroDeTrabajador(e);
                } catch (FechaPosteriorActualException ex) {
                    System.out.println(ex.getMessage());
                }catch (DniYaExistenteException ex){
                    System.out.println(ex.getMessage());
                }catch (DirectorYaAsignadoException ex){
                    System.out.println(ex.getMessage());
                }catch (MaximoGerentesException ex){
                    System.out.println(ex.getMessage());
                }
                break;
            case "2":
                System.out.println("Información de la Empresa");
                System.out.println(e.toString());
                break;
            case "3":
                System.out.println("Organización y Jerarquía de la Empresa");
                if(e.mostrarJerarquiaDeLaEmpresa().isEmpty()){
                    System.out.println("No se ha encontrado ningún trabajador en la empresa");
                }else{
                    System.out.println(e.mostrarJerarquiaDeLaEmpresa());
                }
                break;
            case "4":
                System.out.println("Mostrar Información de un Departamento Concreto");
                Departamentos d;
                System.out.println("Seleccione el Departamento que quiere usted Mostrar");
                d = MyUtils.seleccionarDepartamento();
                System.out.println(e.mostrarInforDepartamentoConcreto(d));
                break;
            case "5":
                String dni;
                sc = new Scanner(System.in);
                System.out.println("Eliminar Trabajador de la Empresa");
                if(e.listadoDeTrabajadores().isEmpty()){
                    System.out.println("No hay trabajadores en la Empresa");
                }else{
                    System.out.println(e.listadoDeTrabajadores());
                    System.out.println("Introduzca el dni del trabajador que quiera eliminar");
                    dni = MyUtils.insertarDni();
                    if(e.EliminarTrabajador(dni)){
                        System.out.println("Trabajador eliminado");
                    }else{
                        System.out.println("No se ha podido eliminar ningún trabajador que coincidiese con ese dni");
                    }
                }

                break;
            case "6":
                System.out.println("Agenda del director");
                director = e.retornarDirector();
                if(director != null){
                    modificarAgendaDirectori(e,director);
                }else{
                    System.out.println("No hay un director asignado en la empresa");
                }
                break;
            case "7":
                System.out.println("Saliendo del programa de gestión de la Empresa");
                break;
            default:
                System.out.println("Opcion no contemplada");
                break;
        }

    }while(!opcion.equals("7"));


    }

    static String opcionesMenu(){
        return """
                Elija una de las siguiente opciones
                1.- Registrar Trabajador En la Empresa
                2.- Mostrar Información Completa de la Empresa
                3.- Mostrar el número de Trabajadores y el organigrama de la Empresa
                4.- Mostrar Información de un Departamento
                5.- Eliminar Trabajador de la Empresa
                6.- Agenda del Director
                7.- Salir
                """;
    }

    public static Empresa creacionEmpresa() throws FechaPosteriorActualException, FormatoCifIncorrectoException{

        Scanner sc = new Scanner(System.in);


        String nombre, cif;
        LocalDate fFundacion;

        System.out.println("Indique el nombre de su empresa");
        nombre = sc.nextLine();
        System.out.println("Indique el cif de su empresa con el siguiente formato" +
                "1 letra y 10 dígitos");
        cif = MyUtils.insertarCif();

        fFundacion = MyUtils.insertarFecha();

        return new Empresa(nombre, cif, fFundacion);
    }

    public static void registroDeTrabajador(Empresa e)throws FechaPosteriorActualException, DirectorYaAsignadoException,
            MaximoGerentesException, DniYaExistenteException{
        Scanner sc = new Scanner(System.in);

        String opcion, telefono;

        Trabajador t = null;
        Director d;
        GerenteDep ge;


        System.out.println("Elija que tipo de trabajador desea registrar en la empresa");
        System.out.println( """
                1.- Registrar Director
                2.- Registrar Gerente de un Departamento
                3.- Registrar Trabajador
                """);

        opcion = sc.nextLine();
        switch (opcion){
            case "1":

                if(e.getDirector() == 0){

                    t = insertarDatosTrabajador();
                    System.out.println("Inserte un número de teléfono");
                    telefono = sc.nextLine();

                    d = new Director(t.getNombre(), t.getFechaNacimiento(),t.getDni(), t.getDireccion(),
                            t.getEmail(), t.getSalario(), t.getDepartamento(), telefono);
                    e.registrarEmpleado(d);
                }else{
                    throw new DirectorYaAsignadoException();
                }

                break;
            case "2":

                if(e.getGerenteDireccion() <= 3){
                    t = insertarDatosTrabajador();
                    System.out.println("Inserte un número de teléfono");
                    telefono = sc.nextLine();
                    ge = new GerenteDep(t.getNombre(), t.getFechaNacimiento(),t.getDni(), t.getDireccion(),
                            t.getEmail(), t.getSalario(), t.getDepartamento(), telefono);
                    e.registrarEmpleado(ge);
                }else{
                    throw new MaximoGerentesException();
                }

                break;
            case "3":
                System.out.println("Vaya insertando los datos según se le indique");
                t = insertarDatosTrabajador();
                e.registrarEmpleado(t);
                break;
            default:
                System.out.println("No ha elegido una de las opciones reflejadas");
        }
    }

    public static Trabajador insertarDatosTrabajador() throws FechaPosteriorActualException{
        Scanner sc = new Scanner(System.in);

        Trabajador trabajador = null;

        String nombre, dni, direccion, email;
        double salario;
        LocalDate fechaNacimiento;
        Departamentos departamento;


        System.out.println("Nombre");
        nombre = sc.nextLine();
        System.out.println("Fecha de nacimiento");
        fechaNacimiento = MyUtils.insertarFecha();
        System.out.println("DNI");
        dni = MyUtils.insertarDni();
        System.out.println("Direccion");
        direccion = sc.nextLine();
        System.out.println("Email");
        email = sc.nextLine();
        System.out.println("Departamento");
        departamento = MyUtils.seleccionarDepartamento();
        System.out.println();
        sc = new Scanner(System.in);
        System.out.println("Salario");
        try {
            salario = sc.nextDouble();
            trabajador = new Trabajador(nombre,fechaNacimiento,dni,direccion,email,salario,departamento);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }


        return trabajador;
    }

    public static void modificarAgendaDirectori(Empresa e, Director d){
        Scanner sc = new Scanner(System.in);

        String opcion;

        System.out.println("""
                1.- El director pasa a reunirse ahora
                2.- El director sale fuera de la oficina
                3.- Convocar a toda la Empresa
                """);

        opcion = sc.nextLine();
        switch (opcion){
            case "1":
                d.setEsReunido(true);
                break;
            case "2":
                d.setFueraOficina(true);
                break;
            case "3":

                break;
            default:
               System.out.println("No ha elegido una de las opciones reflejada");
            break;
        }
    }
}