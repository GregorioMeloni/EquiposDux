package com.dux.pruebatecnica.controllers.request;

import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;


@ApiModel(description = "Request body para creación/actualización de un equipo")
public class EquipoRequestDTO {
    @Schema(example = "Nuevo Nombre")
    @NotBlank(message = "El nombre del equipo es obligatorio")
    private String nombre;
    @Schema(example = "Nueva Liga")
    @NotBlank(message = "La liga del equipo es obligatoria")
    private String liga;

    @Schema(example = "Nuevo País")
    @NotBlank(message = "El país del equipo es obligatorio")
    private String pais;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

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
