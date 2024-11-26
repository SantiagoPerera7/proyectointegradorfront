/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliaria.controladores;

import Peticiones.ModeloCliente;
import inmobiliariafront.dto.ClienteDTO;
import java.util.List;
import inmobiliaria.vista.VistaListadoClientes;

/**
 *
 * @author santi
 */
public class ControladorClientes {
    private final ModeloCliente clienteModelo;
    private final VistaListadoClientes vista;

    public ControladorClientes(VistaListadoClientes vista) {
        this.clienteModelo = new ModeloCliente();
        this.vista=vista;
    }

    public List<ClienteDTO> obtenerClientes() {
        return clienteModelo.obtenerClientes();
    }
    public void cargarClientes() {
    List<ClienteDTO> clientes = clienteModelo.obtenerClientes();
    vista.cargarClientes(clientes);  // Envía los clientes procesados a la vista
}

    //public ClienteDTO agregarCliente(ClienteDTO cliente) {
      //  return clienteModelo.agregarCliente(cliente);
    //}

    //public boolean eliminarCliente(Long id) {
      //  return clienteModelo.eliminarCliente(id);
    //}
}
