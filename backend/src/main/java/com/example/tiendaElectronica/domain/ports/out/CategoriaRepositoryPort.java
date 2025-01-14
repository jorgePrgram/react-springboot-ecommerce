package com.example.tiendaElectronica.domain.ports.out;

import com.example.tiendaElectronica.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {
    Categoria save (Categoria categoria);
    Optional<Categoria> findById(Long id);
    List<Categoria> findAll();

    void linkProductToCategory(Long categoriaId, Long productoId);
    boolean deleteProductFromCategory(Long categoriaId, Long productId);
}
