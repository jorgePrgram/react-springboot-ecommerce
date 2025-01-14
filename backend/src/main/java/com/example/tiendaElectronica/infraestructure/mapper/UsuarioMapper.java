package com.example.tiendaElectronica.infraestructure.mapper;

import com.example.tiendaElectronica.domain.model.Usuario;
import com.example.tiendaElectronica.infraestructure.entity.UsuarioEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mappings({
            @Mapping(source = "clienteEntity", target = "cliente"),
            @Mapping(source = "pedidosEntities",target="pedidos")
    })
    Usuario toUsuario(UsuarioEntity usuarioEntity);
    @Mappings({
            @Mapping(source = "cliente", target = "clienteEntity"),
            @Mapping(source = "pedidos",target = "pedidosEntities")
    })
    UsuarioEntity toUsuarioEntity(Usuario usuario);
}
