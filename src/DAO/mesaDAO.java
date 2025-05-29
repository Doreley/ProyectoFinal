/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author chaco
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Modelo.ModeloMesa;
        
public class mesaDAO {
      private Connection conn;

    public mesaDAO(Connection conn) {
        this.conn = conn;
    }

    // Obtener todas las mesas
    public List<ModeloMesa> obtenerTodas() {
        List<ModeloMesa> lista = new ArrayList<>();
        String sql = "SELECT * FROM mesas ORDER BY numero";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ModeloMesa m = new ModeloMesa();
                m.setId(rs.getInt("id"));
                m.setNumero(rs.getInt("numero"));
                m.setEstado(rs.getString("estado"));
                lista.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Cambiar estado de una mesa
    public boolean actualizarEstado(int numeroMesa, String nuevoEstado) {
        String sql = "UPDATE mesas SET estado = ? WHERE numero = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, numeroMesa);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener estado actual de una mesa
    public String obtenerEstado(int numeroMesa) {
        String sql = "SELECT estado FROM mesas WHERE numero = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numeroMesa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("estado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desconocido";
    }

    // Verificar si una mesa existe por número
    public boolean existeMesa(int numeroMesa) {
        String sql = "SELECT 1 FROM mesas WHERE numero = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numeroMesa);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
