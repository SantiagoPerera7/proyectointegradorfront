/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariafront.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santi
 */
public class InquilinoDTO{

    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;
    //@OneToMany(mappedBy = "inquilino", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonBackReference("inquilino-alquileres")
    //private List<Alquiler> alquileres = new ArrayList<>();


    public InquilinoDTO() {
    }

    public InquilinoDTO(String nombre, String apellido, String direccion) {
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

    //public List<Alquiler> getAlquileres() {
    //    return alquileres;
    //}

    //public void setAlquileres(List<Alquiler> alquileres) {
    //    this.alquileres = alquileres;
    //}

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido + " - " + this.direccion;
    }
}
