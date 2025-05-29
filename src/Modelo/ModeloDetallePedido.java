/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author chaco
 */


public class ModeloDetallePedido {
    private int id;
    private int idPedido;
    private int idProducto;
    private String NombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ModeloDetallePedido() {
    this.id= 0;
    this.idPedido= 0;
    this.idProducto= 0;
    this.NombreProducto= "";
    this.cantidad= 0;
    this.precioUnitario= 0;
    this.subtotal= 0;
    }

    public ModeloDetallePedido(int id, int idPedido, int idProducto, int cantidad, double precioUnitario, double subtotal,String Nombreproducto) {
    this.id = id;
    this.idPedido = idPedido;
    this.idProducto = idProducto;
    this.NombreProducto = NombreProducto;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }
    
    
    
}
