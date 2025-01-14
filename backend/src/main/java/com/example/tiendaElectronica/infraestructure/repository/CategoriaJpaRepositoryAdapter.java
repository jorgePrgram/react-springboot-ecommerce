package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.Categoria;
import com.example.tiendaElectronica.domain.ports.out.CategoriaRepositoryPort;
import com.example.tiendaElectronica.infraestructure.entity.CategoriaEntity;
import com.example.tiendaElectronica.infraestructure.entity.ProductoEntity;
import com.example.tiendaElectronica.infraestructure.mapper.CategoriaMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoriaJpaRepositoryAdapter implements CategoriaRepositoryPort {
    private final CategoriaJpaRepository categoriaJpaRepository;
    private final CategoriaMapper categoriaMapper;
    private final ProductoJpaRepository productoJpaRepository;

    public CategoriaJpaRepositoryAdapter(CategoriaJpaRepository categoriaJpaRepository,CategoriaMapper categoriaMapper,
                                         ProductoJpaRepository productoJpaRepository) {
        this.categoriaJpaRepository = categoriaJpaRepository;
        this.categoriaMapper=categoriaMapper;
        this.productoJpaRepository=productoJpaRepository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity categoriaEntity=categoriaMapper.toCategoriaEntity(categoria);
        CategoriaEntity saveCategoriaEntity=categoriaJpaRepository.save(categoriaEntity);
        return categoriaMapper.toCategoria(saveCategoriaEntity);
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaJpaRepository.findById(id).map(categoriaMapper::toCategoria);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaJpaRepository.findAll().stream().map(categoriaMapper::toCategoria)
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void linkProductToCategory  (Long categoriaId, Long productoId){
        Optional<CategoriaEntity> categoriaOptional=categoriaJpaRepository.findById(categoriaId);
        if (categoriaOptional.isEmpty()){
            throw new RuntimeException("Categoria no encontrada con ID: "+ categoriaId);
        }

        CategoriaEntity categoria=categoriaOptional.get();

        Optional<ProductoEntity> productoOptional=productoJpaRepository.findById(productoId);
        if(productoOptional.isEmpty()){
            throw  new RuntimeException("Producto no encontrado con ID: " + productoId);
        }
        ProductoEntity producto=productoOptional.get();

        categoria.getProductosEntities().add(producto);

        categoriaJpaRepository.save(categoria);

    }
    @Transactional

    @Override
    public boolean deleteProductFromCategory(Long categoriaId, Long productId) {
        if (!categoriaJpaRepository.existsById(categoriaId)) {
            return false;
        }
        CategoriaEntity categoria = categoriaJpaRepository.findById(categoriaId).get();
        Optional<ProductoEntity> productoOptional = productoJpaRepository.findById(productId);
        if (productoOptional.isEmpty()) {
            return false; //
        }
        boolean removed = categoria.getProductosEntities().remove(productoOptional.get());
        if (removed) {
            categoriaJpaRepository.save(categoria);
        }
        return removed;
    }
}
