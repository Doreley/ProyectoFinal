/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author chaco
 */
public class modeloEmpleado {
    
    private int id;
    private String nombre;
    private String apellido;
    private String cargo;
    private String telefono;
    private String direccion;
    private boolean estado;
    private String fechaIngreso;
    private double salario;
    private String email;

    public modeloEmpleado() {
        this.id= 0;
        this.nombre= "";
        this.apellido= "";
        this.cargo= "";
        this.telefono= "";
        this.direccion= "";
        this.estado= true;
        this.fechaIngreso= "";
        this.salario= 0;
        this.email= "";
    
    }

    public modeloEmpleado(int id, String nombre, String apellido, String cargo, String telefono, String direccion, boolean estado, String fechaIngreso, double salario, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.salario = salario;
        this.email = email;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
    

