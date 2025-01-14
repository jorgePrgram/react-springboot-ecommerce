package com.example.tiendaElectronica.domain.ports.out;

import com.example.tiendaElectronica.domain.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepositoryPort {
    Producto save (Producto producto);
    Optional<Producto> findById(Long id);
    List<Producto> getAllProducto();
    Optional<Producto> update(Long id, Producto producto);
    boolean deleteById(Long id);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

}
