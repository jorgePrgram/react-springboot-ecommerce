package com.example.tiendaElectronica.infraestructure.mapper;

import com.example.tiendaElectronica.domain.model.MetodoPago;
import com.example.tiendaElectronica.infraestructure.entity.MetodoPagoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MetodoPagoMapper {
    MetodoPago toMetodoPago(MetodoPagoEntity metodoPagoEntity);
    MetodoPagoEntity toMetodoPagoEntity(MetodoPago metodoPago);
}
