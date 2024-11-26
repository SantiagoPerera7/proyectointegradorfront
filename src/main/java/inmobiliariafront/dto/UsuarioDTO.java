/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariafront.dto;

/**
 *
 * @author Ulx
 */
public class UsuarioDTO {
    
    private String nombreUsuario;
    private String password;
    
    public UsuarioDTO(String nombre, String pass){
        this.nombreUsuario = nombre;
        this.password = pass;
    }
    
     public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombre) {
        this.nombreUsuario = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }
    
}
