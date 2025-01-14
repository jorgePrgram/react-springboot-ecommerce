package com.example.tiendaElectronica.domain.ports.out;

import com.example.tiendaElectronica.domain.model.DetallePedido;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoRepositoryPort {
    DetallePedido save (DetallePedido detallePedido);
    Optional<DetallePedido> findById(Long id);
    List<DetallePedido>findAll();
}
