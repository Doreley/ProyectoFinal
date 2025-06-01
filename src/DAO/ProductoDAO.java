/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author chaco
 */
import Modelo.modeloProducto;
import conection.CreateConection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class ProductoDAO {
     private Connection conn;

    public ProductoDAO() throws SQLException {
        this.conn = new CreateConection().getConnection();
    }

    public List<modeloProducto> obtenerTodos() {
        List<modeloProducto> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, precio FROM productos";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                modeloProducto p = new modeloProducto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public double obtenerPrecioPorId(int idProducto) {
        String sql = "SELECT precio FROM productos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("precio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
}
