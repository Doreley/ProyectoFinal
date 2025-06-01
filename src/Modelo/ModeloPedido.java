/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author chaco
 */
import java.sql.Timestamp;

public class ModeloPedido {
     private int id;
    private int idCliente;
    private int idUsuario;
    private int mesaId;
    private Timestamp fecha;
    private double total;
    private double impuesto;

    public ModeloPedido() {
    this.id = 0;
    this.idCliente= 0;
    this.idUsuario= 0;
    this.mesaId= 0;
    this.fecha= null;
    this. total= 0;
    this.impuesto= 0;
    }

    public ModeloPedido(int id, int idCliente, int idUsuario, int mesaId, Timestamp fecha, double total, double impuesto) {
        this.id = id;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.mesaId = mesaId;
        this.fecha = fecha;
        this.total = total;
        this.impuesto = impuesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getMesaId() {
        return mesaId;
    }

    public void setMesaId(int mesaId) {
        this.mesaId = mesaId;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }
    
   @Override
public String toString() {
    return "Pedido #" + id + " - Mesa " + mesaId;
} 
    
}
