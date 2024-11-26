/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariafront.dto;

/**
 *
 * @author Ulx
 */
public class LoginResponse {
    private String mensaje;
    private int usuarioId;
    
    public LoginResponse(String mensaje, int id) {
        this.mensaje = mensaje;
        this.usuarioId = id;
    }
    
    // Getters y setters
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getId() {
        return usuarioId;
    }
    
    public void setId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

}
