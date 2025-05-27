package com.videodbd.prepexamenjava;

public class EjemploDBOperations {

    //Los return de rowsAffected no me hacen falta en principio.
    public class DBoperations {

        //Ejemplo de operación hacia base de datos
        public int insertarNuevoVideoClub(Videoclub videoClub){
            int rowsAffected=-1;

            String instruccion = "INSERT INTO videoclubs VALUES(?,?,?,?)";

            try(Connection con = SQLSingleConnection.crearConexion();
                PreparedStatement statement = con.prepareStatement(instruccion)){

                statement.setString(1, videoClub.getCif());
                statement.setString(2, videoClub.getNombre());
                statement.setString(3, videoClub.getDireccion());
                statement.setString(4, videoClub.getTelefono());

                //Ejemplos de fechas a sql
                statement.setString(5, MyUtils.formatearFechaSQL(c.getF_nacimiento()));
                statement.setString(6, MyUtils.formatearFechaSQL(c.getF_alta()));

                rowsAffected = statement.executeUpdate();

            }catch (Exception e) {
                System.out.println("Error al ejecutar el statement. " + e.getMessage());
            }

            return rowsAffected;
        }

        //Ejemplo de recuperación de datos desde BD. Tener cuidado si hay que recuperar LocalDates o DAteTime
        public LinkedList<Videoclub> getListadoVideoclubs() {
            LinkedList<Videoclub> listadoVideoclubs = new LinkedList<>();

            String instruccion = "SELECT * FROM videoclubs";

            try(Connection con = SQLSingleConnection.crearConexion();
                Statement statement = con.createStatement()){

                ResultSet rs = statement.executeQuery(instruccion);

                while (rs.next()) {
                    Videoclub videoClub;

                    String cif = rs.getString("cod_videoclub");
                    String nombre = rs.getString("nombre_videoclub");
                    String direccion = rs.getString("direccion");
                    String telefono = rs.getString("telefono");

                    //Por si acaso me dejo ejemplos de recuperar strings para fechas
                    LocalDate f_nac = LocalDate.parse(rs.getString(5), MyUtils.formatoFechaSQL);
                    LocalDate f_alta = LocalDate.parse(rs.getString(6), MyUtils.formatoFechaSQL);

                    videoClub = new Videoclub(cif, nombre, direccion, telefono);

                    listadoVideoclubs.add(videoClub);
                }
            } catch (SQLException e) {

                System.out.println("Error al ejecutar el statement. " + e.getMessage());
            }

            return listadoVideoclubs;
        }

}
