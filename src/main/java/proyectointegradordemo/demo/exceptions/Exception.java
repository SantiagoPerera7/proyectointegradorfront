/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegradordemo.demo.exceptions;

/**
 *
 * @author santi
 */
public class Exception extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // Constructor que acepta solo un mensaje de error
    public Exception(String message) {
        super(message);
    }

    // Constructor que acepta un mensaje y una causa
    public Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
