/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peticiones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import inmobiliariafront.dto.ClienteDTO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import proyectointegradordemo.demo.exceptions.ClienteException;

/**
 *
 * @author santi
 */
public class ModeloCliente {
    private static final String clientesUrl = "http://localhost:8080/sistema/api/v1/clientes";

    public List<ClienteDTO> obtenerClientes() {
        List<ClienteDTO> clientes = new ArrayList<>();
        try {
            URL url = new URL(clientesUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            clientes = mapper.readValue(response.toString(), new TypeReference<List<ClienteDTO>>() {});
        } catch (Exception e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        }
        return clientes;
    }
}
