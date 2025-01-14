package com.example.tiendaElectronica.infraestructure.mapper;

import com.example.tiendaElectronica.domain.model.Cliente;
import com.example.tiendaElectronica.infraestructure.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toCliente(ClienteEntity clienteEntity);

    ClienteEntity toClienteEntity(Cliente cliente);

}
