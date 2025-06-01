/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

/**
 *
 * @author chaco
 */
import Vista.vistaLogin;
import Controlador.controladorLogin;
import java.sql.SQLException;


public class MainApp {
    public static void main(String[] args) throws SQLException {
        vistaLogin vista = new vistaLogin();
        new controladorLogin(vista);
        vista.setVisible(true);
    }
    
}
