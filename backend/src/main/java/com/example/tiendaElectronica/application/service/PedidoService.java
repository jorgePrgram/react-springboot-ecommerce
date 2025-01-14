package com.example.tiendaElectronica.application.service;

import com.example.tiendaElectronica.domain.model.Pedido;
import com.example.tiendaElectronica.domain.ports.in.PedidoUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements PedidoUseCase {
    private final PedidoUseCase pedidoUseCase;

    public PedidoService(PedidoUseCase pedidoUseCase) {
        this.pedidoUseCase = pedidoUseCase;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        return pedidoUseCase.crearPedido(pedido);
    }

    @Override
    public List<Pedido> getAllPedido() {
        return pedidoUseCase.getAllPedido();
    }

    @Override
    public Optional<Pedido> updatePedido(Long id, Pedido pedido) {
        return pedidoUseCase.updatePedido(id,pedido);
    }

    @Override
    public Optional<Pedido> getPedido(Long id) {
        return pedidoUseCase.getPedido(id);
    }

    @Override
    public boolean deletePedido(Long id) {
        return pedidoUseCase.deletePedido(id);
    }
}
