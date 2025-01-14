package com.example.tiendaElectronica.domain.ports.in;

import com.example.tiendaElectronica.domain.model.MetodoPago;

import java.util.List;
import java.util.Optional;

public interface MetodoPagoUseCase {

    MetodoPago crearMetodoPago(MetodoPago metodoPago);
    Optional<MetodoPago>getMetodoPago(Long id);
    List<MetodoPago> getAllMetodosPago();


}