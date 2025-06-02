/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.sql.Connection;
import conection.CreateConection;
import DAO.PedidoDAO;
import Modelo.ModeloPedido;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DAO.DetallePedidoDAO;
import Modelo.ModeloDetallePedido;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.GenerarFactura;
import Modelo.Usuario;
import Vista.VistaAddCliente;

/**
 *
 * @author chaco
 */
public class MenuCajero extends javax.swing.JFrame {
Color mColorFondo = new Color(24, 127, 220); //Azul 
Color mColorFondo2 = new Color(93, 173, 226);
private Usuario usuario;

    /**
     * Creates new form MenuCajero
     */
    public MenuCajero(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
        
    btnVerPedidos.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            CreateConection cc = new CreateConection();
            Connection conn = cc.getConnection();

            PedidoDAO pedidoDAO = new PedidoDAO(conn);
            List<ModeloPedido> pendientes = pedidoDAO.obtenerPedidosPendientes();

            cmbPedidosPendientes.removeAllItems(); // Limpiar

            for (ModeloPedido p : pendientes) {
             cmbPedidosPendientes.addItem(p);
            }

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar pedidos pendientes: " + ex.getMessage());
        }
    }
});
    
btnGenerarfactura.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int idPedido = obtenerIdSeleccionado(); 
        CreateConection cc = new CreateConection();
        Connection conn = null;
        try {
            conn = cc.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MenuCajero.class.getName()).log(Level.SEVERE, null, ex);
        }

        PedidoDAO pedidoDAO = new PedidoDAO(conn);
        DetallePedidoDAO detalleDAO = new DetallePedidoDAO(conn);

        ModeloPedido pedido = pedidoDAO.obtenerPorId(idPedido);
        List<ModeloDetallePedido> detalles = detalleDAO.listarPorPedido(idPedido);

        String nombreCliente = "Juan Pérez"; 
        String mesero = "Usuario01";         

        GenerarFactura factura = new GenerarFactura();
        factura.generarFactura(pedido, detalles, nombreCliente, mesero);
    }
});

     
          this.setLocationRelativeTo(null);
                
//        menuClientes.setOpaque(true);
//        menuClientes.setBackground(mColorFondo);
//        menuClientes.setForeground(Color.white);
//        
//        menuFacturacion.setOpaque(true);
//        menuFacturacion.setBackground(mColorFondo);
//        menuFacturacion.setForeground(Color.white);
//        
//        menuReportes.setOpaque(true);
//        menuReportes.setBackground(mColorFondo);
//        menuReportes.setForeground(Color.white);
//        
//        itemClientes.setOpaque(true);
//        itemClientes.setBackground(mColorFondo2);
//        itemClientes.setForeground(Color.white);
//        
//        itemFacturacion.setOpaque(true);
//        itemFacturacion.setBackground(mColorFondo2);
//        itemFacturacion.setForeground(Color.white);
//        
//        itemReportes.setOpaque(true);
//        itemReportes.setBackground(mColorFondo2);
//        itemReportes.setForeground(Color.white);
//        
      ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/com.png"));
      ImageIcon icono = new ImageIcon(getClass().getResource("/img/b.png"));

      Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
      lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_SMOOTH);

      lblFondo.setIcon(new ImageIcon(imagenEscalada));

      Image imagenEscala = icono.getImage().getScaledInstance(
      lblBienvenida.getWidth(), lblBienvenida.getHeight(), Image.SCALE_SMOOTH);

      lblBienvenida.setIcon(new ImageIcon(imagenEscala));
      
      
      
    }
    
    public void cargarPedidosPendientes() {
    try {
        CreateConection cc = new CreateConection();
        Connection conn = cc.getConnection();
        PedidoDAO pedidoDAO = new PedidoDAO(conn);
        
        List<ModeloPedido> lista = pedidoDAO.obtenerPedidosPendientes();
        cmbPedidosPendientes.removeAllItems();  
        
        for (ModeloPedido p : lista) {
        cmbPedidosPendientes.addItem(p);
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar pedidos: " + e.getMessage());
    }
}
    private int obtenerIdSeleccionado() {
   
   ModeloPedido seleccionado = (ModeloPedido) cmbPedidosPendientes.getSelectedItem();
    return seleccionado.getId();    
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblBienvenida = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnVerPedidos = new javax.swing.JButton();
        cmbPedidosPendientes = new javax.swing.JComboBox<>();
        lblPedidosPendientes = new javax.swing.JLabel();
        btnGenerarfactura = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnAgregarDatosClientes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        lblBienvenida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/b.png"))); // NOI18N
        lblBienvenida.setText("Bienvenido Cajero");

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/com.png"))); // NOI18N
        lblFondo.setText("jLabel1");

        btnCerrarSesion.setBackground(new java.awt.Color(204, 204, 204));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesion");

        jButton1.setBackground(new java.awt.Color(255, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnVerPedidos.setText("Ver Pedidos Pendientes");

        cmbPedidosPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPedidosPendientesActionPerformed(evt);
            }
        });

        lblPedidosPendientes.setText("Pedidos Pendientes");

        btnGenerarfactura.setText("Generar Factura");

        btnActualizar.setText("Actualizar");

        btnAgregarDatosClientes.setText("Agregar Datos de Cliente");
        btnAgregarDatosClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDatosClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(0, 11, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(lblPedidosPendientes))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(btnVerPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarDatosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbPedidosPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton1)
                .addGap(9, 9, 9)
                .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnVerPedidos)
                .addGap(18, 18, 18)
                .addComponent(lblPedidosPendientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPedidosPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarDatosClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnGenerarfactura)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar)
                .addGap(12, 12, 12)
                .addComponent(btnCerrarSesion)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbPedidosPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPedidosPendientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPedidosPendientesActionPerformed

    private void btnAgregarDatosClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDatosClientesActionPerformed
    VistaAddCliente vistaCliente = new VistaAddCliente(usuario);
    vistaCliente.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_btnAgregarDatosClientesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarDatosClientes;
    private javax.swing.JButton btnCerrarSesion;
    public javax.swing.JButton btnGenerarfactura;
    public javax.swing.JButton btnVerPedidos;
    public javax.swing.JComboBox<ModeloPedido> cmbPedidosPendientes;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblFondo;
    public javax.swing.JLabel lblPedidosPendientes;
    // End of variables declaration//GEN-END:variables
}
