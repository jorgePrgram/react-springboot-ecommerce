package com.example.tiendaElectronica.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class Producto {
    private Long id;
    private Double precio;
    private String nombre;
    private int stock;
    private String imagen;
    private Set<Categoria> categorias;

    public Producto(Long id, Double precio, String nombre, int stock, String imagen, Set<Categoria> categorias) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.imagen=imagen;
        this.stock = stock;
        this.categorias = categorias;
    }
}
