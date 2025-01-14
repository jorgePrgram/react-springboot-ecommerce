package com.example.tiendaElectronica.domain.ports.in;

import com.example.tiendaElectronica.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoUseCase {
    Pedido crearPedido (Pedido pedido);
    List<Pedido> getAllPedido();
    Optional<Pedido>updatePedido(Long id, Pedido pedido);
    Optional<Pedido> getPedido(Long id);
    boolean deletePedido (Long id);

}
