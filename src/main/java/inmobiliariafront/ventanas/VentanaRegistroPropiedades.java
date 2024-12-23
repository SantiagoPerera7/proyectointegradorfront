/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inmobiliariafront.ventanas;

//import com.proyectointegradordemo.demo.domain.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import inmobilariafront.callbacks.ActualizacionListadoPropiedadesCallback;
import inmobiliariafront.dto.BarrioDTO;
import inmobiliariafront.dto.CiudadDTO;
import inmobiliariafront.dto.ClienteDTO;
import inmobiliariafront.dto.FotoDTO;
import inmobiliariafront.dto.PropiedadDTO;
import inmobiliariafront.dto.TipoPropiedadDTO;
import inmobiliariafront.dto.TipoTechoDTO;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.json.JSONArray;
import org.json.JSONObject;
import proyectointegradordemo.demo.exceptions.PropiedadException;

/**
 *
 * @author santi
 */
public class VentanaRegistroPropiedades extends javax.swing.JFrame{

    /**
     * Creates new form VentanaRegistroPropiedades
     */
    //que es esto y por que esta aca
    private ClienteDTO cliente;
    private final ActualizacionListadoPropiedadesCallback callback;
    private DefaultListModel<ImageIcon> listaFotosModel;
    private PropiedadDTO propiedad;
    private List<FotoDTO> fotos;

    public VentanaRegistroPropiedades(ClienteDTO cliente,ActualizacionListadoPropiedadesCallback callback) {
        this.callback=callback;
        this.cliente = cliente;
        this.fotos=new ArrayList<>();
        initComponents();
        cargarTiposDeTecho();
        cargarTiposDePropiedad();
        cargarCiudades();
        cargarBarrios();
        ((AbstractDocument) txtCantidadAmbientes.getDocument()).setDocumentFilter(new NumericFilter());
        ((AbstractDocument) txtCantidadPlantas.getDocument()).setDocumentFilter(new NumericFilter());
        ((AbstractDocument) txtCantidadBanios.getDocument()).setDocumentFilter(new NumericFilter());
        ((AbstractDocument) txtMetrosCuadradosTotales.getDocument()).setDocumentFilter(new NumericFilter());
        ((AbstractDocument) txtMetrosCuadradosEdificados.getDocument()).setDocumentFilter(new NumericFilter());
        ((AbstractDocument) txtMetrosCuadradosTerreno.getDocument()).setDocumentFilter(new NumericFilter());
        ((AbstractDocument) txtCantidadCocheras.getDocument()).setDocumentFilter(new NumericFilter());
        ((AbstractDocument) txtCantidadDormitorios.getDocument()).setDocumentFilter(new NumericFilter());
        pnlFotos.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Espaciado opcional
        scrollPaneImagenes.setPreferredSize(new Dimension(400, 300)); // Ajusta el tamaño según necesites
        listaFotosModel= new DefaultListModel<>();
    }
    
    private ClienteDTO obtenerCliente(Integer id) throws Exception{
// Construir la URL
        String url = "http://localhost:8080/sistema/api/v1/clientes/"+id;

        // Crear cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        // Hacer la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Manejo de errores en la respuesta
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al obtener el tipo de techo. Código: " + response.statusCode());
        }

        // Parsear la respuesta JSON a un objeto TipoTecho
        ObjectMapper mapper = new ObjectMapper();
        ClienteDTO cliente = mapper.readValue(response.body(), ClienteDTO.class);

        // Convertir a DTO y retornarlo
        return cliente;
    }
    
    private BarrioDTO obtenerBarrio(String nombreBarrio) throws Exception{
// Construir la URL con el parámetro codificado
    String url = "http://localhost:8080/sistema/api/v1/barrios/obtenerBarrio/" + 
                 URLEncoder.encode(nombreBarrio, StandardCharsets.UTF_8.name());

    // Crear cliente HTTP
    HttpClient client = HttpClient.newHttpClient();

    // Crear solicitud HTTP
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

    try {
        // Hacer la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Manejo de errores en la respuesta
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al obtener el barrio. Código: " + response.statusCode() +
                    ", Mensaje: " + response.body());
        }

        // Parsear la respuesta JSON a un objeto CiudadDTO
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(),BarrioDTO.class);

    } catch (IOException | InterruptedException e) {
        throw new RuntimeException("Error al realizar la solicitud HTTP: " + e.getMessage(), e);
    }  
    }
    
    private CiudadDTO obtenerCiudad(String nombreCiudad) throws Exception{
// Construir la URL con el parámetro codificado
    String url = "http://localhost:8080/sistema/api/v1/ciudades/obtenerCiudad/" + 
                 URLEncoder.encode(nombreCiudad, StandardCharsets.UTF_8.name());

    // Crear cliente HTTP
    HttpClient client = HttpClient.newHttpClient();

    // Crear solicitud HTTP
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

    try {
        // Hacer la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Manejo de errores en la respuesta
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al obtener la ciudad. Código: " + response.statusCode() +
                    ", Mensaje: " + response.body());
        }

        // Parsear la respuesta JSON a un objeto CiudadDTO
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), CiudadDTO.class);

    } catch (IOException | InterruptedException e) {
        throw new RuntimeException("Error al realizar la solicitud HTTP: " + e.getMessage(), e);
    }  
    }
    
    private TipoTechoDTO obtenerTipoTecho(String nombre) throws Exception{
// Construir la URL
        String url = "http://localhost:8080/sistema/api/v1/tipostecho/nombre/" + 
                 URLEncoder.encode(nombre, StandardCharsets.UTF_8.name());

        // Crear cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        // Hacer la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Manejo de errores en la respuesta
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al obtener el tipo de techo. Código: " + response.statusCode());
        }

        // Parsear la respuesta JSON a un objeto TipoTecho
        ObjectMapper mapper = new ObjectMapper();
        TipoTechoDTO tipoTecho = mapper.readValue(response.body(), TipoTechoDTO.class);

        // Convertir a DTO y retornarlo
        return tipoTecho;
    }
    
    private TipoPropiedadDTO obtenerTipoPropiedad(String nombre) throws Exception{
// Construir la URL
        String url = "http://localhost:8080/sistema/api/v1/tipospropiedad/obtenerTipoPropiedad/" + 
                 URLEncoder.encode(nombre, StandardCharsets.UTF_8.name());

        // Crear cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        // Hacer la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Manejo de errores en la respuesta
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al obtener el tipo de techo. Código: " + response.statusCode());
        }

        // Parsear la respuesta JSON a un objeto TipoTecho
        ObjectMapper mapper = new ObjectMapper();
        TipoPropiedadDTO tipoPropiedad = mapper.readValue(response.body(), TipoPropiedadDTO.class);

        // Convertir a DTO y retornarlo
        return tipoPropiedad;
    }
    
    private void cargarBarrios() {
        cbBarrio.removeAllItems();
        List<String> barrios = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/sistema/api/v1/barrios");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();

            // Parse JSON response
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                barrios.add(obj.getString("nombre")); // Asume que el campo "nombre" es el valor deseado
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!barrios.isEmpty()) {
            for (String ciudad : barrios) {
                cbBarrio.addItem(ciudad);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los barrios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarCiudades() {
        cbCiudad.removeAllItems();
        List<String> ciudades = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/sistema/api/v1/ciudades");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();

            // Parse JSON response
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                ciudades.add(obj.getString("nombre")); // Asume que el campo "nombre" es el valor deseado
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!ciudades.isEmpty()) {
            for (String ciudad : ciudades) {
                cbCiudad.addItem(ciudad);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar las ciudades", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarTiposDePropiedad() {
        cbTipoPropiedad.removeAllItems();
        List<String> tiposPropiedad = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/sistema/api/v1/tipospropiedad");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();

            // Parse JSON response
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                tiposPropiedad.add(obj.getString("nombre")); // Asume que el campo "nombre" es el valor deseado
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!tiposPropiedad.isEmpty()) {
            for (String tipo : tiposPropiedad) {
                cbTipoPropiedad.addItem(tipo);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los tipos de propiedad", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarTiposDeTecho() {
        cbTipoTecho.removeAllItems();
        List<String> tiposDeTecho = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/sistema/api/v1/tipostecho");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();

            // Parse JSON response
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                tiposDeTecho.add(obj.getString("nombre")); // Asume que el campo "nombre" es el valor deseado
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!tiposDeTecho.isEmpty()) {
            for (String tipo : tiposDeTecho) {
                cbTipoTecho.addItem(tipo);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los tipos de techo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void subirFotos(){
        for(FotoDTO foto:fotos){
        foto.setPropiedad(propiedad);
        try {
        // Crear el cliente HTTP
        HttpClient client = HttpClient.newHttpClient();


        // Serializar a JSON usando Gson o Jackson
        Gson gson = new Gson(); // O ObjectMapper mapper = new ObjectMapper();
        String jsonBody = gson.toJson(foto); // mapper.writeValueAsString(propiedadDTO);

        // Construir la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/sistema/api/v1/fotos"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        // Enviar la solicitud
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Manejar la respuesta
        if (response.statusCode() == 201) {
            JOptionPane.showMessageDialog(this, "Fotos registradas con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la foto: " + response.body());
            System.out.println(response.body());
        }

    } catch (PropiedadException e) {
        JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage());
        System.out.println(e.getMessage());
    }    catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage());
            System.out.println(e.getMessage());
    }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneVentana = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        lblBarrio = new javax.swing.JLabel();
        lblCiudad = new javax.swing.JLabel();
        lblCantidadAmbientes = new javax.swing.JLabel();
        txtCantidadAmbientes = new javax.swing.JTextField();
        lblCantidadPlantas = new javax.swing.JLabel();
        txtCantidadPlantas = new javax.swing.JTextField();
        lblCantidadBanios = new javax.swing.JLabel();
        txtCantidadBanios = new javax.swing.JTextField();
        lblMetrosCuadradosTotales = new javax.swing.JLabel();
        txtMetrosCuadradosTotales = new javax.swing.JTextField();
        lblTipoPropiedad = new javax.swing.JLabel();
        cbTipoPropiedad = new javax.swing.JComboBox<>();
        lblMetrosCuadradosEdificados = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMetrosCuadradosEdificados = new javax.swing.JTextField();
        lblMetrosCuadradosTerreno = new javax.swing.JLabel();
        txtMetrosCuadradosTerreno = new javax.swing.JTextField();
        lblTitularUTE = new javax.swing.JLabel();
        txtTitularUTE = new javax.swing.JTextField();
        lblTitularOSE = new javax.swing.JLabel();
        txtTitularOSE = new javax.swing.JTextField();
        txtTipoTecho = new javax.swing.JLabel();
        cbTipoTecho = new javax.swing.JComboBox<>();
        chkAceptaMascotas = new javax.swing.JCheckBox();
        lblCantidadCocheras = new javax.swing.JLabel();
        txtCantidadCocheras = new javax.swing.JTextField();
        btnCargarFotos = new javax.swing.JButton();
        scrollPaneImagenes = new javax.swing.JScrollPane();
        pnlFotos = new javax.swing.JPanel();
        cbCiudad = new javax.swing.JComboBox<>();
        cbBarrio = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtCantidadDormitorios = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Registro de propiedades");

        lblDescripcion.setText("Descripción:");

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });

        lblDireccion.setText("Dirección:");

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        lblBarrio.setText("Barrio");

        lblCiudad.setText("Ciudad");

        lblCantidadAmbientes.setText("Cantidad de ambientes");

        lblCantidadPlantas.setText("Cantidad de plantas");

        lblCantidadBanios.setText("Cantidad de baños");

        lblMetrosCuadradosTotales.setText("Metros cuadrados totales");

        lblTipoPropiedad.setText("Tipo de propiedad");

        cbTipoPropiedad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblMetrosCuadradosEdificados.setText("Metros cuadrados edificados");

        lblMetrosCuadradosTerreno.setText("Metros cuadrados de terreno");

        lblTitularUTE.setText("Titular UTE");

        lblTitularOSE.setText("Titular OSE");

        txtTipoTecho.setText("Tipo de techo");

        cbTipoTecho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chkAceptaMascotas.setText("Acepta mascotas");

        lblCantidadCocheras.setText("Cantidad cocheras");

        btnCargarFotos.setText("Cargar fotos");
        btnCargarFotos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarFotosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFotosLayout = new javax.swing.GroupLayout(pnlFotos);
        pnlFotos.setLayout(pnlFotosLayout);
        pnlFotosLayout.setHorizontalGroup(
            pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
        );
        pnlFotosLayout.setVerticalGroup(
            pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        scrollPaneImagenes.setViewportView(pnlFotos);

        cbCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbBarrio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Cantidad de dormitorios");

        txtCantidadDormitorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadDormitoriosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneImagenes)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCantidadCocheras, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidadCocheras))
                    .addComponent(chkAceptaMascotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTipoTecho, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoTecho, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMetrosCuadradosTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMetrosCuadradosTerreno))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion)
                            .addComponent(txtDireccion)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCantidadPlantas, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadDormitorios)
                            .addComponent(txtCantidadPlantas)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblCantidadAmbientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCiudad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBarrio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTipoPropiedad, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantidadAmbientes)
                            .addComponent(cbCiudad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbBarrio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCantidadBanios, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMetrosCuadradosTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMetrosCuadradosEdificados, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadBanios)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtMetrosCuadradosTotales)
                            .addComponent(txtMetrosCuadradosEdificados)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitularUTE, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitularOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTitularUTE)
                            .addComponent(txtTitularOSE, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                .addGap(381, 381, 381))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(btnCargarFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoPropiedad)
                    .addComponent(cbTipoPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCiudad)
                    .addComponent(cbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidadAmbientes)
                    .addComponent(txtCantidadAmbientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCantidadDormitorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidadPlantas)
                    .addComponent(txtCantidadPlantas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidadBanios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantidadBanios))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMetrosCuadradosTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMetrosCuadradosTotales))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblMetrosCuadradosEdificados))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMetrosCuadradosEdificados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMetrosCuadradosTerreno)
                    .addComponent(txtMetrosCuadradosTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitularUTE)
                    .addComponent(txtTitularUTE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitularOSE)
                    .addComponent(txtTitularOSE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoTecho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoTecho))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidadCocheras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidadCocheras))
                .addGap(18, 18, 18)
                .addComponent(chkAceptaMascotas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCargarFotos)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneImagenes, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar)
                .addGap(156, 156, 156))
        );

        scrollPaneVentana.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneVentana, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPaneVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        String direccion = txtDireccion.getText();
        String descripcion = txtDescripcion.getText();
        String tipo=(String)cbTipoPropiedad.getSelectedItem();
        String barrio=(String)cbBarrio.getSelectedItem();
        String ciudad=(String)cbCiudad.getSelectedItem();
        Integer cantidadDormitorios=0;
        Integer cantidadAmbientes=0;
        Integer cantidadPlantas=0;
        Integer cantidadBanios=0;
        Double metrosCuadradosTotales=0.0;
        Double metrosCuadradosEdificados=0.0;
        Double metrosCuadradosTerreno=0.0;
        Integer cantidadCocheras=0;
        if(!txtCantidadDormitorios.getText().isEmpty()&&txtCantidadDormitorios!=null){
            cantidadDormitorios=Integer.parseInt(txtCantidadDormitorios.getText());
        }
        if(!txtCantidadAmbientes.getText().isEmpty()&&txtCantidadAmbientes!=null){
            cantidadAmbientes=Integer.parseInt(txtCantidadAmbientes.getText());
        }
        if(!txtCantidadPlantas.getText().isEmpty()&&txtCantidadPlantas!=null){
            cantidadPlantas=Integer.parseInt(txtCantidadPlantas.getText());
        }
        if(!txtCantidadBanios.getText().isEmpty()&&txtCantidadBanios!=null){
            cantidadBanios=Integer.parseInt(txtCantidadBanios.getText());
        }
        if(!txtMetrosCuadradosTotales.getText().isEmpty()&&txtMetrosCuadradosTotales!=null){
            metrosCuadradosTotales=Double.parseDouble(txtMetrosCuadradosTotales.getText());
        }
        if(!txtMetrosCuadradosEdificados.getText().isEmpty()&&txtMetrosCuadradosEdificados!=null){
            metrosCuadradosEdificados=Double.parseDouble(txtMetrosCuadradosEdificados.getText());
        }
        if(!txtMetrosCuadradosTerreno.getText().isEmpty()&&txtMetrosCuadradosTerreno!=null){
            metrosCuadradosTerreno=Double.parseDouble(txtMetrosCuadradosTerreno.getText());
        }
        String titularUTE=txtTitularUTE.getText();
        String titularOSE=txtTitularOSE.getText();
        String tipoTecho=(String)cbTipoTecho.getSelectedItem();
        if(!txtCantidadCocheras.getText().isEmpty()&&txtCantidadCocheras!=null){
            cantidadCocheras=Integer.parseInt(txtCantidadCocheras.getText());
        }
        Boolean aceptaMascotas=chkAceptaMascotas.isSelected();
        try {
        // Crear el cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear el objeto PropiedadDTO
        propiedad = new PropiedadDTO(direccion, descripcion, obtenerCliente(cliente.getId()), obtenerTipoPropiedad(tipo), obtenerBarrio(barrio), obtenerCiudad(ciudad), cantidadAmbientes, cantidadPlantas, cantidadBanios,cantidadDormitorios,metrosCuadradosTotales,metrosCuadradosEdificados,metrosCuadradosTerreno,titularUTE,titularOSE,obtenerTipoTecho(tipoTecho),cantidadCocheras,aceptaMascotas);

        // Serializar a JSON usando Gson o Jackson
        Gson gson = new Gson(); // O ObjectMapper mapper = new ObjectMapper();
        String jsonBody = gson.toJson(propiedad); // mapper.writeValueAsString(propiedadDTO);
        System.out.println(jsonBody);
        // Construir la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/sistema/api/v1/propiedades"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        // Enviar la solicitud
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Manejar la respuesta
        if (response.statusCode() == 201) {
            PropiedadDTO propiedadGuardada = gson.fromJson(response.body(), PropiedadDTO.class);
            propiedad.setId(propiedadGuardada.getId()); // Asigna el ID generado
            subirFotos();
            JOptionPane.showMessageDialog(this, "Propiedad registrada con éxito.");
            if (callback != null) {
                    callback.actualizarListadoPropiedades();
                }

                dispose();
        } else {
            System.out.println(response.body());
            JOptionPane.showMessageDialog(this, "Error al registrar la propiedad: " + response.body());
        }

    } catch (PropiedadException e) {
        JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage());
            System.out.println(e.getMessage());
    }    catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage());
        System.out.println(e.getMessage());
    }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCargarFotosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarFotosActionPerformed
        JFileChooser fileChooser = new JFileChooser(); // Crear instancia del JFileChooser
    fileChooser.setDialogTitle("Seleccionar fotos");
    fileChooser.setMultiSelectionEnabled(true);
    fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg"));

    int returnValue = fileChooser.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File[] archivosSeleccionados = fileChooser.getSelectedFiles();
        for (File archivo : archivosSeleccionados) {
            try {
                // Leer la imagen y crear miniatura
                BufferedImage imagenOriginal = ImageIO.read(archivo);
                Image miniatura = imagenOriginal.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon miniaturaIcon = new ImageIcon(miniatura);

                // Crear un JLabel para la miniatura y agregarlo al panel
                JLabel lblMiniatura = new JLabel();
                lblMiniatura.setIcon(miniaturaIcon);
                lblMiniatura.setToolTipText(archivo.getName()); // Mostrar nombre al pasar el mouse
                FotoDTO foto=new FotoDTO();
                foto.setUrl(archivo.toURI().toString());
                fotos.add(foto);
                pnlFotos.add(lblMiniatura);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + archivo.getName());
                System.out.println(ex.getMessage());
            }
        }
        pnlFotos.revalidate(); // Refrescar el panel
        pnlFotos.repaint();
    }
    }//GEN-LAST:event_btnCargarFotosActionPerformed

    private void txtCantidadDormitoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadDormitoriosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadDormitoriosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroPropiedades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroPropiedades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroPropiedades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroPropiedades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        Cliente cliente = new Cliente("Nombre del cliente","Apellido del cliente","Dirección del cliente");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new VentanaRegistroPropiedades(cliente).setVisible(true);
            }
        });
    }
    
      static class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && string.matches("\\d*")) { // Solo permitir dígitos
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && string.matches("\\d*")) { // Solo permitir dígitos
                super.replace(fb, offset, length, string, attr);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarFotos;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cbBarrio;
    private javax.swing.JComboBox<String> cbCiudad;
    private javax.swing.JComboBox<String> cbTipoPropiedad;
    private javax.swing.JComboBox<String> cbTipoTecho;
    private javax.swing.JCheckBox chkAceptaMascotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBarrio;
    private javax.swing.JLabel lblCantidadAmbientes;
    private javax.swing.JLabel lblCantidadBanios;
    private javax.swing.JLabel lblCantidadCocheras;
    private javax.swing.JLabel lblCantidadPlantas;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblMetrosCuadradosEdificados;
    private javax.swing.JLabel lblMetrosCuadradosTerreno;
    private javax.swing.JLabel lblMetrosCuadradosTotales;
    private javax.swing.JLabel lblTipoPropiedad;
    private javax.swing.JLabel lblTitularOSE;
    private javax.swing.JLabel lblTitularUTE;
    private javax.swing.JPanel pnlFotos;
    private javax.swing.JScrollPane scrollPaneImagenes;
    private javax.swing.JScrollPane scrollPaneVentana;
    private javax.swing.JTextField txtCantidadAmbientes;
    private javax.swing.JTextField txtCantidadBanios;
    private javax.swing.JTextField txtCantidadCocheras;
    private javax.swing.JTextField txtCantidadDormitorios;
    private javax.swing.JTextField txtCantidadPlantas;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtMetrosCuadradosEdificados;
    private javax.swing.JTextField txtMetrosCuadradosTerreno;
    private javax.swing.JTextField txtMetrosCuadradosTotales;
    private javax.swing.JLabel txtTipoTecho;
    private javax.swing.JTextField txtTitularOSE;
    private javax.swing.JTextField txtTitularUTE;
    // End of variables declaration//GEN-END:variables
}


