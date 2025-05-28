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

public class usuarioDao {
    public Usuario login(String nombreUsuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        try (Connection conn = new CreateConection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setContrasena(rs.getString("contrasena"));
                u.setPuesto(rs.getString("rol"));
                u.setIdEmpleado(rs.getInt("empleado_id"));
                return u;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
}
