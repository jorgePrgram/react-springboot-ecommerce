package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.infraestructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity,Long> {

    UsuarioEntity findByNombreUsuario(String nombreUsuario);
}
