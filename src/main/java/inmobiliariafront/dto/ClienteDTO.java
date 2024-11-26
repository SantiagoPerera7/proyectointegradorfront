/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariafront.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ulx
 */
public class ClienteDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;
    private List<PropiedadDTO> propiedades = new ArrayList<>();
    private List<ContratoDTO> contratos = new ArrayList<>();

    public ClienteDTO() {
    }

    public ClienteDTO(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<PropiedadDTO> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<PropiedadDTO> propiedades) {
        this.propiedades = propiedades;
    }
    public List<ContratoDTO> getContratos() {
        return contratos;
    }

    public void setContratos(List<ContratoDTO> contratos) {
        this.contratos = contratos;
    }

    public void agregarContrato(ContratoDTO contrato) {
        contratos.add(contrato);
        contrato.setCliente(this);
    }

    public void eliminarContrato(ContratoDTO contrato) {
        contratos.remove(contrato);
        contrato.setCliente(null);
    }

    @Override
    public String toString(){
        return this.nombre+" "+this.apellido+" - "+this.direccion;
    }
}
