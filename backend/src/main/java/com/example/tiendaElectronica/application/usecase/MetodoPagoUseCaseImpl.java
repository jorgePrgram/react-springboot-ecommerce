package com.example.tiendaElectronica.application.usecase;

import com.example.tiendaElectronica.domain.model.MetodoPago;
import com.example.tiendaElectronica.domain.ports.in.MetodoPagoUseCase;
import com.example.tiendaElectronica.domain.ports.out.MetodoPagoRepositoryPort;

import java.util.List;
import java.util.Optional;

public class MetodoPagoUseCaseImpl implements MetodoPagoUseCase {

    private final MetodoPagoRepositoryPort metodoPagoRepositoryPort;

    public MetodoPagoUseCaseImpl(MetodoPagoRepositoryPort metodoPagoRepositoryPort) {
        this.metodoPagoRepositoryPort = metodoPagoRepositoryPort;
    }

    @Override
    public MetodoPago crearMetodoPago(MetodoPago metodoPago) {

        return metodoPagoRepositoryPort.save(metodoPago);
    }

    @Override
    public Optional<MetodoPago> getMetodoPago(Long id) {
        return metodoPagoRepositoryPort.findById(id);
    }

    @Override
    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoRepositoryPort.findAll();
    }
}
