
package com.dux.pruebatecnica.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name = "equipos")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La liga del equipo es obligatoria")
    @Column(nullable = false)
    private String liga;

    @NotBlank(message = "El país del equipo es obligatorio")
    @Column(nullable = false)
    private String pais;

    public Equipo() {}

    public Equipo(String nombre, String liga, String pais) {
        this.nombre = nombre;
        this.liga = liga;
        this.pais = pais;
    }
    public Long getId() {
        return id;}

    public void setId(Long id) {
        this.id = id;}

    public String getNombre() {
        return nombre;
    }

    //Asigna nuevo valor al atributo
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
}