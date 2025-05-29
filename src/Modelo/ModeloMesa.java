/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author chaco
 */
public class ModeloMesa {
    private int id;
    private int numero;
    private String estado;

    public ModeloMesa() {
        this.id = 0;
        this.numero = 0;
        this.estado = "";
    }

    public ModeloMesa(int id, int numero, String estado) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}

