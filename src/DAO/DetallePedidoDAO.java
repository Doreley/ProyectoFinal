/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author chaco
 */
import Modelo.ModeloDetallePedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class DetallePedidoDAO {
    private Connection conn;

    public DetallePedidoDAO(Connection conn) {
        this.conn = conn;
    }

   
    public boolean insertarDetalle(ModeloDetallePedido detalle) {
        String sql = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario, subtotal) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detalle.getIdPedido());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());
            ps.setDouble(5, detalle.getSubtotal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertarDetalles(List<ModeloDetallePedido> listaDetalles) {
        for (ModeloDetallePedido d : listaDetalles) {
            if (!insertarDetalle(d)) {
                return false;
            }
        }
        return true;
    }
    
    public List<ModeloDetallePedido> listarPorPedido(int idPedido) {
    List<ModeloDetallePedido> lista = new ArrayList<>();
     String sql = "SELECT dp.*, p.nombre " +
                 "FROM detalle_pedido dp " +
                 "JOIN productos p ON dp.id_producto = p.id " +
                 "WHERE dp.id_pedido = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idPedido);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ModeloDetallePedido d = new ModeloDetallePedido();
            d.setId(rs.getInt("id"));
            d.setIdPedido(rs.getInt("id_pedido"));
            d.setIdProducto(rs.getInt("id_producto"));
            d.setNombreProducto(rs.getString("nombre"));
            d.setCantidad(rs.getInt("cantidad"));
            d.setPrecioUnitario(rs.getDouble("precio_unitario"));
            d.setSubtotal(rs.getDouble("subtotal"));
            lista.add(d);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
    
  
}
