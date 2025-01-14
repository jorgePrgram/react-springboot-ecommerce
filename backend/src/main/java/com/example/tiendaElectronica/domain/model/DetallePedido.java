package com.example.tiendaElectronica.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DetallePedido {
    private Long id;
    private Pedido pedido;
    private Producto producto;
    private int cantidad;

    public DetallePedido(Long id, Pedido pedido, Producto producto, int cantidad) {
        this.id = id;
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
    }
}
