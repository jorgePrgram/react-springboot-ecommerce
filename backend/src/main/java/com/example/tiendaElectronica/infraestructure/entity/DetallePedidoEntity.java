package com.example.tiendaElectronica.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "detalle_pedido")

public class DetallePedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="pedido_id",nullable = false)
    @JsonBackReference
    private PedidoEntity pedidoEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="producto_id")
    private ProductoEntity productoEntity;
    private int cantidad;

    public DetallePedidoEntity() {
    }

    public DetallePedidoEntity(Long id, PedidoEntity pedidoEntity, ProductoEntity productoEntity, int cantidad) {
        this.id = id;
        this.pedidoEntity = pedidoEntity;
        this.productoEntity = productoEntity;
        this.cantidad = cantidad;
    }


}
