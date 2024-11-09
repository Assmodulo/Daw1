package actividad1;

import java.util.Scanner;

public class Actividad1 {

	public static void main(String[] args) {
		
		//Declaro un nuevo objeto de la clase escaner para los ejercicios que lo necesiten
		
		Scanner teclado = new Scanner(System.in);
		
//		1. Escribe un programa que dé los “buenos días”.
		
		System.out.println("Buenos días");
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		2. Escribe un programa que calcule y muestre el área de un cuadrado de lado igual a 5.
		
		int lado = 5;//declaro una variable que almacene el valor del lado
		
		System.out.println("El area del cuadrado es: " + (lado*lado));
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		3. Escribe un programa que calcule el área de un cuadrado cuyo lado se introduce por
//		teclado.
		
		//En este ejercicio voy a usar la variable anterior lado para almacenar un valor introducido por teclado
		
		System.out.println("Introduzca un valor que equivale a la medida del lado de un cuadrado");
		
		lado = teclado.nextInt(); //Hago que la variable lado almacene el valor
		
		System.out.println("El area del cuadrado es: " + (lado * lado));
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		4. Escribe un programa que lea dos números, calcule y muestre el valor de sus suma, resta,
//		producto y división.
		
		int numX = 37, numY = 14;
		
		//Ahora mostrare por pantalla el resultado de las operaciones requeridas
		
		System.out.println("La suma de los números " + numX + " y " + numY + " es: " + (numX + numY));
		
		System.out.println("La resta de los números " + numX + " y " + numY + " es: " + (numX - numY));
		
		System.out.println("El producto de los números " + numX + " y " + numY + " es: " + (numX * numY));
		
		System.out.println("La división de los números " + numX + " y " + numY + " es: " + (numX / numY));
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		5. Escribe un programa que toma como dato de entrada un número que corresponde a la
//		longitud de un radio y nos escribe la longitud de la circunferencia, el área del círculo y el
//		volumen de la esfera que corresponden con dicho radio.
		
		System.out.println("Este programa le solicita la medida del radio de una circunferencia\n"
				+ "y calculara su perímetro, su área y el volumen de la esfera");
		
		double radio; //Declaro una variable que almacena el valor del radio
		
		radio = teclado.nextDouble(); //Lo introduzco por teclado
		
		//Voy a mostrar los resultados por pantalla
		
		System.out.println("La circunferencia será igual a: " + (2 * Math.PI * radio));
		
		System.out.println("El area será igual a: " + (Math.PI * Math.pow(radio, 2)));
		
		System.out.println("El volumen será igual a: " + (4 * Math.PI * Math.pow(radio,3))/3);
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		6. Escribe un programa que dado el precio de un artículo y el precio de venta real nos
//		muestre el porcentaje de descuento realizado.
		
		//Declaro dos variable para los respectivos precios
		double precioTotal, precioFinal;
		
		System.out.println("El programa le va a solicitar el precio de un producto, luego el precio con descuento final\n"
				+ "y luego calculará cual ha sido el porcentaje de descuento");
		
		System.out.println("Introduzca el precio total del producto");
		
		precioTotal = teclado.nextDouble();
		
		System.out.println("Introduzca el precio real que ha pagado");
		
		precioFinal = teclado.nextDouble();
		
		//Calculamos el descuento
		
		System.out.println("El % descuento ha sido de: " + (100 -(precioFinal*100)/precioTotal));
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		7. Escribe un programa que lea un valor correspondiente a una distancia en millas marinas
//		y escriba la distancia en metros. Sabiendo que una milla marina equivale a 1.852 metros.
		
		System.out.println("Introduzca por teclado una distancia en millas marinas y el programa lo convertira\n"
				+ "a metros");
		
		double millas; //Creo una variable millas del tipo double
		
		System.out.println("Introduzca la distancia en millas a convertir");
		
		millas = teclado.nextDouble();
		
		System.out.println(millas + " millas equivale a " + (millas * 1.852) + " metros");
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
		
//		8. Escribe un programa que lee dos números y los visualiza en orden ascendente.
		
		//voy a usar las variable numX y numY de ejercicios anteriores
		
		System.out.println("El programa le solicitará que introduzca dos números.\n"
				+ "Luego los ordenará de forma ascendente");
		
		System.out.println("Introduzca el primer número");
		
		numX = teclado.nextInt();
		
		System.out.println("Introduzca el segundo número");
		
		numY = teclado.nextInt();
		
		//con esto he almacenado los dos valores, ahora lo muestro por pantalla
		
		System.out.println("En orden acendente los números serían " + Math.min(numX , numY) + ", " + Math.max(numX , numY) );
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		9. Escribe un programa que lee dos números y nos dice cuál es el mayor o si son iguales.
		
		//Para este ejercicio sigo usando numX y numY aunque sean int
		
		System.out.println("El programa solicita dos números, luego muestra el mayor y nos dice si son iguales");
		
		System.out.println("Introduzca el primer número");
		
		numX = teclado.nextInt();
		
		System.out.println("Introduzca el segundo valor");
		
		numY = teclado.nextInt();
		
		//Muestro por pantalla lo que se solicita
		
		System.out.println("El número mayor de los dos es " + Math.max(numX, numY));
		
		System.out.println("Son los números iguales?: " + (numX == numY));
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		10. Escribe un programa que lea tres números distintos y nos diga cuál es el mayor.
		
		//Para este programa y por seguir la lógica creo una tercera variable que se suma a las dos anteriores
		
		int numZ;
		
		System.out.println("El programa le solicitará tres números y calculará el mayor");
		
		System.out.println("Introduzca el primer número");
		
		numX = teclado.nextInt();
		
		System.out.println("Introduzca el segundo número");
		
		numY = teclado.nextInt();
		
		System.out.println("Introduzca el tercer número");
		
		numZ = teclado.nextInt();
		
		//muestro por pantalla el resultado
		
		System.out.println("El mayor número de los tres es " + Math.max(Math.max(numX, numY), numZ));
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		11. Escribe un programa que lee dos números, calcula y muestra el valor de su suma, resta,
//		producto y división. (Ten en cuenta la división por cero).
		
		System.out.println("Este programa le pide dos números y le muestra su suma, resta, producto y division\n"
				+ "Tenga en cuenta que el segundo número no debe de ser cero");
		
		double numeroX, numeroY;
		
		System.out.println("Introduzca el primer número");
		
		numeroX = teclado.nextInt();
		
		System.out.println("Introduzca el segundo número");
		
		numeroY = teclado.nextInt();
		
		System.out.println("La suma de los números " + numX + " y " + numY + " es: " + (numX + numY));
		
		System.out.println("La resta de los números " + numX + " y " + numY + " es: " + (numX - numY));
		
		System.out.println("El producto de los números " + numX + " y " + numY + " es: " + (numX * numY));
		
		System.out.println("La división de los números " + numX + " y " + numY + " es: " + (numX / numY));
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
//		12. Escribe un programa que lee 2 números y muestra el mayor.
		
		//Uso de nuevo variables anteriores
		
		System.out.println("Introduzca dos números por teclado y el programa mostrará el mayor");
		
		System.out.println("Introduzca el primer número");
		
		numX = teclado.nextInt();
		
		System.out.println("Introduzca el segundo número");
		
		numY = teclado.nextInt();
		
		//Lo muestro por pantalla
		
		System.out.println("El mayor número de los dos introducidos es " + Math.max(numX , numY));
	
		
		System.out.println();
		System.out.println("**********");
		System.out.println();
		
		
//		13. Escribe un programa que lee un número y me dice si es positivo o negativo
//		consideraremos el cero como positivo.
		
		System.out.println("Este programa le pedirá un número y le dira si es positivo o negativo.\n"
				+ "Consideraremos el cero como positivo");
		
		//Uso numX
		
		numX = teclado.nextInt();
		
		//Muestro el resultado por pantalla
		
		System.out.println("Nuestro numero es positivo?: " + (numX >= 0));
		System.out.println("Nuestro numero es negativo?: " + (numX < 0));

	}

}
