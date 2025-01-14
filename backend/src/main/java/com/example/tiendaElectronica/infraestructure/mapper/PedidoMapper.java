package com.example.tiendaElectronica.infraestructure.mapper;

import com.example.tiendaElectronica.domain.model.Pedido;
import com.example.tiendaElectronica.infraestructure.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    @Mappings({
            @Mapping(source = "usuario",target="usuarioEntity"),
            @Mapping(source = "metodoPago",target = "metodoPagoEntity"),
            @Mapping(source = "detallePedidos",target = "detallePedidoEntities")
    })
    PedidoEntity toPedidoEntity(Pedido pedido);
    @Mappings({
            @Mapping(source="usuarioEntity",target="usuario"),
            @Mapping(source = "metodoPagoEntity",target = "metodoPago"),
            @Mapping(source = "detallePedidoEntities",target = "detallePedidos")
    })
    Pedido toPedido(PedidoEntity pedidoEntity);
}
