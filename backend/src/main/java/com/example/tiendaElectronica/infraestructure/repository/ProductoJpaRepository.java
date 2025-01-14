package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.infraestructure.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoJpaRepository extends JpaRepository<ProductoEntity,Long> {
    List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre);
}
