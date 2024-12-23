/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariafront.dto;

/**
 *
 * @author santi
 */
public class ReciboRequestDTO {
    private Integer idAlquiler;
    private String rutaArchivo;
    private String mes;
    public ReciboRequestDTO(Integer idAlquiler,String rutaArchivo, String mes){
        this.mes=mes;
        this.idAlquiler=idAlquiler;
        this.rutaArchivo=rutaArchivo;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(Integer idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

}

