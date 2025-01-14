package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.infraestructure.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity,Long> {
}