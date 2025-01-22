import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args){

        //Creo 5 instancias de producto
        Producto p1 = new Producto("Intercesores", 5);
        Producto p2 = new Producto("Exterminadores", 2);
        Producto p3 = new Producto("Cruzados Primaris", 6);
        Producto p4 = new Producto("Exploradores", 3);
        Producto p5 = new Producto("Jump Pack Primaris", 3);

        //Creo ArrayList de producto
        ArrayList<Producto> listadoProductos = new ArrayList<>();

        //Añado los productos a la lista
        listadoProductos.add(p1);
        listadoProductos.add(p2);
        listadoProductos.add(p3);
        listadoProductos.add(p4);
        listadoProductos.add(p5);

        //Recorrer el array usando iterator
        Iterator<Producto> iterador = listadoProductos.iterator();
        while(iterador.hasNext()){
            System.out.println(iterador.next());
        }

        System.out.println();
        System.out.println();

        //Eliminar dos objetos. Los termis que no los voy a usar, y los intercesores, que ya tengo
        listadoProductos.remove(p1);
        listadoProductos.remove(p2);

        System.out.println("ORDENAR PRODCUTOS DESPUES DE ELIMINAR 2");

        iterador = listadoProductos.iterator();
        while(iterador.hasNext()){
            System.out.println(iterador.next());
        }

        //Insertar un nuevo objeto producto en mitad de la lista
        //Como sólo me quedan 3 objetos, y empiezo por la posición cero, voy a insertarlo en la pos 1
        Producto p6 = new Producto("Impulsor Tank", 1);
        listadoProductos.add(1,p6);

        System.out.println("ORDENAR PRODUCTOS DESPUES DE AÑADIR UNO EN POSICION ALEATORIA");

        //Vuelvo a utilizar iterator
        iterador = listadoProductos.iterator();
        while(iterador.hasNext()){
            System.out.println(iterador.next());
        }
        System.out.println();

        System.out.println("MOSTRAR LA LISTA DESPUÉS DE ORDENAR LA COLECCIÓN");

        //Usar Sort y Compare to
        Collections.sort(listadoProductos);
        for(Producto p : listadoProductos){
            System.out.println(p);
        }

        //Eliminar todos los elementos
        listadoProductos.clear();

        System.out.println();
        System.out.println();


        //Siguiendo el ejercicio ahora creo una linkedlist
        LinkedList <Producto> lista = new LinkedList();

        //Añado los 5 productos, los mismos que cree al principio
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        lista.add(p4);
        lista.add(p5);

        //Recorro la lista con el iterator
        iterador = lista.iterator();
        while(iterador.hasNext()){
            System.out.println(iterador.next());
        }


        //Elimino dos registros cualquiera
        lista.remove(p2);
        lista.remove(p4);

        System.out.println();
        System.out.println();

        //Vuelvo a recorrer simplemente para comprobar
        iterador = lista.iterator();
        while(iterador.hasNext()){
            System.out.println(iterador.next());
        }

        System.out.println();

        //Insertar un producto en medio de la lista, lo mismo que el ejercicio anterior
        //uso el add con radix
        lista.add(1,p6);

        iterador = lista.iterator();
        while(iterador.hasNext()){
            System.out.println(iterador.next());
        }

        System.out.println();

        Collections.sort(lista);
        for(Producto p : lista){
            System.out.println(p);
        }

        lista.clear();

        System.out.println();

        //Aquí simplemente uso el if por comprobar, ya el propio ide me indica que la condición va a ser siempre true

        if(lista.isEmpty()){
            System.out.println("Lista vacia");
        }else{
            System.out.println("Algo has hecho mal");
        }

    }
}
