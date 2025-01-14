package com.example.tiendaElectronica.application.usecase;

import com.example.tiendaElectronica.domain.model.Producto;
import com.example.tiendaElectronica.domain.ports.in.ProductoUseCase;
import com.example.tiendaElectronica.domain.ports.out.ProductoRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ProductoUseCaseImpl implements ProductoUseCase {
    private final ProductoRepositoryPort productoRepositoryPort;

    public ProductoUseCaseImpl(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepositoryPort.save(producto);
    }

    @Override
    public Optional<Producto> getProducto(Long id) {
        return productoRepositoryPort.findById(id);
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoRepositoryPort.getAllProducto();
    }

    @Override
    public Optional<Producto> actualizasProducto(Long id, Producto producto) {
        return productoRepositoryPort.update(id,producto);
    }

    @Override
    public boolean deleteProducto(Long id) {
        return productoRepositoryPort.deleteById(id);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre){
        return productoRepositoryPort.findByNombreContainingIgnoreCase(nombre);
    }
}
