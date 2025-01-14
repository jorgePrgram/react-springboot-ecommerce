package com.example.tiendaElectronica.application.service;

import com.example.tiendaElectronica.domain.model.DetallePedido;
import com.example.tiendaElectronica.domain.ports.in.DetallePedidoUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DetallePedidoService implements DetallePedidoUseCase {

    private final DetallePedidoUseCase detallePedidoUseCase;

    public DetallePedidoService(DetallePedidoUseCase detallePedidoUseCase) {
        this.detallePedidoUseCase = detallePedidoUseCase;
    }

    @Override
    public DetallePedido crearDetallePedido(DetallePedido detallePedido) {
        return detallePedidoUseCase.crearDetallePedido(detallePedido);
    }

    @Override
    public List<DetallePedido> getAllDetallePedidos() {
        return detallePedidoUseCase.getAllDetallePedidos();
    }

    @Override
    public Optional<DetallePedido> getDetallePedido(Long id) {
        return detallePedidoUseCase.getDetallePedido(id);
    }
}
