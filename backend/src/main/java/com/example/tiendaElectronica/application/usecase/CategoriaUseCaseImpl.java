package com.example.tiendaElectronica.application.usecase;

import com.example.tiendaElectronica.domain.model.Categoria;
import com.example.tiendaElectronica.domain.ports.in.CategoriaUseCase;
import com.example.tiendaElectronica.domain.ports.out.CategoriaRepositoryPort;

import java.util.List;
import java.util.Optional;

public class CategoriaUseCaseImpl implements CategoriaUseCase {
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public CategoriaUseCaseImpl(CategoriaRepositoryPort categoriaRepositoryPort) {
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepositoryPort.save(categoria);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepositoryPort.findAll();
    }

    @Override
    public Optional<Categoria> getCategoria(Long id) {
        return categoriaRepositoryPort.findById(id);
    }

    @Override
    public void addProductToCategory(Long categoriaId, Long productoId) {
        categoriaRepositoryPort.linkProductToCategory(categoriaId,productoId);
    }

    @Override
    public boolean removeProductFromCategory(Long categoriaId, Long productoId) {
        return categoriaRepositoryPort.deleteProductFromCategory(categoriaId,productoId);
    }
}


