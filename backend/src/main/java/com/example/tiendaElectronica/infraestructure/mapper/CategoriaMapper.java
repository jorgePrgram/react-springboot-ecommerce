package com.example.tiendaElectronica.infraestructure.mapper;

import com.example.tiendaElectronica.domain.model.Categoria;
import com.example.tiendaElectronica.infraestructure.entity.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {


    @Mapping(source = "productos", target = "productosEntities")
    CategoriaEntity toCategoriaEntity(Categoria categoria);

    @Mapping(source = "productosEntities", target = "productos")
    Categoria toCategoria(CategoriaEntity categoriaEntity);
}
