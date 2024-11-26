/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliaria.controladores;

import Peticiones.ModeloAutenticacion;
import com.google.gson.Gson;
import inmobiliaria.vista.VistaLogin;
import inmobiliariafront.dto.LoginResponse;
import inmobiliariafront.ventanas.VentanaListado;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 *
 * @author Ulx
 */
public class ControladorLogin {
    private final VistaLogin vista;
    private ModeloAutenticacion modelo;
    
    public ControladorLogin(VistaLogin vista){
        this.vista = vista;
        this.modelo = new ModeloAutenticacion();
    }
    
    public void loginExitoso(String user, String pass){
        // Llamada al Modelo para que haga la solicitud a la API
        boolean esValido = modelo.validarCredenciales(user, pass);

        // Dependiendo de la respuesta, actualizamos la Vista
        if (esValido) {
            vista.mostrarMensaje("Login exitoso");
            vista.abrirVentanaPrincipal();
        } else {
            vista.mostrarMensaje("Usuario o contraseña incorrectos");
        }
    }
}