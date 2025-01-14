package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.infraestructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity,Long> {
}
