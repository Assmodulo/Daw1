package org.example;

import java.sql.*;
import java.util.ArrayList;
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

    public List<Producto> buscarPorReferencia(String referencia){
        List<Producto> productos = new LinkedList<>();

        String Mystatement = "SELECT * FROM productos WHERE referencia = ?";

        try(Connection con = SQLDBMan.getConnection();
            PreparedStatement statement = con.prepareStatement(Mystatement);){

            statement.setString(1, referencia);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                String ref = rs.getString(2);
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

        }catch (Exception e){
            System.out.println("Error al obtener los datos de la BD " + e.getMessage());
        }

        return productos;
    }

    public List<Producto> buscarPorIdTipo(int idTipo){
        List<Producto> productos = new LinkedList<>();

        String Mystatement = "SELECT * FROM productos WHERE id_Tipo = ? ORDER BY id";

        try(Connection con = SQLDBMan.getConnection();
        PreparedStatement statement = con.prepareStatement(Mystatement)){

            statement.setInt(1, idTipo);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){

                int id = rs.getInt(1);
                String ref = rs.getString(2);
                String nombre = rs.getString(3);
                String descripcion = rs.getString(4);
                int tipo = rs.getInt(5);
                int cantidad = rs.getInt(6);
                double precio = rs.getDouble(7);
                int descuento = rs.getInt(8);
                int iva = rs.getInt(9);
                boolean aIva = rs.getBoolean(10);


                Producto p = new Producto(id, ref, nombre, descripcion, tipo, cantidad, precio,
                        descuento, iva, aIva);

                productos.add(p);

            }

        } catch (Exception e) {
            System.out.println("Error al obtener los datos de la BD " + e.getMessage());
        }

        return productos;
    }


    public List<Producto> buscarPorCantidad(int cant){
        List<Producto> productos = new LinkedList<>();

        String Mystatement = "SELECT * FROM productos WHERE cantidad = ? ORDER BY id";

        try(Connection con = SQLDBMan.getConnection();
            PreparedStatement statement = con.prepareStatement(Mystatement)){

            statement.setInt(1, cant);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){

                int id = rs.getInt(1);
                String ref = rs.getString(2);
                String nombre = rs.getString(3);
                String descripcion = rs.getString(4);
                int tipo = rs.getInt(5);
                int cantidad = rs.getInt(6);
                double precio = rs.getDouble(7);
                int descuento = rs.getInt(8);
                int iva = rs.getInt(9);
                boolean aIva = rs.getBoolean(10);


                Producto p = new Producto(id, ref, nombre, descripcion, tipo, cantidad, precio,
                        descuento, iva, aIva);

                productos.add(p);

            }

        } catch (Exception e) {
            System.out.println("Error al obtener los datos de la BD " + e.getMessage());
        }

        return productos;
    }


    public int eliminarPorReferencia(String referencia){
        String Mystatement = "DELETE FROM productos WHERE referencia = ?";
        int rowsAffected = -1;

        try(Connection con = SQLDBMan.getConnection();
        PreparedStatement statement = con.prepareStatement(Mystatement)){

            statement.setString(1, referencia);
            rowsAffected = statement.executeUpdate();

        }catch (Exception e){
            System.out.println("Error al eliminar un producto de la BD " + e.getMessage());
        }
        return rowsAffected;
    }

    public int crearNuevoTipoProducto(Tipo tipo){

        String MyStatement = "INSERT INTO tipos VALUES(?)";

        int rowsAffected = -1;

        try(Connection con = SQLDBMan.getConnection();
        PreparedStatement statement = con.prepareStatement(MyStatement)){


            statement.setString(1,tipo.getTipo().toString());
            rowsAffected = statement.executeUpdate();

        }catch (Exception e){
            System.out.println("Error al crear un producto de la BD " + e.getMessage());
        }

        return rowsAffected;
    }

    public int insertarNuevoProducto(Producto producto){
        String MyStatement = "INSERT INTO productos (referencia, nombre, descripcion, id_tipo, cantidad," +
                "precio, descuento, iva, aplicar_dto) VALUES(?,?,?,?,?,?,?,?,?)";

        int rowsAffected = -1;

        try(Connection con = SQLDBMan.getConnection();
        PreparedStatement statement = con.prepareStatement(MyStatement)){

            statement.setString(1, producto.getReferencia());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setInt(4,producto.getIdTipo());
            statement.setInt(5,producto.getCantidad());
            statement.setDouble(6,producto.getPrecio());
            statement.setInt(7,producto.getDescuento());
            statement.setInt(8,producto.getIva());
            statement.setBoolean(9,producto.isAplicarDescuento());

            rowsAffected = statement.executeUpdate();

        }catch (Exception e){
            System.out.println("Error al insertar un producto de la BD " + e.getMessage());
        }

        return rowsAffected;
    }

    public int modificarRegistro(Producto producto){

        String MyStatement = """
                update productos set descripcion = ?
                , cantidad = ?, precio = ?
                , descuento = ?, aplicar_dto = ?
                 where referencia = ?""";

        int rowsAffected = -1;

        try(Connection con = SQLDBMan.getConnection(); PreparedStatement statement = con.prepareStatement(MyStatement)){

            statement.setString(1, producto.getDescripcion());
            statement.setInt(2, producto.getCantidad());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getDescuento());
            statement.setBoolean(5, producto.isAplicarDescuento());
            statement.setString(6, producto.getReferencia());

            rowsAffected = statement.executeUpdate();


        }catch (Exception e){
            System.out.println("Error al modificar un producto de la BD " + e.getMessage());
        }

        return rowsAffected;
    }

    public List<Tipo> obtenerTipos(){
        String MyStatement = """
                select * from tipos
                """;

        List<Tipo> tipos = new LinkedList<>();

        try(Connection con = SQLDBMan.getConnection();
        PreparedStatement statement = con.prepareStatement(MyStatement)){

            ResultSet rs = statement.executeQuery(MyStatement);

            while(rs.next()){

                int id = rs.getInt(1);
                String tipo = rs.getString(2);

                Tipo t = new Tipo(id, tipo);

                tipos.add(t);

            }


        }catch (Exception e){
            System.out.println("Error al obtener los datos de la BD " + e.getMessage());
        }

        return tipos;
    }

    public int creacionNuevoTipo(String tipo){
        String MyStatement = """
                insert into tipos (tipo) values (?)
                """;

        int rowsAffected = -1;

        try(Connection con = SQLDBMan.getConnection();
            PreparedStatement statement = con.prepareStatement(MyStatement)){

            statement.setString(1, tipo);
            rowsAffected = statement.executeUpdate();

        }catch (Exception e){
                System.out.println("Error al crear un tipo de la BD " + e.getMessage());
        }

        return rowsAffected;
    }
}
