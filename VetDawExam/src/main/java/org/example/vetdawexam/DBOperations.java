package org.example.vetdawexam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class DBOperations {

    //Con este método cargo la información de la tabla propietarios cuando me haga falta.

    public ObservableList<Propietario> cargaDatosPropietarioInicio() throws SQLException {

        String myStatment = "SELECT * FROM Propietario";

        ObservableList<Propietario> propietarios = FXCollections.observableArrayList();

        try(Connection con = SQLConnectionManagement.crearConexion();
            Statement statement = con.createStatement()){

            ResultSet rs = statement.executeQuery(myStatment);

            while(rs.next()){

                Propietario p;

                String dni = rs.getString("dni");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String telefono = rs.getString("Telefono");
                String direccion = rs.getString("Direcion");
                String email = rs.getString("Email");

                p = new Propietario(dni,nombre,apellido,telefono,direccion,email);

                propietarios.add(p);
            }

        }catch (SQLException e){
            System.out.println("Error al leer los datos de las consultas. " + e.getMessage());
        }

        return propietarios;

    }

    public ObservableList<Mascota> cargaDatosMascotasInicio() {

        String myStatment = "SELECT * FROM Mascota";

        ObservableList<Mascota> mascotas = FXCollections.observableArrayList();

        try(Connection con = SQLConnectionManagement.crearConexion();
            Statement statement = con.createStatement()){

            ResultSet rs = statement.executeQuery(myStatment);
            while(rs.next()){
                Mascota m;
                String pasaporte = rs.getString("Pasaporte");
                String nombre = rs.getString("Nombre");
                double peso = rs.getDouble("Peso");
                LocalDate fechaNacimiento = LocalDate.parse(rs.getString("FechaNacimiento"), MyFechas.formatoFechaSQL);
                String propDni = rs.getString("Propietario_dni");
                int tipo = rs.getInt("Tipo_idTipo");

                m= new Mascota(pasaporte, nombre, peso, fechaNacimiento, propDni, tipo);

                mascotas.add(m);
            }

        }catch (SQLException e){
            System.out.println("Error al leer los datos de las mascotas. " + e.getMessage());
        }

        return mascotas;
    }

    public ObservableList<TipoMascota> cargaDatosTiposMascotas() {

        String myStatment = "SELECT * FROM Tipo";
        ObservableList<TipoMascota> tiposMascotas = FXCollections.observableArrayList();

        try(Connection con = SQLConnectionManagement.crearConexion();
        Statement statement = con.createStatement()){

            ResultSet rs = statement.executeQuery(myStatment);
            while(rs.next()){
                TipoMascota t;
                int idTipo = rs.getInt("idTipo");
                String tipo = rs.getString("tipo");

                t = new TipoMascota(idTipo,tipo);
                tiposMascotas.add(t);

            }

        }catch(SQLException e){
            System.out.println("No se ha podido recuperar los datos de la tabla. " + e.getMessage());
        }

        return tiposMascotas;
    }

    public String insertarMascota(Mascota m) {

        String myStatement = "INSERT INTO Mascota VALUES(?,?,?,?,?,?)";

        String resultado = "Fallo al intentar insertar una mascota";

        try(Connection con = SQLConnectionManagement.crearConexion();
            PreparedStatement statement = con.prepareStatement(myStatement)){

            statement.setString(1,m.getPasaporte());
            statement.setString(2,m.getNombre());
            statement.setDouble(3,m.getPeso());
            statement.setString(4,MyFechas.formatearFechaSQL(m.getFechaNacimiento()));
            statement.setString(5,m.getPropietarioDni());
            statement.setInt(6,m.getTipoMascota());

            statement.executeUpdate();

            resultado = "Se ha registrado la mascota";

        }catch (SQLException e){
            System.out.println("Error al insertar una mascota. " + e.getMessage());
        }

        return resultado;
    }

    public int insertarPropietario(Propietario p) {

        int rowsAffected = -1;

        String myStatment = "INSERT INTO Propietario VALUES(?,?,?,?,?,?)";

        try(Connection con = SQLConnectionManagement.crearConexion();
        PreparedStatement statement = con.prepareStatement(myStatment)){

            statement.setString(1, p.getDni());
            statement.setString(2, p.getNombre());
            statement.setString(3, p.getApellido());
            statement.setString(4, p.getTelefono());
            statement.setString(5, p.getDireccion());
            statement.setString(6, p.getEmail());

            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error al insertar un Propietario. " + e.getMessage());
        }

        return rowsAffected;
    }

    public Propietario obtenerPropietarioDni(String propietarioDni) {

        Propietario p = null;

        String myStatement = "SELECT * FROM Propietario WHERE dni = ?";

        try(Connection con = SQLConnectionManagement.crearConexion();
        PreparedStatement statement = con.prepareStatement(myStatement)){

            statement.setString(1, propietarioDni);

            ResultSet rs = statement.executeQuery(myStatement);
            while(rs.next()){

                String dni = rs.getString("dni");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String telefono = rs.getString("Telefono");
                String direccion = rs.getString("Direcion");
                String email = rs.getString("Email");

                p = new Propietario(dni,nombre,apellido,telefono,direccion,email);

            }

        }catch(SQLException e){
            System.out.println("Error al obtener un Propietario. " + e.getMessage());
        }

        return p;
    }

    public int registrarConsulta(Mascota mascota, String fecha, int duracion, String comentario) {

        int rowsAffected = -1;

        String myStatement = "INSERT INTO Consulta (Fecha, Duracion, Observaciones, Mascota_Pasaporte, " +
                "Mascota_Propietario_dni) VALUES(?,?,?,?,?)";

        try(Connection con = SQLConnectionManagement.crearConexion();
        PreparedStatement statement = con.prepareStatement(myStatement)){
            statement.setString(1,fecha);
            statement.setInt(2,duracion);
            statement.setString(3,comentario);
            statement.setString(4,mascota.getPasaporte());
            statement.setString(5, mascota.getPropietarioDni());

            rowsAffected = statement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Error al realizar el insert. " + e.getMessage());
        }


        return rowsAffected;
    }

    public Mascota obtenerMascotaPorPasaporte(String pasaporte) {

        Mascota m = null;

        String myStatement = "SELECT * FROM Mascota WHERE Pasaporte = ?";

        try(Connection con = SQLConnectionManagement.crearConexion();
        PreparedStatement statement = con.prepareStatement(myStatement)){

            statement.setString(1,pasaporte);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String pas = rs.getString("Pasaporte");
                String nombre = rs.getString("Nombre");
                double peso = rs.getDouble("Peso");
                LocalDate fnac = LocalDate.parse(rs.getString("FechaNacimiento"),MyFechas.formatoFechaSQL);
                String dni = rs.getString("Propietario_dni");;
                int tipo = rs.getInt("Tipo_idTipo");

                m = new Mascota(pas, nombre, peso, fnac, dni, tipo);
            }

        }catch(SQLException e){
            System.out.println("Error al obtener un Mascota. " + e.getMessage());
        }

        return m;
    }
}
