/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariafront.dto;

import java.util.Date;

/**
 *
 * @author santi
 */
public class ContratoDTO{
    private Integer id;
    private String nombreArchivo;
    private String rutaArchivo;
    private ClienteDTO cliente;
    private Date fechaInicio;
    private Date fechaFin;
    private AlquilerDTO alquiler;

    public ContratoDTO() {
    }

    public ContratoDTO(String nombreArchivo, String rutaArchivo, ClienteDTO cliente, Date fechaInicio, Date fechaFin) {
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.cliente = cliente;
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;  
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                ", rutaArchivo='" + rutaArchivo + '\'' +
                '}';
    }
}
