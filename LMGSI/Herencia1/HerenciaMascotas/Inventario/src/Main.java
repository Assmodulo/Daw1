import java.util.Scanner;

public class Main {
    static Mascotas[] mascotas = new Mascotas[100];
    static int contadorMascotas = 0;
    public static void main(String[] args) {

        String opcion;
        Scanner sc = new Scanner(System.in);
        Perro perro;
        Gato gato;
        Loro loro;
        Canario canario;
        System.out.println("BIENVENIDOS A NUESTRA TIENDA DE MASCOTAS");

        do{
            System.out.println("1.-MOSTRAR LISTA DE ANIMALES\n2.-MOSTRAR DATOS COMPLETOS DE UN ANIMAL\n3.-MOSTRAR TODOS LOS DATOS" +
                    " DE TODOS LO ANIMALES\n4.-INSERTAR ANIMALES EN EL INVENTARIO\n5.-ELIMINAR ANIMALES DEL INVENTARIO\n6.-VACIAR INVENTARIO" +
                    "\n7.-SALIR");
            opcion = sc.nextLine();
            switch(opcion){
                case "1":
                    if(contadorMascotas > 0){
                       System.out.println(mostrarListadoSimple());
                    }else{
                        System.out.println("NO HAY REGISTRADO NINGÚN ANIMAL EN ESTOS MOMENTOS");
                    }
                    break;
                case "2":
                    if(contadorMascotas > 0){

                    }else{
                        System.out.println("NO HAY REGISTRADO NINGÚN ANIMAL EN ESTOS MOMENTOS");
                    }
                    break;
                case "3":
                    if(contadorMascotas > 0){
                        System.out.println(mostrarListadoCompleto());
                    }else{
                        System.out.println("NO HAY REGISTRADO NINGÚN ANIMAL EN ESTOS MOMENTOS");
                    }
                    break;
                case "4":
                    break;
                case "5":
                    if(contadorMascotas > 0){

                    }else{
                        System.out.println("NO HAY REGISTRADO NINGÚN ANIMAL EN ESTOS MOMENTOS");
                    }
                    break;
                case "6":
                    if(contadorMascotas > 0){

                    }else{
                        System.out.println("NO HAY REGISTRADO NINGÚN ANIMAL EN ESTOS MOMENTOS");
                    }
                    break;
                case "7":
                    System.out.println("NOS VEMOS EN OTRO MOMENTO");
                    break;
                default:
                    System.out.println("OPCION INCORRECTA");
                    break;
            }
        }while(!opcion.equals("6"));
    }

    public static String mostrarListadoSimple(){
        String listadoSimple = "";
        for(int i = 0; i < contadorMascotas; i++){
            listadoSimple += (i+1) + " " + mascotas[i].getClass() + " " + mascotas[i].getNombre() + "\n";
        }
        return listadoSimple;
    }

    public static String mostrarListadoCompleto(){
        String listadoCompleto = "";
        for(int i = 0; i < contadorMascotas; i++){
            listadoCompleto += (i+1) + " " + mascotas[i].toString() + "\n";
        }
        return listadoCompleto;
    }
}