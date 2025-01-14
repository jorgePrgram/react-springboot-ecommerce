package com.example.tiendaElectronica.application.usecase;

import com.example.tiendaElectronica.domain.model.Usuario;
import com.example.tiendaElectronica.domain.ports.in.UsuarioUseCase;
import com.example.tiendaElectronica.domain.ports.out.UsuarioRepositoryPort;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepositoryPort.save(usuario);
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {
        return usuarioRepositoryPort.findById(id);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepositoryPort.findAll();
    }

    @Override
    public Optional<Usuario> updateUsuario(Long id, Usuario usuario) {
        return usuarioRepositoryPort.update(id,usuario);
    }

    @Override
    public boolean deleteUsuario(Long id) {
        return usuarioRepositoryPort.deleteById(id);
    }

    @Override
    public String login(Map<String, String> requestMap) {
        return usuarioRepositoryPort.login(requestMap);
    }
}
