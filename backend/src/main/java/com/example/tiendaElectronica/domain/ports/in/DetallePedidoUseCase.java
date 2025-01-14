package com.example.tiendaElectronica.domain.ports.in;

import com.example.tiendaElectronica.domain.model.DetallePedido;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoUseCase {
    DetallePedido crearDetallePedido(DetallePedido detallePedido);
    List<DetallePedido>getAllDetallePedidos();
    Optional<DetallePedido>getDetallePedido(Long id);
}
