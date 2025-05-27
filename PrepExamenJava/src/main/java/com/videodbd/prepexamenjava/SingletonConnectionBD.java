package com.videodbd.prepexamenjava;

public class SingletonConnectionBD {

    //Todo copiado de otros ejercicios. Aquí tengo también ejemplo de lectura de ficheros en caso que haga falta.
    package com.videoDBD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class SQLSingleConnection {

        private final String driver;
        private final String url;
        private final String schema;
        private final String user;
        private final String pass;



        public static Connection crearConexion() {

            Connection con = null;

            SQLSingleConnection sql = null;

            String driver = "", url = "", schema = "", user = "", pass = "";


            try (FileReader fichero = new FileReader("src/main/resources/application.dat");
                 BufferedReader lector = new BufferedReader(fichero)) {

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

                sql = new SQLSingleConnection(driver, url, schema, user, pass);

            } catch (IOException e) {
                System.out.println("Error al leer el archivo de conexión a la BD. " + e.getMessage());
            }

            try {
                if (sql != null) {
                    Class.forName(sql.getDriver());
                    con = DriverManager.getConnection(sql.url + sql.schema, sql.user, sql.pass);
                }

            } catch (ClassNotFoundException e) {
                System.out.println("Error al conectar con el driver " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Fallo al conectar con la base de datos" + e.getMessage());
            }


            return con;
        }


        //En principio no tengo que crear un objeto de esta clase para el singleton, lo dejo aquí por si acaso

        private SQLSingleConnection(String driver, String url, String schema, String user, String pass) {
            this.driver = driver;
            this.url = url;
            this.schema = schema;
            this.user = user;
            this.pass = pass;

        }

        public String getDriver() {
            return driver;
        }

        public String getUrl() {
            return url;
        }

        public String getSchema() {
            return schema;
        }

        public String getUser() {
            return user;
        }

        public String getPass() {
            return pass;
        }
    }
}
