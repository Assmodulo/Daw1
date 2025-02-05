public class Main {
    public static void main(String[] args) throws ValoresNoPermitidosException {
        //Tengo que cambiar cosas, como por ejemplo que solo tengo una clase de excepción y debería de tener varias, una para negativos
        //en positivos y viceversa. Luego debería de probar con varios bloques trycatch.

        try {
            imprimePositivo(5);
            imprimeNegativo(-7);
            //imprimePositivo(-1);
            imprimeNegativo(6);
        } catch (ValoresNoPermitidosException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void imprimePositivo(int p) throws ValoresNoPermitidosException {
        if (p > 0) {
            System.out.println(p);
        } else {
            throw new ValoresNoPermitidosException(p);
        }
    }

    public static void imprimeNegativo(int p) throws ValoresNoPermitidosException {
        if (p < 0) {
            System.out.println(p);
        } else {
            throw new ValoresNoPermitidosException(p);
        }
    }
}