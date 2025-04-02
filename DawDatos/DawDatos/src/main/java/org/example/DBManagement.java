package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DBManagement {

    public List<Producto> obtenerTodosProductos(){
        List<Producto> productos = new LinkedList<>();

        try(Connection con = SQLDBMan.getConnection();
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT * FROM productos");
            while(rs.next()){

                int id = rs.getInt(1);
                String referencia = rs.getString(2);
                String nombre = rs.getString(3);
                String descripcion = rs.getString(4);
                int tipo = rs.getInt(5);
                int cantidad = rs.getInt(6);
                double precio = rs.getDouble(7);
                int descuento = rs.getInt(8);
                int iva = rs.getInt(9);
                boolean aIva = rs.getBoolean(10);

                Producto p = new Producto(id, referencia, nombre, descripcion, tipo, cantidad, precio,
                        descuento, iva, aIva);

                productos.add(p);
            }
        }catch (SQLException e){
            System.out.println("Error al obtener los datos de la BD " + e.getMessage());
        }
        return productos;
    }

}
