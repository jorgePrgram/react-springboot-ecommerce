package com.example.tiendaElectronica.application.usecase;

import com.example.tiendaElectronica.domain.model.Pedido;
import com.example.tiendaElectronica.domain.ports.in.PedidoUseCase;
import com.example.tiendaElectronica.domain.ports.out.PedidoRepositoryPort;

import java.util.List;
import java.util.Optional;

public class PedidoUseCaseImpl implements PedidoUseCase {
    private final PedidoRepositoryPort pedidoRepositoryPort;

    public PedidoUseCaseImpl(PedidoRepositoryPort pedidoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepositoryPort.save(pedido);
    }

    @Override
    public List<Pedido> getAllPedido() {
        return pedidoRepositoryPort.findAll();
    }

    @Override
    public Optional<Pedido> getPedido(Long id) {
        return pedidoRepositoryPort.findById(id);
    }


    @Override
    public Optional<Pedido> updatePedido(Long id,Pedido pedido) {
        return pedidoRepositoryPort.update(id, pedido);
    }

    @Override
    public boolean deletePedido(Long id) {
        return pedidoRepositoryPort.deleteById(id);
    }
}
