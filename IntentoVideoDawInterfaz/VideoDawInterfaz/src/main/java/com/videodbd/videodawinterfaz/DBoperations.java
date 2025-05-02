package com.videodbd.videodawinterfaz;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class DBoperations {

    public int insertarNuevoVideoClub(Videoclub videoClub){
        int rowsAffected=-1;

        String instruccion = "INSERT INTO videoclubs VALUES(?,?,?,?)";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, videoClub.getCif());
            statement.setString(2, videoClub.getNombre());
            statement.setString(3, videoClub.getDireccion());
            statement.setString(4, videoClub.getTelefono());

            rowsAffected = statement.executeUpdate();

        }catch (Exception e) {
            System.out.println("Error al ejecutar el statement. " + e.getMessage());
        }

        return rowsAffected;
    }


}
