package ud2_actividad2;

import java.util.Scanner;

public class Unidad2_Actividad2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner teclado = new Scanner(System.in);
		
		
//		Realiza los siguientes ejercicios relacionados con la segunda unidad didáctica.
		
//		1. Escribe un programa que pide la edad por teclado y nos muestra el mensaje de “Eres
//		mayor de edad” solo si lo somos.
		
		System.out.println("El programa nos solicitara por teclado una edad y nos dirá si"
				+ " el sujeto es mayor de edad o no");
		
		int edad; //defino una variable edad
		
		System.out.println("Introduzca por teclado su edad");
		
		edad = teclado.nextInt();
		
		//Con un if evaluamos si somos mayores de edad o no
		
		if (edad>=18) {
			System.out.println("Eres mayor de edad. Ya puedes ir a la carcel");
		}
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		2. Escribe un programa que pide la edad por teclado y nos muestra el mensaje de “eres
//		mayor de edad” o el mensaje de “eres menor de edad”.
		
		//Voy a utilizar la variable edad y la variable teclado de la clase Scanner del ejercicio anterior
		
		System.out.println("Introduzca un nuevo valor de edad por teclado");
		edad = teclado.nextInt();
		
		//Volvemos a evaluar el valor de edad
		
		if (edad>0 && edad<18) {
			System.out.println("Eres menor de edad. Al reformatorio");
		} else {
			System.out.println("Eres mayor de edad. A la carcel otra vez");
		}
		
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		3. Realiza un programa que muestre por pantalla los 20 primeros números naturales (1, 2,
//		3... 20).
		
		
		//con un bucle for recorro los números del 1 al 20 y los muestro por pantalla mediante el valor del
		//contador i
		
		for(int i=1;i<=20;i++) {
			System.out.print(i + " ");
		}
		
		System.out.println();
		
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		4. Realiza un programa que muestre los números pares comprendidos entre el 1 y el 200.
//		Para ello utiliza un contador y suma de 2 en 2.
		
		
		//Uso un bucle for pero inicio el valor de la variable contador en 2 y la voy añadiendo dos en cada iteración
		//mediante la instrucción +=2
		
		for(int i = 2; i <= 200; i+=2) {
			System.out.print(i + " ");
		}	
		
		System.out.println();
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
//		5. Realiza un programa que muestre los números pares comprendidos entre el 1 y el 200.
//		Esta vez utiliza un contador sumando de 1 en 1.
		
		
		//El mismo caso que en los ejercicios anteriores pero evaluando con un if si es par o no
		
		for(int i=1;i<=200;i++) {
			if(i%2==0) {
				System.out.print(i + " ");
			}
		}
		
		System.out.println();
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
//		6. Realiza un programa que muestre los números desde el 1 hasta un número N que se
//		introducirá por teclado.
		
		int n; //Declaro una variable n que es un número que voy a introducir por teclado
		
		System.out.println("Introduzca por teclado un número, el que usted quiera. Luego el sistema"
				+ " mostrará los números desde 1 hasta el número n que haya introducido usted");
		
		n = teclado.nextInt();
		
		for(int i=1;i<=n;i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
//		7. Escribe un programa que lea una calificación numérica entre 0 y 10 y la transforma en
//		calificación alfabética, escribiendo el resultado.
//		• de 0 a <3 Muy Deficiente.
//		• de 3 a <5 Insuficiente.
//		• de 5 a <6 Bien.
//		• de 6 a <9 Notable
		
		
		//Solicitaremos un numero entre el 0 y el 10 por teclado y luego lo transformaremos en texto para indicar las
		//calificaciones
		
		int calificacion; //declaramos una variable que almacenará el valor de la nota
		
		System.out.println("Introduzca por teclado una calificación  comprendida entre 1 y 10");
		
		calificacion = teclado.nextInt();
		
		if(calificacion>=0 && calificacion<=10) {
			if(calificacion<3) {
				System.out.println("Muy Deficiente");
			}else if(calificacion>=3 && calificacion<5) {
				System.out.println("Insuficiente");
			}else if(calificacion>=5 && calificacion<6) {
				System.out.println("Bien");
			}else if(calificacion>=6 && calificacion<9) {
				System.out.println("Notable");
			}else {
				System.out.println("Sobresaliente");
			}
		}else {
			System.err.println("Las calificaciones siempre deben de estar comprendidas entre 0 y 10");
		}
		
		System.out.println();
		System.out.println("**********");
		System.out.println();

//		8. Realiza un programa que lea un número positivo N y calcule y visualice su factorial N!
//		Siendo el factorial:
//		• 0! = 1
//		• 1! = 1
//		• 2! = 2 * 1
//		• 3! = 3 * 2* 1
//		• N! = N * (N-1) * (N-2)........* 3*2*1
		
		//voy a usar la variable n para solicitar el número del ejercicio anterior
		System.out.println("Introduzca por teclado un numero N del que luego el programa calculara su factorial");
		
		n = teclado.nextInt();
		
		//Declaro una variable tambien para almacenar las sucesivas operaciones
		
		int factorial = 1; //Inicio esta variable como uno pues el factorial del 0 por convención es 1 y el del 1 también
						//No tiene sentido inicializar esta variable en 0
		
		//Inicio un for para realizar las sucesivas multiplicaciones
		
		for( int i = 2; i <= n; i++) {
			//La variable int del for la inicio en 2 por la siguiente razón. Ya que el factorial de 0 y de 1 es 1 no es necesario
			//empezar a contar por 0 y 1 ya que no va a modificar para nada el valor de la variable factorial
			factorial = factorial * i;
		}
		
		System.out.println("El factorial de N es: " + factorial);
		
		
//		9. Escribe un programa que recibe como datos de entrada una hora expresada en horas,
//		minutos y segundos que nos calcula y escribe la hora, minutos y segundos que serán,
//		transcurrido un segundo.
		
		int horas, minutos, segundos; //Declaro las variables que voy a necesitar
		
		System.out.println("Este programa le va a solicitar un valor de horas, luego uno de minutos\n"
				+ "y luego los segundos.\n"
				+ "El formato de las horas debe ser el de 12 horas, no el de 24, así hay mas\n"
				+ "posibilidades de que se produzcan cambios");
		
		System.out.println("Introduzca ahora el valor de las horas");
		
		horas = teclado.nextInt();
		
		System.out.println("Introduzca ahora el valor de los minutos");
		
		minutos = teclado.nextInt();
		
		System.out.println("Introduzca ahora el valor de los segundos");
		
		segundos = teclado.nextInt();
		
		System.out.println("Ahora vamos a sumarle un segundo a lo que ya tenemos y vemos que pasa");
		
		segundos += 1; //Sumamos un segundo a segundo y evaluamos
		
		if(segundos > 59) {
			segundos = 0;
			minutos += 1;
		 	if(minutos > 59) {
				minutos = 0;
				horas += 1;
				if(horas > 12) {
					horas = 1;
				}
			}
		}
		
		
		//Con estos if hemos evaluado lo que pasa al añadir un segundo, evaluamos el cambio que supone en segundos
		//Luego evaluamos los minutos y después finalmente las horas
		
		System.out.println("Al añadir un segundo la nueva hora es:  " + horas + ":" + minutos + ":" + segundos);
		
		//Donde mejor se ve si todos sale bien es si se introduce las 12:59:59
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		10. Realiza un programa que lea 10 números no nulos y luego muestre un mensaje de si ha
//		leído algún número negativo o no.
		
		
		//Como sólo necesito conocer la cantidad de numeros negativos puedo volver a usar las 
		//variable n para leer el número, pero con otra variable booleana puede decir que en cuanto
		//lear el primer numero negativo pase de false a true
		
		boolean existenNegativos = false;
		
		System.out.println("Se le va a pedir que introduzca 10 numeros enteros. Luego el programa le indicará\n"
				+ "si ha introducido algún número negativo");
		
		for(int i = 1; i <= 10; i++) {
			System.out.println("Introduzca un número entero");
			n = teclado.nextInt();
			if(n < 0) {
				existenNegativos = true;
			}
		}
		
		//Ahora evaluo la variable booleana para imprimir un mensaje u otro
		
		if(existenNegativos) {
			System.out.println("Se han encontrado números negativos entre los 10 que ha introducido");
		}else {
			System.out.println("No se han encontrado números negativos");
		}
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		11. Realiza un programa que lea 10 números no nulos y luego muestre un mensaje
//		indicando cuántos son positivos y cuantos negativos.
		
		
		/*
		 * Como en los anteriores usaré la variable n para leer los números de forma temporal
		 * Definiré dos variables, contadorNegativos y ContadorPositivos que se irán incrementando cada vez
		 * que se lea un número de este tipo. Contaremos 0 como un número positivo
		 * Pero primero le indicamos al usuario lo que hace el programa
		 */
		
		System.out.println("El programa solicitará al usuario que introduzca diez números, uno por uno\n"
				+ "e irá llevando la cuenta de cuantos son positivos y cuantos negativos. Luego mostrará los totales");
		int contadorPositivos = 0, contadorNegativos = 0;
		
		//Para solicitar los 10 números usaré un for
		
		for(int i = 1; i <= 10; i++) {
			System.out.println("Introduzca el número a evaluar");
			n = teclado.nextInt();
			if( n >= 0) {
				contadorPositivos++;
			}else {
				contadorNegativos++;
			}
		}
		
		System.out.println("La cantidad de números positivos es de: " + contadorPositivos);
		System.out.println("La cantidad de números negativos es de: " + contadorNegativos);
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		12. Realiza un programa que lea una secuencia de números no nulos hasta que se introduzca
//		un 0, y luego muestre si ha leído algún número negativo, cuantos positivos y cuantos
//		negativos.
		
		/*
		 * Para este ejercicio voy a declarar nuevas variables, para no quemar las anteriores
		 * Voy a declarar 3 variables ints, una para almacenar el número que se introduce mediante teclado
		 * Otras dos que son contadores como en el anterior ejercicio para saber número de postitivos y negativos
		 * Además definiré una booleana para saber si encuentra algún negativo y otra más para salir del bucle
		 */
		
		int numeroN, contPosit = 0, contNegat = 0;
		boolean negativos = false, saliendo = false;
		
		//Para este ejercicio usaré un do-while, ya que necesito que se ejecute la primera vez y siga hasta que
		//se pulse cero
		
		System.out.println("El programa le va a pedir introducir números, hasta que pulse 0.");
		System.out.println("Al introducir números evaluará si ha encontrado negativos, cuantos\n"
				+ "negativos y cuantos positivos");
		
		do {
			System.out.println("Introduzca un número");
			numeroN = teclado.nextInt();
			if (numeroN == 0) {
				saliendo = true;
			}else if (numeroN < 0) {
				negativos = true;
				contNegat++;
			}else {
				contPosit++;
			}
		}while(!saliendo); //esta es la condición para que salga, que cambiará cuando se pulse 0;
		
		//Ahora evaluamos el estado de negativos para ver el mensaje que tiene que mandar
		
		if (negativos) {
			System.out.println("Se han encontrado números negativos");
		}else {
			System.out.println("No se han encontrado números negativos");
		}
		
		System.out.println("El total de números positivos es de: " + contPosit);
		System.out.println("El total de números positivos es de: " + contNegat);
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
//		13. Realiza un programa que calcule y escriba la suma y el producto de los 10 primeros
//		números naturales.
		
		
		//Voy a declarar dos variable para almacenar los totales de la suma y el producto
		
		int sumaNaturales = 1, productoNaturales = 1;
		
		/*
		 * He iniciado las dos variables a 1 ya que es más fácil para calcular las operaciones acumuladas
		 * sobre todo el producto, además de que debo iniciarlas las dos a ese mismo valor porque así lo hago 
		 * todo en un solo for
		 */
		
		for ( int i = 2; i <= 10; i++) { //Inicio la variable i en 2 porque el resultado de sumar o multiplicar por 1 lo tengo ya
										 // en la s variables
			
			sumaNaturales += i;
			productoNaturales *= i;
		}
		
		//Mostramos por pantalla los resultados
		
		System.out.println("El resultado de la suma de los primeros 10 naturales es: " + sumaNaturales);
		System.out.println("El resultado del producto de los primeros 10 naturales es: " + productoNaturales);
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		14. Escribe un programa que calcula el salario neto semanal de un trabajador en función del
//		número de horas trabajadas y la tasa de impuestos de acuerdo a las siguientes hipótesis:
//		• Las primeras 35 horas se pagan a tarifa normal.
//		• Las horas que pasen de 35 se pagan a 1,5 veces la tarifa normal.
//		• Las tasas de impuestos son:
//		• Los primeros 500 euros son libres de impuestos.
//		• Los siguientes 400 tienen un 25% de impuestos.
//		• Los restantes un 45% de impuestos.
//		Escribir nombre, salario bruto, tasas y salario neto.
		
		System.out.println("En este programa le vamos a indicar al programa las hora que trabaja un\n"
				+ "trabajador y el precio base de la hora de trabajo");
		System.out.println("Luego el programa nos va a hacer una serie de cálculos y los mostrará por pantalla");
		
		//Declaro una variable String para almacenar el nombre del trabajador introducido por teclado
		
		String nombre;
		nombre = teclado.next();
		
		//Lo primero que tengo que hacer es indicar el precio al que se pagan las horas y las horas trabajadas
		//Para ello declararé dos variable que almacenarán los datos después de solicitarlas por teclado
		
		double precioHora, horasTrabajadas; //Las horas trabajadas también como double por si trabaja fracciones de hora
		
		System.out.println("Introduzca el número de horas trabajadas");
		horasTrabajadas = teclado.nextDouble();
		System.out.println("Introduca el precio base de la hora de trabajo");
		precioHora = teclado.nextDouble();
		
		//Ahora debo declarar una variable que almacenara el sueldo bruto total después de calcular 
		//lo que se debe de pagar por cada hora
		
		double salarioBruto;
		
		//Evaluo todo lo que debo de pagar según las condiciones que especifica el ejercicio
		
		if(horasTrabajadas > 35) {
			salarioBruto = ((horasTrabajadas - 35) * (precioHora * 1.5)) + (35 * precioHora);
		}else {
			salarioBruto = precioHora * horasTrabajadas;
		}
		
		//Tengo que calcular ahora los impuestos, así que voy a declarar una variable para los impuestos
		
		double tasas;
		
		if(salarioBruto>500 && salarioBruto<=900) {
			tasas = 400 * 0.25;
		}else if (salarioBruto > 900) {
			tasas = (400 * 0.25) + ((salarioBruto - 900) * 0.45);
		}else {
			tasas = 0.0;
		}
		
		System.out.println("El nombre del trabajador es : " + nombre);
		System.out.println("Las horas trabajadas por " + nombre + " son: " + horasTrabajadas);
		System.out.println("El salario bruto del trabajador es : " + salarioBruto);
		System.out.println("Las tasas que debe de abonar el trabajador son : " + tasas);
		System.out.println("El salario neto del trabajador es de: " + (salarioBruto - tasas));
	}

}
