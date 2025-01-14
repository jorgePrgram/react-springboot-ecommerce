package com.example.tiendaElectronica.domain.ports.out;

import com.example.tiendaElectronica.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {
    Pedido save(Pedido pedido);
    Optional<Pedido> findById(Long id);
    Optional<Pedido> update(Long id,Pedido pedido);
    List<Pedido> findAll();
    boolean deleteById(Long id);
}
