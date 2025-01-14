package com.example.tiendaElectronica.infraestructure.security;

import com.example.tiendaElectronica.infraestructure.entity.UsuarioEntity;
import com.example.tiendaElectronica.infraestructure.repository.UsuarioJpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CustomerDetailService implements UserDetailsService {
    private final UsuarioJpaRepository usuarioJpaRepository;
    private UsuarioEntity userDetail;

    public CustomerDetailService(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository=usuarioJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        userDetail=usuarioJpaRepository.findByNombreUsuario(username);

        if(!Objects.isNull(userDetail)){
            return new User(userDetail.getNombreUsuario(),userDetail.getContrasenia(),new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public UsuarioEntity getUserDetail(){
        return userDetail;
    }

}
