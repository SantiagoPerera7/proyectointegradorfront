/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peticiones;

import com.fasterxml.jackson.databind.ObjectMapper;
import inmobiliariafront.dto.ReciboRequestDTO;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author santi
 */
public class ModeloAlquiler {
    public boolean generarPDF(ReciboRequestDTO datosPDF){
        String apiUrl = "http://localhost:8080/sistema/api/v1/alquileres/generarPDF"; // Cambia por tu URL si es necesario
        try {
            // Crear conexión
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar solicitud
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Convertir objeto a JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(datosPDF);
            System.out.println(jsonInputString);
            // Escribir el cuerpo de la solicitud
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Obtener respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("PDF generado correctamente.");
                return true;
            } else {
                System.out.println("Error al generar el PDF: " + responseCode);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
