package com.example.tiendaElectronica.application.service;

import com.example.tiendaElectronica.domain.model.Usuario;
import com.example.tiendaElectronica.domain.ports.in.UsuarioUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService implements UsuarioUseCase {

    private final UsuarioUseCase usuarioUseCase;

    public UsuarioService(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioUseCase.crearUsuario(usuario);
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {
        return usuarioUseCase.getUsuario(id);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioUseCase.getAllUsuarios();
    }

    @Override
    public Optional<Usuario> updateUsuario(Long id, Usuario usuario) {
        return usuarioUseCase.updateUsuario(id,usuario);
    }

    @Override
    public boolean deleteUsuario(Long id) {
        return usuarioUseCase.deleteUsuario(id);
    }

    @Override
    public String login(Map<String, String> requestMap) {
        return usuarioUseCase.login(requestMap);
    }
}
