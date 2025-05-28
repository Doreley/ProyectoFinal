/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author chaco
 */
import Vista.vistaLogin;
import Modelo.Usuario;
import conection.CreateConection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class controladorLogin {
  
    private vistaLogin vista;

    public controladorLogin(vistaLogin vista) {
        this.vista = vista;
        initController();
    }

    private void initController() {
        vista.btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });
    }

    private void autenticarUsuario() {
        String user = vista.txtUsuario.getText();
        String pass = new String(vista.txtContrasena.getPassword());

        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ? AND estado = true";

        try {
            CreateConection conn = new CreateConection();
            Connection conexion = conn.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String rol = rs.getString("rol");
                JOptionPane.showMessageDialog(vista, "¡Bienvenido, " + rol + "!");
                // Aquí puedes abrir el menú principal según rol
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "Credenciales incorrectas o usuario inactivo.");
            }

            rs.close();
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al conectar con la base de datos: " + e.getMessage());
        }
    }
    
}
