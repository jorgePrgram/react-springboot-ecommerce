package com.example.tiendaElectronica.domain.ports.out;

import com.example.tiendaElectronica.domain.model.Usuario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    List<Usuario> findAll();
    Optional<Usuario> update(Long id, Usuario usuario);
    boolean deleteById(Long id);


    String login (Map<String,String> requestMap);
}
