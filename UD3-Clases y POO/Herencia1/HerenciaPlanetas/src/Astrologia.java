import java.util.Scanner;

public class Astrologia{

    public static void main (String[] args){
        Planeta[] planetas = new Planeta [10];
        int contadorPlanetas = 0;
        Planeta p;
        Satelites s;
        String opcion;
        Scanner sc;
        String nombre;
        double masa, rotacionSoEje, tempMedia, gravedad, radioEcuatorial, distanciaSol, orbita,
                distanciaPlaneta, orbitaPlanetaria;
        boolean tSatelite =false;

        System.out.println("ESTE PROGRAMA LE SIRVE PARA ALMACENAR DATOS DE PLANETAS Y SUS SATÉLITE");

        //No voy a extenderme mucho en el programa, así que voy a hacerlo de una forma sencilla

        //Creo un menú básico para navegar por el programa

        do {
            System.out.println("Ingrese la opcion:\n1. Insertar Planeta\n2. Insertar Satélite\n" +
                    "3. Mostrar info Planeta\n4. Mostrar info Satelite\n5. Salir ");
            sc = new Scanner(System.in);
            opcion = sc.nextLine();
            switch (opcion){
                case "1":
                    System.out.println("Ingrese los datos del planeta según se le vaya solicitando");
                    System.out.println("Nombre");
                    nombre = sc.nextLine();
                    System.out.println("Masa");
                    masa = sc.nextDouble();
                    System.out.println("Rotación sobre su eje");
                    rotacionSoEje = sc.nextDouble();
                    System.out.println("Temperatura Media");
                    tempMedia = sc.nextDouble();
                    System.out.println("Gravedad");
                    gravedad = sc.nextDouble();
                    System.out.println("Radio Ecuatorial");
                    radioEcuatorial = sc.nextDouble();
                    System.out.println("Distancia al Sol");
                    distanciaSol = sc.nextDouble();
                    System.out.println("Orbita alrededor del sol");
                    orbita = sc.nextDouble();
                    System.out.println("Tiene Satélites? 1. SI 2.NO");
                    if(sc.nextInt() == 1){
                        tSatelite = true;
                    }else{
                        tSatelite = false;
                    }

                    //No voy a validar todos los datos por ahora, si tengo tiempo lo voy puliendo después
                    p = new Planeta(nombre, masa, rotacionSoEje, tempMedia, gravedad, radioEcuatorial,distanciaSol,orbita,tSatelite);
                    planetas[contadorPlanetas] = p;
                    contadorPlanetas++;
                    break;
                case "2":
                    System.out.println("Ingrese los datos del satélite según se le vaya solicitando");
                    //Lo primero que tendré que hacer será seleccionar el planeta al que pertenece el satélite y si al
                    //insertar los datos del planeta hemos indicado que el planeta tiene satélites
                    String listadoPlanetas = "";
                    for(int i = 0; i < contadorPlanetas; i++){
                        listadoPlanetas = listadoPlanetas + "\n" + (i+1) + planetas[i].getNombre();
                    }
                    System.out.println(listadoPlanetas);
                    System.out.println("Seleccione el planeta al que pertenece introduciendo su índice");
                    int indice = sc.nextInt();
                    System.out.println();
                    p = planetas[indice - 1];
                    if(p.isTieneSatelites()){
                        sc = new Scanner(System.in);
                        //Hechas las comprobaciones puede insertar datos del satélite
                        System.out.println("Ingrese los datos del planeta según se le vaya solicitando");
                        System.out.println("Nombre");
                        nombre = sc.nextLine();
                        System.out.println("Masa");
                        masa = sc.nextDouble();
                        System.out.println("Rotación sobre su eje");
                        rotacionSoEje = sc.nextDouble();
                        System.out.println("Temperatura Media");
                        tempMedia = sc.nextDouble();
                        System.out.println("Gravedad");
                        gravedad = sc.nextDouble();
                        System.out.println("Radio Ecuatorial");
                        radioEcuatorial = sc.nextDouble();
                        System.out.println("Distancia al Planetaria");
                        distanciaPlaneta = sc.nextDouble();
                        System.out.println("Orbita Planetaria");
                        orbitaPlanetaria = sc.nextDouble();
                        s= new Satelites(nombre,masa,rotacionSoEje,tempMedia,gravedad,radioEcuatorial,distanciaPlaneta,orbitaPlanetaria,p.getNombre());
                        p.almacenarSatelites(s);
                    }else{
                        System.out.println("El planeta no tiene satelites");
                    }
                    break;
                case "3":
                    System.out.println("Indique el planeta del que quiere ver datos");
                    listadoPlanetas="";
                    for(int i = 0; i < contadorPlanetas; i++){
                        listadoPlanetas = listadoPlanetas + "\n" + (i+1) + planetas[i].getNombre();
                    }
                    System.out.println(listadoPlanetas);
                    System.out.println("Seleccione el planeta del que quiere ver su informacion");
                    indice = sc.nextInt();
                    p = planetas[indice - 1];
                    System.out.println(p.toString());
                    break;
                case "4":
                    System.out.println("Indique los datos del satélite que quiere visualizar los datos");
                    //Aquí, aunque en principio podría ser más farragoso, voy a hacer un for anidado dependiendo
                    //de una condición
                    System.out.println("Indique el planeta al que pertenece el satélite");
                    listadoPlanetas="";
                    for(int i = 0; i < contadorPlanetas; i++){
                        if(planetas[i].isTieneSatelites() && planetas[i].getContadorSatelites() > 0){
                            listadoPlanetas = listadoPlanetas + "\n" + (i+1) + planetas[i].getNombre();
                        }
                    }
                    System.out.println(listadoPlanetas);
                    indice = sc.nextInt();
                    p = planetas[indice - 1];
                    System.out.println("Veamos sus satélites");
                    listadoPlanetas = "";
                    for(int j = 0; j < p.getContadorSatelites(); j++){
                        listadoPlanetas = listadoPlanetas + "\n" + (j+1) + p.satelites[j].getNombre();
                    }
                    System.out.println(listadoPlanetas);
                    System.out.println("Indique el satélite del que quiere conocer toda su información");
                    indice = sc.nextInt();
                    s = p.satelites[indice - 1];
                    System.out.println(s.toString());
                    break;
                case "5":
                    System.out.println("Abandonando la galaxia");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }while(!opcion.equals("5"));

    }


}
