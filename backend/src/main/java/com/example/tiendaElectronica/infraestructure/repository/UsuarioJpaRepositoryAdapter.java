package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.Usuario;
import com.example.tiendaElectronica.domain.ports.out.UsuarioRepositoryPort;
import com.example.tiendaElectronica.infraestructure.entity.UsuarioEntity;
import com.example.tiendaElectronica.infraestructure.mapper.UsuarioMapper;
import com.example.tiendaElectronica.infraestructure.security.CustomerDetailService;
import com.example.tiendaElectronica.infraestructure.security.Jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioJpaRepositoryAdapter implements UsuarioRepositoryPort {
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioMapper usuarioMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomerDetailService customerDetailService;


    public UsuarioJpaRepositoryAdapter(UsuarioJpaRepository usuarioJpaRepository, UsuarioMapper usuarioMapper,
                                       AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomerDetailService customerDetailService) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioMapper = usuarioMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customerDetailService = customerDetailService;
    }

    @Override
    public String login(Map<String, String> requestMap) {
      try{
          Authentication authentication=authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          requestMap.get("nombreUsuario"),
                          requestMap.get("contrasenia")
                  )
          );
        if (authentication.isAuthenticated()){
            String userId=String.valueOf(customerDetailService.getUserDetail().getId());
            String token=jwtUtil.generateToken(
                    customerDetailService.getUserDetail().getNombreUsuario(),
                    customerDetailService.getUserDetail().getRole(),
                    userId
            );

            // Formatear correctamente el JSON
            return "{\"token\": \"" + token + "\"}";
        }
      }catch (Exception e){
          e.printStackTrace();
      }
      return null;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity=usuarioMapper.toUsuarioEntity(usuario);
        UsuarioEntity saveUsuarioEntity=usuarioJpaRepository.save(usuarioEntity);
        return usuarioMapper.toUsuario(saveUsuarioEntity);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id).map(usuarioMapper::toUsuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioJpaRepository.findAll().stream().
                map(usuarioMapper::toUsuario).collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> update(Long id, Usuario usuario) {
        if(usuarioJpaRepository.existsById(id)){
            UsuarioEntity usuarioEntity=usuarioMapper.toUsuarioEntity(usuario);
            usuarioEntity.setId(id);
            UsuarioEntity updateUsuarioEntity=usuarioJpaRepository.save(usuarioEntity);
            Usuario updateUsuario=usuarioMapper.toUsuario(updateUsuarioEntity);
            return Optional.of(updateUsuario);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if(usuarioJpaRepository.existsById(id)){
            usuarioJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
