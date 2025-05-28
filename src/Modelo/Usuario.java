/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author chaco
 */
public class Usuario {
    private int id;
    private int idEmpleado;
    private String nombreUsuario;
    private String contrasena;
    private String puesto;
    private boolean estado;

   
   public Usuario() {
   this.id= 0;
   this.idEmpleado= 0;
   this.nombreUsuario= "";
   this.contrasena= "";
   this.puesto= "";
   this.estado= true;
    }
   
   public Usuario(int id, int idEmpleado, String nombreUsuario, String contrasena, String puesto, boolean estado ) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.nombreUsuario= nombreUsuario;
        this.contrasena = contrasena;
        this.puesto = puesto;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
   
   
   


    
}
