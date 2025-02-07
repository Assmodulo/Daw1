public class Main {
    public static void main(String[] args) {

        //De inicio declaro tres variables del tipo gato con el constructor por defecto
        Gato g1 = new Gato();
        Gato g = new Gato();
        Gato g2 = new Gato();
        try {
            //Dentro del try voy a ir definiendo nuevos objetos de tipo gato
            //El primero va a ser correcto, el segundo no y el tercero correcto también
            //Luego voy imprimiendo cada una, la cosa es ver el flow dentro del try
            g = new Gato("Arthur",6);
            System.out.println(g);
            g1 = new Gato("Io", -3);
            System.out.println(g1);
            g2 = new Gato("Firulais", 5);
            System.out.println(g2);
        } catch (LongitudNombreException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (EdadException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            //Aquí muestro por pantalla los objetos creados. El primero lo mostrará bien, el segundo todo null porque no se ha
            //creado y el tercero, como el segundo da fallo, directamente no llega a el por lo tanto va a ser null
            System.out.println(g1 + " " + g + " " + g2);
        }

        //Otro bloque try para porbarlo simplemente con las funciones set,no va a saltar ningún error previsiblemente
        //aunque nunca se sabe
        try {
            g1.setNombre("Misifu");
            g1.setEdad(2);
        } catch (LongitudNombreException e) {
            System.out.println(e.getMessage());
        } catch (EdadException e) {
            System.out.println(e.getMessage());
        }

        //Muestro por pantalla g1 para ver que se ha cambiado
        System.out.println(g1);

    }
}