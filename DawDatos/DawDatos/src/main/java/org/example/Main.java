package org.example;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        controlDeFicheros();

        DBManagement db = new DBManagement();

        List<Producto> listado = db.obtenerTodosProductos();

        for (Producto producto : listado) {
            System.out.println(producto);
        }

    }

    public static void controlDeFicheros() {

        File carpeta = new File("./Recursos");
        carpeta.mkdir();

        File fichero = new File(carpeta,"application.dat");
        try {
            if(!fichero.exists()){

                fichero.createNewFile();
                if(fichero.length() == 0){
                    guardarDatosEnFichero();
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo " + e.getMessage());
        }
    }

    public static void guardarDatosEnFichero() {

        try(FileWriter fichero = new FileWriter("./Recursos/application.dat");
            BufferedWriter escritor = new BufferedWriter(fichero)){

            escritor.write("com.mysql.cj.jdbc.Driver,jdbc:mysql://localhost:3306/,dawdata,developer,1234");

        }catch (IOException e) {
            System.out.println("Error al guardar datos en el archivo " + e.getMessage());
        }
    }
}