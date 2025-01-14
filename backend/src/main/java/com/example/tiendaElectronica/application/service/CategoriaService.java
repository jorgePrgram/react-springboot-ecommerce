package com.example.tiendaElectronica.application.service;

import com.example.tiendaElectronica.domain.model.Categoria;
import com.example.tiendaElectronica.domain.ports.in.CategoriaUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements CategoriaUseCase {
    private final CategoriaUseCase categoriaUseCase;

    public CategoriaService(CategoriaUseCase categoriaUseCase) {
        this.categoriaUseCase = categoriaUseCase;
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaUseCase.crearCategoria(categoria);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaUseCase.getAllCategorias();
    }

    @Override
    public Optional<Categoria> getCategoria(Long id) {
        return categoriaUseCase.getCategoria(id);
    }

    @Override
    public void addProductToCategory(Long categoriaId, Long productoId) {
        categoriaUseCase.addProductToCategory(categoriaId,productoId);
    }

    @Override
    public boolean removeProductFromCategory(Long categoriaId, Long productoId) {
       return categoriaUseCase.removeProductFromCategory(categoriaId,productoId);
    }
}
