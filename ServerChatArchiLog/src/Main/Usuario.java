/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Eros
 */
public class Usuario {
    private String Contrasenna;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private String Telefono;

    public Usuario(String Contrasenna, String Nombre, String Apellido, String Direccion, String Telefono) {
        this.Contrasenna = Contrasenna;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }

    public String getContrasenna() {
        return Contrasenna;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }
    
}
