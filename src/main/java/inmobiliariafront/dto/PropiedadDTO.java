/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariafront.dto;

/**
 *
 * @author Ulx
 */
public class PropiedadDTO {
    private Integer id;
    private String direccion;
    private String descripcion;
    public ClienteDTO propietario;
    public PropiedadDTO(){
    }
    public PropiedadDTO(String direccion, String descripcion, ClienteDTO propietario){
        this.direccion=direccion;
        this.descripcion=descripcion;
        this.propietario=propietario;
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
    @Override
    public String toString(){
        return this.descripcion+" - "+this.direccion;
    }
}
