/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author chaco
 */
import DAO.PedidoDAO;
import Modelo.ModeloPedido;
import java.util.List;

public class ControladorPedido {
      private PedidoDAO pedidoDAO;

    public ControladorPedido(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    // Crear un nuevo pedido
    public int registrarPedido(ModeloPedido pedido) {
        return pedidoDAO.crearPedido(pedido);
    }

    // Listar pedidos de una mesa específica
    public List<ModeloPedido> obtenerPedidosPorMesa(int mesaId) {
        return pedidoDAO.listarPorMesa(mesaId);
    }
}
