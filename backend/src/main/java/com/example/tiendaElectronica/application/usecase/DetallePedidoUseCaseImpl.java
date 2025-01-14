package com.example.tiendaElectronica.application.usecase;

import com.example.tiendaElectronica.domain.model.DetallePedido;
import com.example.tiendaElectronica.domain.ports.in.DetallePedidoUseCase;
import com.example.tiendaElectronica.domain.ports.out.DetallePedidoRepositoryPort;

import java.util.List;
import java.util.Optional;

public class DetallePedidoUseCaseImpl implements DetallePedidoUseCase {
    private final DetallePedidoRepositoryPort detallePedidoRepositoryPort;

    public DetallePedidoUseCaseImpl(DetallePedidoRepositoryPort detallePedidoRepositoryPort) {
        this.detallePedidoRepositoryPort = detallePedidoRepositoryPort;
    }

    @Override
    public DetallePedido crearDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepositoryPort.save(detallePedido);
    }

    @Override
    public List<DetallePedido> getAllDetallePedidos() {
        return detallePedidoRepositoryPort.findAll();
    }

    @Override
    public Optional<DetallePedido> getDetallePedido(Long id) {
        return detallePedidoRepositoryPort.findById(id);
    }
}
