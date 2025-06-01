/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author chaco
 */
import conection.CreateConection;
import Modelo.Usuario;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;


public class usuarioDao {

    private Connection conn;
    
    public usuarioDao(Connection conn) {
    this.conn = conn;
}
    
//    public Usuario login(String nombreUsuario, String contrasena) {
//        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
//        try (Connection conn = new CreateConection().getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, nombreUsuario);
//            ps.setString(2, contrasena);
//
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                Usuario u = new Usuario();
//                u.setId(rs.getInt("id"));
//                u.setNombreUsuario(rs.getString("nombre_usuario"));
//                u.setContrasena(rs.getString("contrasena"));
//                u.setPuesto(rs.getString("rol"));
//                u.setIdEmpleado(rs.getInt("empleado_id"));
//                return u;
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return null;
//    }
    
    public Usuario validarLogin(String usuario, String contrasena) {
    String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, usuario);
        ps.setString(2, contrasena);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setIdEmpleado(rs.getInt("id_empleado"));
            u.setNombreUsuario(rs.getString("nombre_usuario"));
            u.setContrasena(rs.getString("contrasena"));
            u.setPuesto(rs.getString("rol")); 
            u.setEstado(rs.getBoolean("estado")); 
            return u;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
    
}
