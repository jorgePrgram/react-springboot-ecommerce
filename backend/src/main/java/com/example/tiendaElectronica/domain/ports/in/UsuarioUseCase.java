package com.example.tiendaElectronica.domain.ports.in;

import com.example.tiendaElectronica.domain.model.Usuario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UsuarioUseCase {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuario(Long id);
    Optional<Usuario> updateUsuario(Long id,Usuario usuario);
    boolean deleteUsuario(Long id);

    String login(Map<String,String> requestMap);
}
