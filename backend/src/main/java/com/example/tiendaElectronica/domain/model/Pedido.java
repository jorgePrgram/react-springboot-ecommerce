package com.example.tiendaElectronica.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Pedido {
    private Long id;
    private Double montoTotal;
    private Usuario usuario;
    private MetodoPago metodoPago;
    private List<DetallePedido>detallePedidos;

    public Pedido() {
    }

    public Pedido(Long id, Double montoTotal, Usuario usuario, MetodoPago metodoPago) {
        this.id = id;
        this.montoTotal = montoTotal;
        this.usuario = usuario;
        this.metodoPago = metodoPago;
    }
    public Pedido(Long id, Double montoTotal, Usuario usuario, MetodoPago metodoPago, List<DetallePedido> detallePedidos) {
        this.id = id;
        this.montoTotal = montoTotal;
        this.usuario = usuario;
        this.metodoPago = metodoPago;
        this.detallePedidos = detallePedidos;
    }
}
