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
        vista.btnModificar.addActionListener(e -> modificarEmpleado());
        vista.btnEliminar.addActionListener(e -> eliminarEmpleado());
        vista.btnConsultar.addActionListener(e -> consultarEmpleado());
        
        vista.empleadoTable.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        int fila = vista.empleadoTable.getSelectedRow();
        if (fila >= 0) {
            vista.txtCodigo.setText(vista.empleadoTable.getValueAt(fila, 0).toString()); 
            vista.txtNombre.setText(vista.empleadoTable.getValueAt(fila, 1).toString());
            vista.txtApellido.setText(vista.empleadoTable.getValueAt(fila, 2).toString());
            vista.txtCargo.setText(vista.empleadoTable.getValueAt(fila, 3).toString());
            vista.txtTelefono.setText(vista.empleadoTable.getValueAt(fila, 4).toString());
            vista.txtDireccion.setText(vista.empleadoTable.getValueAt(fila, 5).toString());
            vista.jCheckBoxEstado.setSelected(vista.empleadoTable.getValueAt(fila, 6).toString().equalsIgnoreCase("Activo"));
            vista.txtFecha.setText(vista.empleadoTable.getValueAt(fila, 7).toString());
            vista.txtSalario.setText(vista.empleadoTable.getValueAt(fila, 8).toString());
            vista.txtEmail.setText(vista.empleadoTable.getValueAt(fila, 9).toString());
        }
    }
});
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

    private void modificarEmpleado() {
    try {
        modeloEmpleado e = new modeloEmpleado();
        e.setId(Integer.parseInt(vista.txtCodigo.getText())); // ID desde campo oculto o deshabilitado
        e.setNombre(vista.txtNombre.getText());
        e.setApellido(vista.txtApellido.getText());
        e.setCargo(vista.txtCargo.getText());
        e.setTelefono(vista.txtTelefono.getText());
        e.setDireccion(vista.txtDireccion.getText());
        e.setEstado(vista.jCheckBoxEstado.isSelected());
        e.setFechaIngreso(vista.txtFecha.getText());
        e.setSalario(Double.parseDouble(vista.txtSalario.getText()));
        e.setEmail(vista.txtEmail.getText());

        boolean modificado = dao.modificarEmpleado(e);

        if (modificado) {
            JOptionPane.showMessageDialog(vista, "Empleado modificado correctamente.");
            listarEmpleados();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo modificar el empleado.");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
    }
}
    
    private void eliminarEmpleado() {
    int fila = vista.empleadoTable.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(vista, "Selecciona un empleado de la tabla.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(vista, "¿Estás seguro de eliminar este empleado?", "Confirmar", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        int id = Integer.parseInt(vista.empleadoTable.getValueAt(fila, 0).toString());
        if (dao.eliminarEmpleado(id)) {
            JOptionPane.showMessageDialog(vista, "Empleado eliminado correctamente.");
            listarEmpleados();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al eliminar empleado.");
        }
    }
}
    
    private void consultarEmpleado() {
    String nombre = JOptionPane.showInputDialog(vista, "Ingrese nombre o apellido del empleado:");
    if (nombre == null || nombre.trim().isEmpty()) return;

    DefaultTableModel modelo = (DefaultTableModel) vista.empleadoTable.getModel();
    modelo.setRowCount(0);

    List<modeloEmpleado> lista = dao.buscarEmpleado(nombre);

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
