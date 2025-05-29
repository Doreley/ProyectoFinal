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
import java.util.List;

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
    
}
