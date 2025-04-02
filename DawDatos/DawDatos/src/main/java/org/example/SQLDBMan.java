package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDBMan {

    private static String driver;
    private static String url;
    private static String schema;
    private static String user;
    private static String pass;



    public static Connection getConnection() {
        Connection con = null;

        try(FileReader fichero = new FileReader("./Recursos/application.dat");
            BufferedReader lector = new BufferedReader(fichero)){

            String linea = lector.readLine();

            while (linea != null) {
                String[] datos = linea.split(",");
                driver = datos[0];
                url = datos[1];
                schema = datos[2];
                user = datos[3];
                pass = datos[4];

                linea = lector.readLine();
            }

        }catch (IOException e){
            System.out.println("Error al leer el archivo " + e.getMessage());
        }

        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url+schema,user,pass);
        }catch (ClassNotFoundException e){
            System.out.println("Error al conectar con el driver " + e.getMessage());
        }catch (SQLException e){
            System.out.println("Fallo al conectar con la base de datos" + e.getMessage());
        }

        return con;
    }

}
