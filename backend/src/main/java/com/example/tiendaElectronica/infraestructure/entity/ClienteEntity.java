package com.example.tiendaElectronica.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String genero;
    private Date fechaNacimiento;
    private String telefono;

    public ClienteEntity() {
    }

    public ClienteEntity(Long id, String nombre, String apellido, String genero, Date fechaNacimiento, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
    }


}
