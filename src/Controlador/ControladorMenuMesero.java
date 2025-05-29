/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author chaco
 */
import Vista.MenuMesero;
import Vista.VistaAgregarPedido;
import conection.CreateConection;
import Modelo.ModeloPedido;
import DAO.PedidoDAO;
import Modelo.ModeloDetallePedido;
import DAO.DetallePedidoDAO;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ControladorMenuMesero {

    private MenuMesero vista;
    private int idUsuario;  

    public ControladorMenuMesero(MenuMesero vista, int idUsuario) {
        this.vista = vista;
        this.idUsuario = idUsuario;

        
        this.vista.btnAgregarPedido.addActionListener(e -> abrirVistaAgregarPedido());
        this.vista.btnVerPedido.addActionListener(e -> {
            try {
                verPedidoActual();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorMenuMesero.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.vista.btnActualizar.addActionListener(e -> {
            try {
                actualizarVistaMesa();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorMenuMesero.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void abrirVistaAgregarPedido() {
        int idMesa = vista.cmbMesa.getSelectedIndex() + 1; 
        VistaAgregarPedido vistaPedido = new VistaAgregarPedido(idMesa, idUsuario);
        vistaPedido.setVisible(true);
    }
    
    private void verPedidoActual() throws SQLException {
    int idMesa = vista.cmbMesa.getSelectedIndex() + 1;

    CreateConection cc = new CreateConection();
    Connection conn = cc.getConnection();
    PedidoDAO pedidoDAO = new PedidoDAO(conn);
    DetallePedidoDAO detalleDAO = new DetallePedidoDAO(conn);

    List<ModeloPedido> pedidos = pedidoDAO.listarPorMesa(idMesa);
    if (!pedidos.isEmpty()) {
        ModeloPedido ultimo = pedidos.get(0); 
        List<ModeloDetallePedido> detalles = detalleDAO.listarPorPedido(ultimo.getId());

       
        DefaultTableModel modelo = (DefaultTableModel) vista.tblMesas.getModel();
        modelo.setRowCount(0);
        for (ModeloDetallePedido d : detalles) {
            Object[] fila = {
                d.getIdProducto(), d.getNombreProducto(), d.getCantidad(), d.getPrecioUnitario(), d.getSubtotal()
            };
            modelo.addRow(fila);
        }

        vista.lblEstadoMesa.setText("Ocupada");
    } else {
        JOptionPane.showMessageDialog(vista, "No hay pedidos actuales para esta mesa.");
        vista.lblEstadoMesa.setText("Libre");
    }
}

    private int obtenerIdMesaSeleccionada() {
    String seleccion = vista.cmbMesa.getSelectedItem().toString();
    return Integer.parseInt(seleccion);
}
    private void actualizarEstadoMesa() throws SQLException {
    int idMesa = obtenerIdMesaSeleccionada();

    CreateConection cc = new CreateConection();
    Connection conn = cc.getConnection();
    PedidoDAO pedidoDAO = new PedidoDAO(conn);
    DetallePedidoDAO detalleDAO = new DetallePedidoDAO(conn);

    List<ModeloPedido> pedidos = pedidoDAO.listarPorMesa(idMesa);

    if (!pedidos.isEmpty()) {
        ModeloPedido ultimo = pedidos.get(0);
        List<ModeloDetallePedido> detalles = detalleDAO.listarPorPedido(ultimo.getId());

        DefaultTableModel modelo = (DefaultTableModel) vista.tblMesas.getModel();
        modelo.setRowCount(0);

        for (ModeloDetallePedido d : detalles) {
            Object[] fila = {
                d.getIdProducto(),
                d.getNombreProducto(),
                d.getCantidad(),
                d.getPrecioUnitario(),
                d.getSubtotal()
            };
            modelo.addRow(fila);
        }

        vista.lblEstadoMesa.setText("Ocupada");
    } else {
        JOptionPane.showMessageDialog(vista, "No hay pedidos actuales para esta mesa.");
        vista.lblEstadoMesa.setText("Libre");
    }
}
    private void actualizarVistaMesa() throws SQLException {
    int idMesa = obtenerIdMesaSeleccionada();

    CreateConection cc = new CreateConection();
    Connection conn = cc.getConnection();

    PedidoDAO pedidoDAO = new PedidoDAO(conn);
    DetallePedidoDAO detalleDAO = new DetallePedidoDAO(conn);

    List<ModeloPedido> pedidos = pedidoDAO.listarPorMesa(idMesa);

    if (!pedidos.isEmpty()) {
        ModeloPedido ultimo = pedidos.get(0);
        List<ModeloDetallePedido> detalles = detalleDAO.listarPorPedido(ultimo.getId());

        DefaultTableModel modelo = (DefaultTableModel) vista.tblMesas.getModel();
        modelo.setRowCount(0); // Limpiar

        for (ModeloDetallePedido d : detalles) {
            Object[] fila = {
                d.getIdProducto(),
                d.getNombreProducto(),
                d.getCantidad(),
                d.getPrecioUnitario(),
                d.getSubtotal()
            };
            modelo.addRow(fila);
        }

        vista.lblEstadoMesa.setText("Ocupada");
    } else {
        JOptionPane.showMessageDialog(vista, "No hay pedidos actuales para esta mesa.");
        vista.lblEstadoMesa.setText("Libre");
    }
}
}

