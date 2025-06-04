package org.example.tucochealquileres;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Clase que gestiona la conexión a la base de datos de gestión de Parkings y Alquiler de coches
 */
public class SingletonConnectionSQL {


    private final String driver;
        private final String url;
        private final String schema;
        private final String user;
        private final String pass;


    /**
     * Este es el método crearConexion. Lee los datos necesarios para crear la conexión a la base de datos desde
     * un fichero externo
     * @return variable con de la clase Connection con una conexión creada a nuestra base de datos
     */
    public static Connection crearConexion() {

            Connection con = null;

            SingletonConnectionSQL sql = null;

            String driver = "", url = "", schema = "", user = "", pass = "";

            //Bloque de instrucción con un try catch con recursos para leer los datos necesarios para establecer
            //la conexión con la base de datos

        try (FileReader fichero = new FileReader("src/main/resources/org/example/tucochealquileres/datosConexionSql.dat");
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

                sql = new SingletonConnectionSQL(driver, url, schema, user, pass);

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

    /**
     * Contructor privado de la clase de conexión a la base de datos. Utilizado según el patrón singleton
     * @param driver El driver de conexión a la base de datos
     * @param url   La url de la base de datos
     * @param schema El esquema al que se conecta para trabajar
     * @param user El usuario ya creado en el modelo que va a realizar las operaciones requeridas
     * @param pass El pass del usuario requerido para conectarse
     */
        private SingletonConnectionSQL(String driver, String url, String schema, String user, String pass) {
            this.driver = driver;
            this.url = url;
            this.schema = schema;
            this.user = user;
            this.pass = pass;

        }

    /**
     * Getter del Driver de la base de datos
     * @return String con el valor del driver
     */
    public String getDriver() {
            return driver;
        }


    }


