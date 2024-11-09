package ud2_actividad3;

import java.util.Scanner;

public class Ud2_Actividad3 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in); //Instancio un nuevo objeto de la clase Scanner
		
		
//		Realiza un programa que dada una cantidad de euros que el usuario introduce por
//		teclado (múltiplo de 5 €) mostrará los billetes de cada tipo que serán necesarios para
//		alcanzar dicha cantidad (utilizando billetes de 500, 200, 100, 50, 20, 10 y 5). Hay que
//		indicar el mínimo de billetes posible. Por ejemplo, si el usuario introduce 145 el
//		programa indicará que será necesario 1 billete de 100 €, 2 billetes de 20 € y 1 billete de
//		5 € (no será válido por ejemplo 29 billetes de 5, que aunque sume 145 € no es el mínimo
//		número de billetes posible).
//		
		int dinero; //Declaro una variable double para almacenar el dinero que introduciremos por teclado

		//Yo habia hecho el ejercicio de otra manera, pero ya que pide que sea multiplo de 5 voy a cambiar el programa para
		//que se ajuste a lo que se solicita

		/*Para asegurarme de que se introduzca una cantidad multiplo de 5 voy a usar un do while
		creando una variable de tipo boolean que cambiará a true cuando se cumplan las condiciones */


		boolean cantCorrecta = false;

		do{
			System.out.println("Introduzca por teclado una cantidad de dinero, multiplo de 5 y el programa calculará\n"
				+ "el número menor de billetes necesarios para sumar dicha cantidad");
		
			dinero = teclado.nextInt();

			if(dinero%5==0){
				cantCorrecta = true;
			}else{
				System.out.println("Ha introducido una cantidad que no es multiplo de 5.\n"
				+"Intentelo de nuevo");
			}

		}while(!cantCorrecta);

		

		System.out.println("El dinero del que vamos a calcular los billetes necesarios es :" + dinero);

		//Debo declarar variables para lamacenar billetes

		int bill500 = 0, bill200 = 0, bill100 = 0, bill50 = 0, bill20 = 0, bill10 = 0, bill5 = 0;
		 

		if (dinero >= 500) {
			bill500 = dinero / 500; 
			dinero = dinero%500;
		}

		if (dinero >= 200) {
			bill200 =  dinero / 200; 
			dinero = dinero%200;
		}

		if (dinero >= 100) {
			bill100 =  dinero / 100; 
			dinero = dinero%100;
		}

		if (dinero >= 50) {
			bill50 =  dinero / 50; 
			dinero = dinero%50;
		}

		if (dinero >= 20) {
			bill20 =  dinero / 20; 
			dinero = dinero%20;
		}

		if (dinero >= 10) {
			bill10 =  dinero / 10; 
			dinero = dinero%10;
		}

		if (dinero >= 5) {
			bill5 =  dinero / 5; 
			dinero = dinero%5;
		}

		//A partir de ahora hay que ir evaluando la cantidad del dinero que nos queda y modificándolo todo
		System.out.println("La cantidad de billetes para sumar el dinero deseado es de: \n"
		+"Billetes de 500: " + bill500 +"\n"
		+"Billetes de 200: " + bill200 +"\n"
		+"Billetes de 100: " + bill100 +"\n"
		+"Billetes de 50: " + bill50 +"\n"
		+"Billetes de 20: " + bill20 +"\n"
		+"Billetes de 10: " + bill10 +"\n"
		+"Billetes de 5: " + bill5);

//		Realiza un programa que muestre un menú de opciones como el siguiente:
//		1. Sumar
//		2. Restar
//		3. Multiplicar
//		4. Dividir (incluir manejo de división por 0)
//		5. Salir
//		El menú debe de repetirse hasta que se escoja la opción 5 (Salir).

		//Lo primero que voy a hacer es definir dos variables que van a ser los números correspondiente
		double numero1, numero2;

		
		

		System.out.println("Se le va a mostrar un menú de operaciones, sigue los pasos");

		boolean salir = false; //Declaro esta variable boolean para controlar la salida del bucle

		String opcion = "";   //Declaro esta variable para compararle con la opción seleccionada, en principio
							  //habia usado una variable int pero se explicó en clase que mejor una variable String
		
		do { 
			//también reinicio la variable de la clase Scanner, para evitar problemas lo reinicio cada vez que se ejecuta
			//una nueva iteración del bucle

			teclado = new Scanner(System.in);

			//Muestro las opciones del menú al usuario
			System.out.println("Elija una de las opciones");
			System.out.println("1 - Suma\n"
			+"2 - Resta\n"
			+"3 - Multiplicación\n"
			+"4 - División\n"
			+"5 - Salir\n");

			//Guardo la elección del usuario en la variable opcion
			opcion = teclado.nextLine();

			//Inicio un switch para que evalue la opción
			switch(opcion){
				case "1":
					System.out.println("Introduce el primer número");
					numero1 = teclado.nextDouble();
					System.out.println("Introduce el segundo número");
					numero2 = teclado.nextDouble();
					System.out.println("La suma de los números es " + (numero1 + numero2));
					System.out.println();
				break;
				case "2":
					System.out.println("Introduce el primer número");
					numero1 = teclado.nextDouble();
					System.out.println("Introduce el segundo número");
					numero2 = teclado.nextDouble();
					System.out.println("La resta de los números es " + (numero1-numero2));
					System.out.println();
				break;
				case "3":
					System.out.println("Introduce el primer número");
					numero1 = teclado.nextDouble();
					System.out.println("Introduce el segundo número");
					numero2 = teclado.nextDouble();
					System.out.println("La multiplicación de los números es " + (numero1*numero2));
					System.out.println();
				break;
				case "4":
					System.out.println("Introduce el primer número");
					numero1 = teclado.nextDouble();
					System.out.println("Introduce el segundo número");
					numero2 = teclado.nextDouble();
					if(numero2 != 0){
						System.out.println("La división de los números es " + (numero1/numero2));	
					}else{
						System.err.println("La división de un número por cero es error");
					}
					System.out.println();
				break;
				case "5":
					System.out.println("El programa se va a cerrar");
					salir = true; 
				break;
				default:
				System.err.println("La opción elegida no es una opción válida");
			}
		} while (!salir);


	}

}
