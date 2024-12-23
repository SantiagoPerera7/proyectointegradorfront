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
public class GarantiaDTO{
    private Integer id;
    private String nombre;
    //@OneToMany(mappedBy = "tipoGarantia", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonBackReference("garantia-alquileres")
    //private List<Alquiler> alquileres;
    public GarantiaDTO(){
    
    }
    public GarantiaDTO(String nombre){
        this.nombre=nombre;
    }
    public String getNombre(){
        return nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //public List<Alquiler> getAlquileres() {
    //    return alquileres;
    //}

    //public void setAlquileres(List<Alquiler> alquileres) {
    //    this.alquileres = alquileres;
    //}
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
}


