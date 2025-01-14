package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.infraestructure.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoJpaRepository extends JpaRepository<PedidoEntity,Long> {
}
