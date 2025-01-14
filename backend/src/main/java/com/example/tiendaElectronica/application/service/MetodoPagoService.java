package com.example.tiendaElectronica.application.service;

import com.example.tiendaElectronica.domain.model.MetodoPago;
import com.example.tiendaElectronica.domain.ports.in.MetodoPagoUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagoService implements MetodoPagoUseCase {
    private final MetodoPagoUseCase metodoPagoUseCase;

    public MetodoPagoService(MetodoPagoUseCase metodoPagoUseCase) {

        this.metodoPagoUseCase = metodoPagoUseCase;
    }

    @Override
    public MetodoPago crearMetodoPago(MetodoPago metodoPago) {

        return metodoPagoUseCase.crearMetodoPago(metodoPago);
    }

    @Override
    public Optional<MetodoPago> getMetodoPago(Long id) {
        return metodoPagoUseCase.getMetodoPago(id);
    }

    @Override
    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoUseCase.getAllMetodosPago();
    }
}