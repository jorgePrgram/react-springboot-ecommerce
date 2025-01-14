package com.example.tiendaElectronica.infraestructure.mapper;

import com.example.tiendaElectronica.domain.model.Producto;
import com.example.tiendaElectronica.infraestructure.entity.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface ProductoMapper {
    @Mappings({
            @Mapping(source = "categorias",target = "categoriaEntities"),
    })
    ProductoEntity toProductoEntity(Producto producto);

    @Mappings({
            @Mapping(source = "categoriaEntities",target = "categorias")
    })
    Producto toProducto(ProductoEntity productoEntity);
}
