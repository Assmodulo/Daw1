import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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

        //Eliminar dos objetos. Los termis que no los voy a usar, y los intercesores, que ya tengo
        listadoProductos.remove(p1);
        listadoProductos.remove(p2);

        Iterator<Producto> iterador2 = listadoProductos.iterator();
        while(iterador2.hasNext()){
            System.out.println(iterador2.next());
        }

        //Insertar un nuevo objeto producto en mitad de la lista
        //Como sólo me quedan 3 objetos, y empiezo por la posición cero, voy a insertarlo en la pos 1
        Producto p6 = new Producto("Impulsor Tank", 1);
        listadoProductos.add(1,p6);

        //Vuelvo a utilizar iterator
        Iterator<Producto> iterador3 = listadoProductos.iterator();
        while(iterador3.hasNext()){
            System.out.println(iterador3.next());
        }
        System.out.println();

        //Usar Sort y Compare to
        Collections.sort(listadoProductos);
        for(Producto p : listadoProductos){
            System.out.println(p);
        }

        //Eliminar todos los elementos
        listadoProductos.clear();
    }
}
