/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariafront.dto;

import java.util.List;

/**
 *
 * @author santi
 */
public class LiquidacionRequestDTO {
    private List<String> datos;
    private String rutaArchivo;
    private String mes;
    private String direccion;

    public LiquidacionRequestDTO(List<String> datos,String rutaArchivo, String mes, String direccion){
        this.datos=datos;
        this.direccion=direccion;
        this.rutaArchivo=rutaArchivo;
        this.mes=mes;
    }
        public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
        public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
    public List<String> getDatos() {
        return datos;
    }

    public void setDatos(List<String> datos) {
        this.datos = datos;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
}
