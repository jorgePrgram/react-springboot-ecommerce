package com.example.tiendaElectronica.domain.ports.out;

import com.example.tiendaElectronica.domain.model.MetodoPago;

import java.util.List;
import java.util.Optional;

public interface MetodoPagoRepositoryPort {
    MetodoPago save(MetodoPago metodoPago);
    Optional<MetodoPago>findById(Long id);
    List<MetodoPago> findAll();
}