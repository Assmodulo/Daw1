package com.videoDBD;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        Videoclub v = null;

        Cliente c;

        Articulo a;

        Alquiler al;

        DBoperations db = new DBoperations();

        LinkedList<Videoclub> listadoVideoclubs;

        LinkedList<Cliente> clientes;

        LinkedList<Articulo> articulos;

        LinkedList<Alquiler> alquileres;

        String opcion, listado, cif, codigo;

        int resultadoOperacion = -1;

        MyUtils.leerDatosArchivosVariablesNecesarias();

        do{

            System.out.println(MyUtils.devolverOpcionesMenu());

            opcion = MyUtils.seleccionarOpcion();

                switch(opcion){
                    case "1":

                        v = MyUtils.crearVideoclub();

                        listadoVideoclubs = db.getListadoVideoclubs();

                        if(MyUtils.validarVideoClub(v.getCif(), listadoVideoclubs)){

                            System.out.println("El cif de su nuevo videoclub ya existe. Tendrá que introducir los datos de nuevo");

                        }else{
                            resultadoOperacion = db.insertarNuevoVideoClub(v);

                            if(resultadoOperacion > 0){
                                System.out.println(resultadoOperacion + " fila/s han sido afectadas");
                            }
                        }

                        break;
                    case "2":

                        listadoVideoclubs = db.getListadoVideoclubs();

                        listado = MyUtils.listadoVideoclubs(listadoVideoclubs);

                        System.out.println(listado);

                        System.out.println("Inserte el CIF del videoclub que desea dar de baja");

                        cif = MyUtils.insertarCif();

                        if(MyUtils.validarVideoClub(cif, listadoVideoclubs)){

                            resultadoOperacion = db.eliminarVideoClub(cif);

                            if(resultadoOperacion > 0){

                                System.out.println("Se ha eliminado el videoclub con CIF: " + cif);
                            }

                        }else{
                            System.out.println("No se ha encontrado un videoclub con ese cif");
                        }
                        break;
                    case "3":

                        listadoVideoclubs = db.getListadoVideoclubs();

                        listado = MyUtils.listadoVideoclubs(listadoVideoclubs);

                        System.out.println(listado);

                        System.out.println("Inserte el CIF del videoclub que desea dar de baja");

                        cif = MyUtils.insertarCif();

                        if(MyUtils.validarVideoClub(cif, listadoVideoclubs)) {

                            v = db.seleccionarVideoClub(cif);

                            System.out.println("El videoclub seleccionaldo es: \n" + v.toString());

                        }else{

                            System.out.println("No se ha podido seleccionar el videoclub");
                            
                        }
                        break;
                    case "4":

                        if (v != null) {
                            try {

                                c = MyUtils.darDeAltaNuevoCliente(v.getCif());

                                MyUtils.validarSocioYaExistente(c, v);

                                resultadoOperacion = db.insertarNuevoCliente(c,v);

                                if(resultadoOperacion > 0){
                                    System.out.println("Se han modificado " + resultadoOperacion + " fila/s");
                                }else{
                                    System.out.println("Ha surgido algun problema con la operación");
                                }

                            } catch (FechaPosteriorActualException e) {
                                System.out.println(e.getMessage());
                            } catch (MenorDeEdadException e) {
                                System.out.println(e.getMessage());
                            } catch (DniExistenteException e){
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Debe de seleccionar un videoclub con el que trabajar o crear uno nuevo");
                        }

                        break;
                    case "5":

                        if (v != null) {
                            a = MyUtils.crearNuevoArticulo(v);

                            resultadoOperacion = db.insertarNuevoArticulo(a, v);

                            if(resultadoOperacion > 0){
                                System.out.println("Se ha dado de alta un nuevo artículo");
                            }else{
                                System.out.println("No se ha podido insertar el artículo por algún motivo");
                            }
                        } else {
                            System.out.println("Ningún videclub seleccionado. Seleccione o cree 1");
                        }
                        break;
                    case "6":

                        if (v != null) {
                            System.out.println(MyUtils.mostrarArticulosDeUnVideoclub(v));

                            System.out.println("\n Inserte el código del artículo que desea eliminar");

                            if(MyUtils.eliminarArticuloPorReferencia(v)){
                                System.out.println("El artículo se ha eliminado del catálogo de este videoclub");
                            }else{
                                System.out.println("No se ha podido encontrar el artículo para su eliminación.");
                            }

                        } else {
                            System.out.println("Ningún videoclub seleccionado o creado. Seleccione o cree 1");
                        }
                        break;
                    case "7":

                        if(v != null){

                            listado = "";

                            clientes = MyUtils.obtenerListadoClientesActivos(v);

                            if (!clientes.isEmpty()) {

                                for(Cliente cliente : clientes){
                                    listado += cliente.toString() +"\n";
                                }

                                System.out.println(listado);

                                System.out.println("\n Indique el código del cliente que va a realizar la operación.");

                                c = MyUtils.seleccionarCliente(clientes);

                                if(c != null){

                                    listado = "";

                                    articulos = MyUtils.obtenerListadoArticulosAlquiler(v);

                                    if(!articulos.isEmpty()){

                                        for(Articulo articulo : articulos){
                                            listado += articulo.toString() +"\n";
                                        }

                                        System.out.println("Listado de artículos disponibles");

                                        System.out.println(listado);

                                        a = MyUtils.seleccionarArticulo(articulos);

                                        if(a != null){

                                            resultadoOperacion = db.insertarNuevoAlquiler(v.getCif(), a.getCod_articulo(), c.getCod_cliente());

                                            if(resultadoOperacion > 0){
                                                System.out.println("Se ha realizado un nuevo alquiler.");
                                            }else{
                                                System.out.println("Tras todos estos pasos y estos ifs, al final nada. No se ha realizado el alquiler");
                                            }

                                        }else{
                                            System.out.println("No se ha seleccionado un artículo correcto.");
                                        }

                                    }else{
                                        System.out.println("No existen artículos disponibles para alquilar.");
                                    }

                                }else{
                                    System.out.println("No existe un cliente con ese código.");
                                }
                            } else {
                                System.out.println("No hay clientes que cumplan los requisitos para realizar un alquiler");
                            }

                        }else{
                            System.out.println("Debe de crear o seleccionar un videoclub primero");
                        }
                        break;

                    case "8":

                        if(v != null){

                            al = MyUtils.seleccionarAlquilerFinalizar(v);

                            System.out.println("Este es el alquiler que se va a finalizar\n " +
                                    db.obtenerDatosCompletosAlquiler(al.getCodArticulo(), al.getCodSocio(), al.getF_alquiler()));

                            System.out.println(MyUtils.validarPeriodoDeDevolucion(al));

                            resultadoOperacion = db.realizarDevolucionArticulo(al.getCodArticulo(), al.getCodSocio(), al.getF_alquiler(), LocalDateTime.now());

                            if(resultadoOperacion > 0){
                                System.out.println("Se ha realizado el update de " + resultadoOperacion + " registro/s con éxito");
                            }else{
                                System.out.println("Ha ocurrido un fallo inesperado y no se han podido realizar las operaciones solicitadas.");
                            }

                        }else{
                            System.out.println("Debe de crear o seleccionar un videoclub para realizar esta operación");
                        }

                        break;
                    case "9":
                        try {
                            MyUtils.guardadoEnFicheroDeVariablesNecesarias();
                        } catch (IOException e) {
                            System.out.println("Error en el manejo de ficheros. " +e.getMessage());
                        }
                        System.out.println("Saliendo de la base de datos");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }


        }while(!opcion.equals("9"));
    }
}