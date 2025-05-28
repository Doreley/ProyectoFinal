/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author chaco
 */
import DAO.empleadoDAO;
import Modelo.modeloEmpleado;
import Vista.vistaEmpleado;

import javax.swing.*;
import java.sql.SQLException; 

import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class controladorEmpleado {
    private vistaEmpleado vista;
    private empleadoDAO dao;

    public controladorEmpleado(vistaEmpleado vista) {
        this.vista = vista;
        this.dao = new empleadoDAO();
        initController();
        listarEmpleados();
    }

    private void initController() {
        vista.btnIngresar.addActionListener(e -> guardarEmpleado());
        // Puedes agregar aquí los demás botones luego
    }

    private void guardarEmpleado() {
        try {
            modeloEmpleado e = new modeloEmpleado();
            e.setNombre(vista.txtNombre.getText());
            e.setApellido(vista.txtApellido.getText());
            e.setCargo(vista.txtCargo.getText());
            e.setTelefono(vista.txtTelefono.getText());
            e.setDireccion(vista.txtDireccion.getText());
            e.setEstado(vista.jCheckBoxEstado.isSelected());
            e.setFechaIngreso(vista.txtFecha.getText());
            e.setSalario(Double.parseDouble(vista.txtSalario.getText()));
            e.setEmail(vista.txtEmail.getText());

            boolean resultado = dao.guardarEmpleado(e);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "Empleado guardado correctamente.");
                listarEmpleados();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar empleado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }

    private void listarEmpleados() {
        DefaultTableModel modelo = (DefaultTableModel) vista.empleadoTable.getModel(); 
        modelo.setRowCount(0);

        List<modeloEmpleado> lista = dao.listarEmpleados();

        for (modeloEmpleado e : lista) {
            modelo.addRow(new Object[]{
                e.getId(),
                e.getNombre(),
                e.getApellido(),
                e.getCargo(),
                e.getTelefono(),
                e.getDireccion(),
                e.isEstado() ? "Activo" : "Inactivo",
                e.getFechaIngreso(),
                e.getSalario(),
                e.getEmail()
            });
        }
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtApellido.setText("");
        vista.txtCargo.setText("");
        vista.txtTelefono.setText("");
        vista.txtDireccion.setText("");
        vista.txtSalario.setText("");
        vista.txtEmail.setText("");
        vista.txtFecha.setText("");
        vista.jCheckBoxEstado.setSelected(false);
    }
    
}
