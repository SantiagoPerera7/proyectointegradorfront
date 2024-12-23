/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariafront.dto;

/**
 *
 * @author santi
 */
public class FotoDTO {
    private Integer id;
    private String url;
    private PropiedadDTO propiedad;

    public FotoDTO() {
    }

    public FotoDTO(String url, PropiedadDTO propiedad) {
        this.url = url;
        this.propiedad = propiedad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PropiedadDTO getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadDTO propiedad) {
        this.propiedad = propiedad;
    }

    @Override
    public String toString() {
        return "Foto{" +
               "id=" + id +
               ", url='" + url + '\'' +
               '}';
    }
}

