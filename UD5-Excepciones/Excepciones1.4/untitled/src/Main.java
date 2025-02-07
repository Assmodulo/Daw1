public class Main {
    public static void main(String[] args) throws ValoresNoPermitidosException {
        //Tengo que cambiar cosas, como por ejemplo que solo tengo una clase de excepción y debería de tener varias, una para negativos
        //en positivos y viceversa. Luego debería de probar con varios bloques trycatch.

        try {
            //Pruebo primero con estos dos valores, no van a dar errores y se van a mostror por pantalla
            imprimePositivo(5);
            imprimeNegativo(-7);

        } catch (ValoresNoPermitidosException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ValoresNoPertmitidosNegativosException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        try{
            //Aqui voy a meter dos valores que no están permitidos. Me va a saltar error en el primero, al segundo
            //no va ni a llegar porque van a saltar las excepciones y luego mostrará el stackTrace
            imprimePositivo(-1);
            imprimeNegativo(6);
        }catch (ValoresNoPermitidosException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (ValoresNoPertmitidosNegativosException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void imprimePositivo(int p) throws ValoresNoPermitidosException {
        if (p > 0) {
            System.out.println(p);
        } else {
            throw new ValoresNoPermitidosException(p);
        }
    }

    public static void imprimeNegativo(int p) throws ValoresNoPertmitidosNegativosException {
        if (p < 0) {
            System.out.println(p);
        } else {
            throw new ValoresNoPertmitidosNegativosException(p);
        }
    }
}