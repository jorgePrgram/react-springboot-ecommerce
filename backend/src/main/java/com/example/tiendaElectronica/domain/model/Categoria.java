package com.example.tiendaElectronica.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Categoria {
    private Long id;
    private String nombre;
    private Set<Producto> productos;

    public Categoria() {
    }

    public Categoria(Long id, String nombre, Set<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
    }
}
