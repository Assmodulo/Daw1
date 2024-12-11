public class GestionEmpresarial {
    public static void main(String[] args) {

        //Defino las variables de las clases que voy a necestiar
        Empresa empresa;
        Trabajador trabajador = new Trabajador();
        String opcion = "", dni = "", nombre = "", fecha = "", direccion = "";

        System.out.println("BIENVENIDOS A NUESTRA GESTION EMPRESARIAL");
        System.out.println("LO PRIMERO QUE TENEMOS QUE HACER ES CREAR UNA EMPRESA");
        System.out.println(MyUtils.imprimirPantalla("NOMBRE DE LA EMPRESA"));
        nombre = MyUtils.devolverDato();
        System.out.println(MyUtils.imprimirPantalla("CIF"));
        String cif = MyUtils.obtenerCif();
        System.out.println(MyUtils.imprimirPantalla("FECHA FUNDACIÓN EMPRESA"));
        fecha = MyUtils.validarPatronFecha();
        int numero;
        do {
            System.out.println(MyUtils.imprimirPantalla("NUMERO MÁXIMO DE TRABAJADORES"));
            numero = MyUtils.validadNumeroTrabajadores(MyUtils.devolverDato());
        } while (numero <= 0);
        System.out.println();
        empresa = new Empresa(nombre, cif, fecha, numero);
        System.out.println("SU EMPRESA ES: " + empresa.mostrarInfoEmpresa());

        do{
            System.out.println(MyUtils.devolverOpcionesMenu());
            System.out.println(MyUtils.imprimirPantalla("OPCION"));
            opcion = MyUtils.devolverDato();
            switch (opcion) {
                case "1":
                    System.out.println("CREAR PERSONA EN EL SISTEMA");
                    //Lo primero es validar el dni, que no se puede repetir. Si algún trabajador ya creado tiene
                    //el mismo dni, el programa debe de salir de esta opción
                    dni = MyUtils.formatoDni();
                    //Creo un metodo dentro de la clase trabajador que valida que no exista alguno ya con ese dni
                    if(!trabajador.validarDniExistente(dni)){
                        System.out.println(MyUtils.imprimirPantalla("NOMBRE"));
                        nombre = MyUtils.devolverDato();
                        System.out.println(MyUtils.imprimirPantalla("FECHA NACIMIENTO"));
                        fecha = MyUtils.validarPatronFecha();
                        System.out.println(MyUtils.imprimirPantalla("DIRECCION"));
                        direccion = MyUtils.devolverDato();
                        trabajador = new Trabajador(nombre, fecha, dni, direccion);
                    }else{
                        System.out.println("EL TRABAJADOR CON ESTE DNI YA ESTA CREADO EN EL SISTEMA");
                    }
                    break;
                case "2":
                    String listadoTrabajadores = trabajador.listadoTrabajadores();
                    if(!listadoTrabajadores.isEmpty()){
                        System.out.println(listadoTrabajadores);
                        do{
                            System.out.println(MyUtils.imprimirPantalla("INDICE TRABAJADOR"));
                            opcion = MyUtils.devolverDato();
                        }while(Integer.parseInt(opcion) < 1 || Integer.parseInt(opcion) > Trabajador.contadorTrabajadores);
                        trabajador = Trabajador.personas[(Integer.parseInt(opcion) - 1)];
                        if (empresa.incluirTrabajadorEmpresa(trabajador)) {
                            System.out.println("EL TRABAJADOR SE HA INCORPORADO A LA EMPRESA DE FORMA CORRECTA");
                        } else {
                            System.out.println("EL TRABAJADOR NO PUEDE INCORPORARSE A LA EMPRESA");
                        }
                    }else{
                        System.out.println("NO HAY NINGUN TRABAJADOR DISPONIBLE PARA AÑADIR A LA EMPRESA");
                    }
                    break;
                case "3":
                    System.out.println("LA INFORMACION DE SU EMPRESA ES:\n" + empresa.mostrarInfoEmpresa());
                    break;
                case "4":
                    System.out.println("EL NUMERO DE TRABAJADORES ACTUALES DE SU EMPRESA ES: " + empresa.getNumeroTrabajadoresActurales());
                    break;
                case "5":
                    System.out.println();
                    if(empresa.getNumeroTrabajadoresActurales() > 0){
                        System.out.println("ESTOS SON LOS TRABAJADORES DE SU EMPRESA\n" + empresa.mostrarInformacionTrabajadores());

                    }else{
                        System.out.println("NO HAY NINGÚN TRABAJADOR DE ALTA EN SU EMPRESA");
                    }
                    break;
                case "6":
                    System.out.println("ELIMINAR TRABAJADOR DE LA EMPRESA");
                    dni = MyUtils.formatoDni();
                    if(empresa.eliminarTrabajadorEmpresa(dni)){
                        System.out.println("TRABAJADOR ELIMINADO DE LA EMPRESA");
                        empresa.reasignarArray();
                    }else{
                        System.out.println("NO SE HA ENCONTRADO EL TRABAJADOR EN EL LISTADO DE LA EMPRESA");
                    }
                    break;
                case "7":
                    System.out.println("ELIMINAR PERSONA DEL SISTEMA");
                    break;
                case "8":
                    System.out.println("SALIENDO DE GESTION EMPRESARIAL");
                    break;
                default:
                    System.out.println("OPCION INTRODUCIDA INCORRECTA. INTENTE DE NUEVO");
                    break;
            }
        }while(!opcion.equals("8"));
    }
}