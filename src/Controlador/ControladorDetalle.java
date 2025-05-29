/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author chaco
 */
import DAO.DetallePedidoDAO;
import Modelo.ModeloDetallePedido;
import java.util.List;

public class ControladorDetalle {
      private DetallePedidoDAO detalleDAO;

    public ControladorDetalle(DetallePedidoDAO detalleDAO) {
        this.detalleDAO = detalleDAO;
    }

    
    public boolean guardarDetalles(List<ModeloDetallePedido> detalles) {
        return detalleDAO.insertarDetalles(detalles);
    }
    
    
}
