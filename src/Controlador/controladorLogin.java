/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author chaco
 */
import DAO.usuarioDao;
import Modelo.Usuario;
import Vista.vistaLogin;
import Vista.MenuAdministrador;
import Vista.MenuCajero;
import Vista.MenuMesero;
import conection.CreateConection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




public class controladorLogin {
    private vistaLogin vista;
    private usuarioDao dao;

    public controladorLogin(vistaLogin vista) throws SQLException {
        this.vista = vista;
        
        CreateConection cc = new CreateConection();
    Connection conn = cc.getConnection();
        this.dao = new usuarioDao(conn);

        // Listener para el botón de ingresar
        this.vista.btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validarLogin(); 
                } catch (SQLException ex) {
                    Logger.getLogger(controladorLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Método que valida login y redirige según el rol
    private void validarLogin() throws SQLException {
        String usuario = vista.txtUsuario.getText().trim();
        String clave = vista.txtContrasena.getText().trim();

        if (usuario.isEmpty() || clave.isEmpty()) {
            vista.lblMensaje.setText("Por favor, llena todos los campos.");
            return;
        }

        CreateConection cc = new CreateConection();
        Connection conn = cc.getConnection();

        usuarioDao usuarioDAO = new usuarioDao(conn);
        Usuario modeloUsuario = usuarioDAO.validarLogin(usuario, clave);

        if (modeloUsuario != null) {
            vista.lblMensaje.setText("Bienvenido, rol: " + modeloUsuario.getPuesto());

            switch (modeloUsuario.getPuesto().toLowerCase()) {
                case "administrador":
                    new MenuAdministrador(modeloUsuario).setVisible(true);
                    break;
                case "cajero":
                    new MenuCajero(modeloUsuario).setVisible(true);
                    break;
                case "mesero":
                    new MenuMesero(modeloUsuario).setVisible(true);
                    break;
                default:
                    vista.lblMensaje.setText("Rol desconocido.");
                    return;
            }

            vista.dispose(); 
        } else {
            vista.lblMensaje.setText("Usuario o contraseña incorrectos.");
        }
    }
   
}
