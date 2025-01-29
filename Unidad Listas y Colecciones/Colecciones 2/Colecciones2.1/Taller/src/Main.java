import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Declaro las variables que creo que necesito
        Scanner teclado;
        String opcion;
        Taller taller = new Taller();

        //Comienzo el menú como de costumbre con el do while
        do{
            //Doy la info necesaria al usuario
            System.out.println("Elija una de las opciones");
            System.out.println("1.- Añadir Coche\n2.- Eliminar Coche\n3.- Salir");

            //Reinicio scanner cada vez que se ejecuta el bucle y solicito el dato de la opcion elegida
            teclado=new Scanner(System.in);
            opcion=teclado.nextLine();
            switch(opcion){
                case "1":
                    //Opcion para añadir un elemento
                    if(taller.anadeElemento()){
                        System.out.println("Coche añadido al taller");
                    }else{
                        System.out.println("Matrícula duplicada, no se ha podido añadir");
                    }
                    break;
                case "2":
                    //Opcion para eliminar un coche
                    if(taller.eliminarCoche(MyUtils.insertarDato("matricula"))){
                        System.out.println("El vehículo seleccionado ha sido eliminado del taller");
                    }else{
                        System.out.println("No se ha podido encontrar un vehículo con esa matrícula");
                    }
                    break;
                case "3":
                    //Opcion de salida
                    System.out.println("Hasta la próxima avería");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }while(!opcion.equals("3"));

        System.out.println(taller.visualizarMatriculas());
        System.out.println();
        System.out.println("****************************");
        System.out.println();
        System.out.println(taller.visualizaCoches());
        System.out.println();
        System.out.println("****************************");
        System.out.println();
        System.out.println(taller.visualizarTaller());
    }
}