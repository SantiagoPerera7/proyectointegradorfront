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
public class PropiedadDTO {
    private Integer id;
    private String direccion;
    private String descripcion;
    private Integer cantidadBanios;
    private Integer cantidadAmbientes;
    private Integer cantidadCocheras;
    private String barrio;
    private String ciudad;
    private Integer cantidadPlantas;
    private boolean aceptaMascotas;
    private String tipo;
    private Double metrosCuadradosTotales;
    private Double metrosCuadradosEdificados;
    private Double metrosCuadradosTerreno;
    private String titularActualUTE;
    private String titularActualOSE;
    private String tipoTecho;
    public ClienteDTO propietario;
    private List<FotoDTO> fotos = new ArrayList<>();
    public PropiedadDTO(){
    }
    public PropiedadDTO(String direccion, String descripcion, ClienteDTO propietario, String tipo, String barrio,
            String ciudad, Integer cantidadAmbientes, Integer cantidadPlantas, Integer cantidadBanios,
            Double metrosCuadradosTotales, Double metrosCuadradosEdificados, Double metrosCuadradosTerreno,
            String titularActualUTE,String titularActualOSE, String tipoTecho, Integer cantidadCocheras, Boolean aceptaMascotas){
        this.direccion=direccion;
        this.descripcion=descripcion;
        this.propietario=propietario;
        this.tipo=tipo;
        this.barrio=barrio;
        this.ciudad=ciudad;
        this.cantidadAmbientes=cantidadAmbientes;
        this.cantidadPlantas=cantidadPlantas;
        this.cantidadBanios=cantidadBanios;
        this.metrosCuadradosTotales=metrosCuadradosTotales;
        this.metrosCuadradosEdificados=metrosCuadradosEdificados;
        this.metrosCuadradosTerreno=metrosCuadradosTerreno;
        this.titularActualUTE=titularActualUTE;
        this.titularActualOSE=titularActualOSE;
        this.tipoTecho=tipoTecho;
        this.cantidadCocheras=cantidadCocheras;
        this.aceptaMascotas=aceptaMascotas;
    }
    public List<FotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoDTO> fotos) {
        this.fotos = fotos;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ClienteDTO getPropietario() {
        return propietario;
    }

    public void setPropietario(ClienteDTO propietario) {
        this.propietario = propietario;
    }
    public Integer getCantidadBanios() {
        return cantidadBanios;
    }

    public void setCantidadBanios(Integer cantidadBanios) {
        this.cantidadBanios = cantidadBanios;
    }

    public Integer getCantidadAmbientes() {
        return cantidadAmbientes;
    }

    public void setCantidadAmbientes(Integer cantidadAmbientes) {
        this.cantidadAmbientes = cantidadAmbientes;
    }

    public Integer getCantidadCocheras() {
        return cantidadCocheras;
    }

    public void setCantidadCocheras(Integer cantidadCocheras) {
        this.cantidadCocheras = cantidadCocheras;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getCantidadPlantas() {
        return cantidadPlantas;
    }

    public void setCantidadPlantas(Integer cantidadPlantas) {
        this.cantidadPlantas = cantidadPlantas;
    }

    public boolean isAceptaMascotas() {
        return aceptaMascotas;
    }

    public void setAceptaMascotas(boolean aceptaMascotas) {
        this.aceptaMascotas = aceptaMascotas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMetrosCuadradosTotales() {
        return metrosCuadradosTotales;
    }

    public void setMetrosCuadradosTotales(Double metrosCuadradosTotales) {
        this.metrosCuadradosTotales = metrosCuadradosTotales;
    }

    public Double getMetrosCuadradosEdificados() {
        return metrosCuadradosEdificados;
    }

    public void setMetrosCuadradosEdificados(Double metrosCuadradosEdificados) {
        this.metrosCuadradosEdificados = metrosCuadradosEdificados;
    }

    public Double getMetrosCuadradosTerreno() {
        return metrosCuadradosTerreno;
    }

    public void setMetrosCuadradosTerreno(Double metrosCuadradosTerreno) {
        this.metrosCuadradosTerreno = metrosCuadradosTerreno;
    }

    public String getTitularActualUTE() {
        return titularActualUTE;
    }

    public void setTitularActualUTE(String titularActualUTE) {
        this.titularActualUTE = titularActualUTE;
    }

    public String getTitularActualOSE() {
        return titularActualOSE;
    }

    public void setTitularActualOSE(String titularActualOSE) {
        this.titularActualOSE = titularActualOSE;
    }

    public String getTipoTecho() {
        return tipoTecho;
    }

    public void setTipoTecho(String tipoTecho) {
        this.tipoTecho = tipoTecho;
    }
    @Override
    public String toString(){
        return this.descripcion+" - "+this.direccion;
    }
}
