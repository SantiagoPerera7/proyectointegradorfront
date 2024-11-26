/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peticiones;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 *
 * @author Ulx
 */
public class ModeloAutenticacion {
    
    public boolean validarCredenciales(String user, String pass){
        boolean exito = false;  // Inicializamos la variable para saber si el login fue exitoso

        String apiUrl = "http://localhost:8080/sistema/api/v1/login";  // URL de la API
        JSONObject json = new JSONObject();  // Creamos el objeto JSON con los datos de entrada
        json.put("usuario", user);
        json.put("password", pass);

        try {
            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crear solicitud POST
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json.toString())) // Enviamos el JSON como cuerpo
                .build();

            // Enviar la solicitud de manera sincrónica y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar el código de respuesta (200 OK)
            if (response.statusCode() == 200) {
                // Si la respuesta es 200, consideramos el login exitoso
                JSONObject responseJson = new JSONObject(response.body());  // Convertimos el cuerpo a JSON
                exito = true;  // Se espera una propiedad "success" en la respuesta
            }
        } catch (Exception e) {
            e.printStackTrace();  // Si ocurre algún error, lo imprimimos
        }

        return exito;  // Devolvemos el resultado de si fue exitoso o no
    }
}

