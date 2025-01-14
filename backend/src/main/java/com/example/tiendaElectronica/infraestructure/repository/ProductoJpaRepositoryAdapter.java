package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.Producto;
import com.example.tiendaElectronica.domain.ports.out.ProductoRepositoryPort;
import com.example.tiendaElectronica.infraestructure.entity.ProductoEntity;
import com.example.tiendaElectronica.infraestructure.mapper.ProductoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductoJpaRepositoryAdapter implements ProductoRepositoryPort {
    private final ProductoJpaRepository productoJpaRepository;
    private final ProductoMapper productoMapper;

    public ProductoJpaRepositoryAdapter(ProductoJpaRepository productoJpaRepository, ProductoMapper productoMapper) {
        this.productoJpaRepository = productoJpaRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    public Producto save(Producto producto) {
        ProductoEntity productoEntity=productoMapper.toProductoEntity(producto);
        Long productId = producto.getId();

        if (productoJpaRepository.existsById(productId)) {
            throw new RuntimeException("Ya existe un producto con ID: " + productId);
        }
        else{
            ProductoEntity saveProductoEntity = productoJpaRepository.save(productoEntity);
            return productoMapper.toProducto(saveProductoEntity);
        }
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoJpaRepository.findById(id).map(productoMapper::toProducto);
    }

    @Override
    public List<Producto> getAllProducto() {
        return productoJpaRepository.findAll().stream().
                map(productoMapper::toProducto).collect(Collectors.toList());
    }

    @Override
    public Optional<Producto> update(Long id, Producto producto) {
        if(productoJpaRepository.existsById(id)){
            ProductoEntity productoEntity=productoMapper.toProductoEntity(producto);
            productoEntity.setId(id);
            ProductoEntity updateProductoEntity=productoJpaRepository.save(productoEntity);
            Producto updateProducto=productoMapper.toProducto(updateProductoEntity);
            return Optional.of(updateProducto);
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if(productoJpaRepository.existsById(id)){
            productoJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public List<Producto> findByNombreContainingIgnoreCase(String nombre) {
        return productoJpaRepository.findByNombreContainingIgnoreCase(nombre).
                stream().map(productoMapper::toProducto)
                .collect(Collectors.toList());
    }

}

