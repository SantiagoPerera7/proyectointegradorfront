/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegradordemo.demo.exceptions;

/**
 *
 * @author santi
 */
public class PropiedadException extends Exception{
    public PropiedadException(String message) {
        super(message);
    }

    public PropiedadException(String message, Throwable cause) {
        super(message, cause);
    }
}
