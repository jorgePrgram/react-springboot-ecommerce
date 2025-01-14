package com.example.tiendaElectronica.application.service;

import com.example.tiendaElectronica.domain.model.Producto;
import com.example.tiendaElectronica.domain.ports.in.ProductoUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements ProductoUseCase {
    private final ProductoUseCase productoUseCase;

    public ProductoService(ProductoUseCase productoUseCase) {
        this.productoUseCase = productoUseCase;
    }

    @Override
    public Producto crearProducto(Producto producto) {

        return productoUseCase.crearProducto(producto);
    }

    @Override
    public Optional<Producto> getProducto(Long id) {
        return productoUseCase.getProducto(id);
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoUseCase.getAllProductos();
    }

    @Override
    public Optional<Producto> actualizasProducto(Long id, Producto producto) {
        return productoUseCase.actualizasProducto(id,producto);
    }

    @Override
    public boolean deleteProducto(Long id) {
        return productoUseCase.deleteProducto(id);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre){
        return productoUseCase.buscarPorNombre(nombre);
    }
}