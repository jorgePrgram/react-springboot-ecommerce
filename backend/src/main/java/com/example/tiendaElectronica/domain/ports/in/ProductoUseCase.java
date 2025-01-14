package com.example.tiendaElectronica.domain.ports.in;

import com.example.tiendaElectronica.domain.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoUseCase {
    Producto crearProducto(Producto producto);
    Optional<Producto> getProducto(Long id);
    List<Producto> getAllProductos();
    Optional<Producto> actualizasProducto(Long id, Producto producto);
    boolean deleteProducto(Long id);

    List<Producto> buscarPorNombre(String nombre);
}
