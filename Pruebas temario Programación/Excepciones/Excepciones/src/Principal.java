
    /*
    public class Principal {
        public static void main(String args[]) {
            int cero=0;
            int resul;
            try {
                resul=6/cero;
                System.out.println(resul);
            } catch (ArithmeticException e) {
                System.out.println("Se ha producido un error");
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Hasta pronto");
            }
        }
    }*/
    import java.util.Scanner;
    public class Principal {
        public static void main(String[] args) {
            Scanner lector = new Scanner(System.in);
            System.out.println("Introduce radio de la circunferencia: ");
                    String num = lector.nextLine();
            lector.close();
            int radio;
            try {
                radio = Integer.parseInt(num);
            } catch (NumberFormatException e) {
                System.out.println("Ha ocurrido una excepción de tipo el que sea");
                        System.out.println(e.getMessage());
                return;
            }finally {
                System.out.println("Fin del programa");
            }
            System.out.println("Longitud: " + (2*Math.PI*radio));
            System.out.println("Area: " + (Math.PI*radio*radio));

        }
    }