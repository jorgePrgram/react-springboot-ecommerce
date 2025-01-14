package com.example.tiendaElectronica.infraestructure.mapper;

import com.example.tiendaElectronica.domain.model.DetallePedido;
import com.example.tiendaElectronica.infraestructure.entity.DetallePedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper {

    @Mappings({
            @Mapping(source = "pedidoEntity",target = "pedido"),
            @Mapping(source = "productoEntity",target = "producto")
    })
    DetallePedido toDetallePedido(DetallePedidoEntity detallePedidoEntity);
    @Mappings({
            @Mapping(source = "pedido",target = "pedidoEntity"),
            @Mapping(source = "producto",target = "productoEntity")
    })
    DetallePedidoEntity toDetallePedidoEntity(DetallePedido detallePedido);



}
