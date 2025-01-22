import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado;
        String opcion;
        Taller taller = new Taller();

        do{
            System.out.println("Elija una de las opciones");
            System.out.println("1.- Añadir Coche\n2.- Eliminar Coche\n3.- Salir");
            teclado=new Scanner(System.in);
            opcion=teclado.nextLine();
            switch(opcion){
                case "1":
                    if(taller.anadeElemento()){
                        System.out.println("Coche añadido al taller");
                    }else{
                        System.out.println("Matrícula duplicada, no se ha podido añadir");
                    }
                    break;
                case "2":
                    if(taller.eliminarCoche(MyUtils.insertarDato("matricula"))){
                        System.out.println("El vehículo seleccionado ha sido eliminado del taller");
                    }else{
                        System.out.println("No se ha podido encontrar un vehículo con esa matrícula");
                    }
                    break;
                case "3":
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