package com.example.tiendaElectronica.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Cliente {
    private Long id;
    private String nombre;
    private String apellido;
    private String genero;
    private Date fechaNacimiento;
    private String telefono;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String apellido, String genero, Date fechaNacimiento, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
    }



}
