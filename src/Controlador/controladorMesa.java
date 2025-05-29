/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author chaco
 */
import DAO.mesaDAO;
import Modelo.ModeloMesa;
import java.util.List;

public class controladorMesa {
    private mesaDAO dao;

    public controladorMesa(mesaDAO dao) {
        this.dao = dao;
    }

    
    public List<ModeloMesa> listarMesas() {
        return dao.obtenerTodas();
    }

    public boolean cambiarEstadoMesa(int numeroMesa, String nuevoEstado) {
        return dao.actualizarEstado(numeroMesa, nuevoEstado);
    }

    public String obtenerEstadoMesa(int numeroMesa) {
        return dao.obtenerEstado(numeroMesa);
    }

    public boolean existeMesa(int numeroMesa) {
        return dao.existeMesa(numeroMesa);
    }
    
}
