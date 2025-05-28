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


public class controladorLogin {
     private vistaLogin vista;
    private usuarioDao dao;

    public controladorLogin(vistaLogin vista) {
        this.vista = vista;
        this.dao = new usuarioDao();

        this.vista.btnIngresar.addActionListener(e -> validarLogin());
    }

    private void validarLogin() {
        String usuario = vista.txtUsuario.getText();
        String clave = vista.txtContrasena.getText();

        if (usuario.isEmpty() || clave.isEmpty()) {
            vista.lblMensaje.setText("Por favor, llena todos los campos.");
            return;
        }

        Usuario user = dao.login(usuario, clave);

        if (user != null) {
            vista.lblMensaje.setText("Bienvenido, rol: " + user.getPuesto());

            switch (user.getPuesto().toLowerCase()) {
                case "administrador":
                    new MenuAdministrador().setVisible(true);
                    break;
                case "cajero":
                    new MenuCajero().setVisible(true);
                    break;
                case "mesero":
                    new MenuMesero().setVisible(true);
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
