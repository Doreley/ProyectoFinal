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
import modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class empleadoDAO {
    
    public boolean guardarEmpleado(Empleado e) {
        String sql = "INSERT INTO empleados(nombre, apellido, cargo, telefono, direccion, estado, fecha_ingreso, salario, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new CreateConection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getCargo());
            ps.setString(4, e.getTelefono());
            ps.setString(5, e.getDireccion());
            ps.setBoolean(6, e.isEstado());
            ps.setDate(7, Date.valueOf(e.getFechaIngreso()));
            ps.setDouble(8, e.getSalario());
            ps.setString(9, e.getEmail());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Empleado> listarEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Connection conn = new CreateConection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setCargo(rs.getString("cargo"));
                e.setTelefono(rs.getString("telefono"));
                e.setDireccion(rs.getString("direccion"));
                e.setEstado(rs.getBoolean("estado"));
                e.setFechaIngreso(rs.getDate("fecha_ingreso").toString());
                e.setSalario(rs.getDouble("salario"));
                e.setEmail(rs.getString("email"));
                lista.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    
}
