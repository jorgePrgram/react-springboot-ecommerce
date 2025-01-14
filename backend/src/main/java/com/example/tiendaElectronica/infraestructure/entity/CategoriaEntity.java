package com.example.tiendaElectronica.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToMany
    @JoinTable(
            name = "producto_categoria",
            joinColumns = @JoinColumn(name = "categoria_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private Set<ProductoEntity> productosEntities = new HashSet<>();

    public CategoriaEntity() {
    }

    public CategoriaEntity(Long id, String nombre, Set<ProductoEntity> productosEntities) {
        this.id = id;
        this.nombre = nombre;
        this.productosEntities = productosEntities;
    }
}
