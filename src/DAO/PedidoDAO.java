/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author chaco
 */
import Modelo.ModeloPedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class PedidoDAO {
    private Connection conn;

    public PedidoDAO(Connection conn) {
        this.conn = conn;
    }

    
    public int insertarPedido(ModeloPedido pedido) {
        String sql = "INSERT INTO pedidos (id_cliente, id_usuario, mesa_id, total, impuesto) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pedido.getIdCliente());
            ps.setInt(2, pedido.getIdUsuario());
            ps.setInt(3, pedido.getMesaId());
            ps.setDouble(4, pedido.getTotal());
            ps.setDouble(5, pedido.getImpuesto());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); // devuelve el ID del nuevo pedido
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // error
    }

    // 🔹 Obtener todos los pedidos de una mesa específica
    public List<ModeloPedido> listarPorMesa(int mesaId) {
        List<ModeloPedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE mesa_id = ? ORDER BY fecha DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mesaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ModeloPedido p = new ModeloPedido();
                p.setId(rs.getInt("id"));
                p.setIdCliente(rs.getInt("id_cliente"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setMesaId(rs.getInt("mesa_id"));
                p.setFecha(rs.getTimestamp("fecha"));
                p.setTotal(rs.getDouble("total"));
                p.setImpuesto(rs.getDouble("impuesto"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }  
   
    public List<ModeloPedido> obtenerPedidosPendientes() {
    List<ModeloPedido> lista = new ArrayList<>();
    String sql = "SELECT * FROM pedidos WHERE pagado = false ORDER BY fecha DESC";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ModeloPedido p = new ModeloPedido();
            p.setId(rs.getInt("id"));
            p.setMesaId(rs.getInt("mesa_id"));
            p.setFecha(rs.getTimestamp("fecha"));
            lista.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
    public ModeloPedido obtenerPorId(int idPedido) {
    ModeloPedido pedido = null;
    String sql = "SELECT * FROM pedidos WHERE id = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idPedido);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            pedido = new ModeloPedido();
            pedido.setId(rs.getInt("id"));
            pedido.setIdCliente(rs.getInt("id_cliente"));
            pedido.setIdUsuario(rs.getInt("id_usuario"));
            pedido.setMesaId(rs.getInt("mesa_id"));
            pedido.setFecha(rs.getTimestamp("fecha"));
            pedido.setTotal(rs.getDouble("total"));
            pedido.setImpuesto(rs.getDouble("impuesto"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return pedido;
}
   
    public int obtenerUltimoPedidoPorMesa(int idMesa) {
    String sql = "SELECT id FROM pedidos WHERE mesa_id = ? ORDER BY fecha DESC LIMIT 1";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idMesa);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");  
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return -1;  
}
  
    
}
