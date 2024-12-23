/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliaria.controladores;

import Peticiones.ModeloAlquiler;
import inmobiliaria.vista.VistaListadoAlquileres;
import inmobiliariafront.dto.ReciboRequestDTO;

/**
 *
 * @author santi
 */
public class ControladorAlquileres {
    private final ModeloAlquiler modeloAlquiler;
    private final VistaListadoAlquileres vista;

    public ControladorAlquileres(VistaListadoAlquileres vista) {
        this.modeloAlquiler = new ModeloAlquiler();
        this.vista=vista;
    }
    public boolean generarPDF(ReciboRequestDTO datosPDF) {
        return modeloAlquiler.generarPDF(datosPDF);
    }
}
