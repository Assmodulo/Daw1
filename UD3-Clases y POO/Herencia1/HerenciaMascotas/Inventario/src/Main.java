import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Mascotas[] mascotas = new Mascotas[10];
    static int contadorMascotas = 0;



    public static void main(String[] args) {

        String opcion, seleccion;
        Scanner sc = new Scanner(System.in);
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
                        do {
                            System.out.println(mostrarListadoSimple());
                            System.out.println("DE QUE ANIMAL QUIERE CONOCER LOS DATOS");
                            seleccion = sc.nextLine();
                        } while (Integer.parseInt(seleccion) < 1 || Integer.parseInt(seleccion) > mascotas.length);
                        System.out.println(mascotas[Integer.parseInt(seleccion)-1].toString());
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
                    String tipo;
                    if(contadorMascotas == mascotas.length){
                        System.out.println("INVENTARIO DE MASCOTAS LLENO");
                    }else{
                        System.out.println("SELECCIONE EL TIPO DE MASCOTA A AÑADIR");
                        do {
                            for(int i = 0; i < Tipos.values().length; i++){
                                System.out.println((i+1)+ " " + Tipos.values()[i]);
                            }
                            opcion = sc.nextLine();
                        } while (Integer.parseInt(opcion) < 0 || Integer.parseInt(opcion) > Tipos.values().length);
                        tipo = Tipos.values()[Integer.parseInt(opcion) - 1].toString();
                        System.out.println("EL TIPO SELECCIONADO ES " + tipo);
                        switch (tipo){
                            case "PERRO":
                                String raza;
                                do {
                                    sc = new Scanner(System.in);
                                    System.out.println("ELIJA UNA DE ENTRE LAS RAZAS SIGUIENTES");
                                    System.out.println(MyUtils.razaDePerro());
                                    seleccion = sc.nextLine();
                                }while (Integer.parseInt(seleccion) < 1 || Integer.parseInt(seleccion) > (RazasPerro.values().length));
                                raza = MyUtils.seleccionarRaza(Integer.parseInt(seleccion) - 1);
                                System.out.println("INSERTE AHORA EL NOMBRE DE SU ANIMAL");
                                Perro perro = new Perro(sc.nextLine(), MyUtils.insertarFPorTeclado(),raza);
                                almacenarMascota(perro);
                                break;
                            case "GATO":
                                System.out.println("ISERTE AHORA LOS DATOS DE SU GATO, EMPEZANDO POR EL NOMBRE");
                                Gato gato = new Gato(sc.nextLine(), MyUtils.insertarFPorTeclado(), MyUtils.color(), MyUtils.pelaje());
                                almacenarMascota(gato);
                                break;
                            case "LORO":
                                System.out.println("ISERTE AHORA LOS DATOS DE SU LORO, EMPEZANDO POR EL NOMBRE");
                                Loro loro = new Loro(sc.nextLine(), MyUtils.insertarFPorTeclado(), MyUtils.pico(), MyUtils.origen());
                                almacenarMascota(loro);
                                break;
                            case "CANARIO":
                                System.out.println("ISERTE AHORA LOS DATOS DE SU CANARIO, EMPEZANDO POR EL NOMBRE");
                                Canario canario = new Canario(sc.nextLine(), MyUtils.insertarFPorTeclado(), MyUtils.pico(), MyUtils.color());
                                almacenarMascota(canario);
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case "5":
                    if(contadorMascotas > 0){
                        do {
                            System.out.println(mostrarListadoSimple());
                            System.out.println("Elija una de los animales que quiere eliminar");
                            seleccion = sc.nextLine();
                        } while (Integer.parseInt(seleccion) < 1 || Integer.parseInt(seleccion) > mascotas.length);
                        mascotas[Integer.parseInt(seleccion)-1] = null;
                        contadorMascotas--;
                        reordenarAlmacenamiento();
                    }else{
                        System.out.println("NO HAY REGISTRADO NINGÚN ANIMAL EN ESTOS MOMENTOS");
                    }
                    break;
                case "6":
                    if(contadorMascotas > 0){
                        Arrays.fill(mascotas, null);
                        contadorMascotas = 0;
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
        }while(!opcion.equals("7"));
    }

    public static String mostrarListadoSimple(){
        String listadoSimple = "";
        for(int i = 0; i < contadorMascotas; i++){
            listadoSimple += (i+1) + " " + mascotas[i].getClass().toString() + " " + mascotas[i].getNombre() + "\n";
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

    public static void almacenarMascota(Mascotas m){
        if(contadorMascotas == mascotas.length){
            System.out.println("YA NO SE PUEDEN ALMACENAR MÁS MASCOTAS");
        }else{
            mascotas[contadorMascotas] = m;
            contadorMascotas++;
        }
    }

    //Este método le creo sobre todo para resarcirme del examen, que esto no lo conseguí hacer bien
    public static void reordenarAlmacenamiento(){
        Mascotas [] auxiliar = new Mascotas[mascotas.length];
        int j = 0;
        for(int i = 0; i < mascotas.length; i++){
            if(mascotas[i] != null){
                auxiliar[j] = mascotas[i];
                j++;
            }
        }
        Arrays.fill(mascotas, null);
        j = 0;
        for(int i = 0; i < auxiliar.length; i++){
            if(auxiliar[i] != null){
                mascotas[j] = auxiliar[i];
            }
        }


        for(int i = 0; i < mascotas.length; i++) {
            if (mascotas[i] != null) {
                System.out.println(mascotas[i].toString());
            }
        }
    }
}