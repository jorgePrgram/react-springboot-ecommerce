package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.Cliente;
import com.example.tiendaElectronica.domain.model.Usuario;
import com.example.tiendaElectronica.infraestructure.entity.ClienteEntity;
import com.example.tiendaElectronica.infraestructure.entity.UsuarioEntity;
import com.example.tiendaElectronica.infraestructure.mapper.UsuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class UsuarioJpaRepositoryAdapterTest {

    @Mock
    UsuarioJpaRepository usuarioJpaRepository;
    @Mock
    UsuarioMapper usuarioMapper;

    @InjectMocks
    UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void save() {
        Cliente cliente=new Cliente(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        ClienteEntity clienteEntity=new ClienteEntity(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        Usuario usuario=new Usuario(2L,"johndoe123","contraseña1","carlos543@gmail.com","user",cliente);
        UsuarioEntity usuarioEntity=new UsuarioEntity(2L,"johndoe123","contraseña1","carlos543@gmail.com","user",clienteEntity);


        when(usuarioMapper.toUsuario(usuarioEntity)).thenReturn(usuario);
       when(usuarioMapper.toUsuarioEntity(usuario)).thenReturn(usuarioEntity);
        when(usuarioJpaRepository.save(usuarioEntity)).thenReturn(usuarioEntity);
        Usuario usuario1=usuarioJpaRepositoryAdapter.save(usuario);

        assertNotNull(usuario1);
        assertEquals(usuario1.getNombreUsuario(),usuario.getNombreUsuario());
        assertEquals(usuario.getCorreoElectronico(),usuario.getCorreoElectronico());
        assertEquals(usuario.getCliente().getId(), usuario1.getCliente().getId());
        assertEquals(usuario.getCliente().getNombre(),usuario1.getCliente().getNombre());


    }

    @Test
    void findByIdExitososUsuarioEntity() {
        Cliente cliente=new Cliente(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        Long id=2L;
                Usuario usuario=new Usuario(id,"johndoe123","contraseña1","carlos543@gmail.com","user",cliente);
        ClienteEntity clienteEntity=new ClienteEntity(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        UsuarioEntity usuarioEntity=new UsuarioEntity(id,"johndoe123","contraseña1","carlos543@gmail.com","user",clienteEntity);

        when(usuarioJpaRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioMapper.toUsuario(usuarioEntity)).thenReturn(usuario);

        Optional<Usuario>optionalUsuario=usuarioJpaRepositoryAdapter.findById(id);
        assertTrue(optionalUsuario.isPresent());
        Usuario usuarioEncontrado=optionalUsuario.get();


        assertEquals(usuarioEncontrado.getCorreoElectronico(),usuario.getCorreoElectronico());
        assertEquals(usuarioEncontrado.getCliente().getNombre(),usuario.getCliente().getNombre());
    }
    @Test
    void findById_IsEmpty(){
        Long id=2L;
        when(usuarioJpaRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Usuario>optionalUsuario=usuarioJpaRepositoryAdapter.findById(id);

        assertTrue(optionalUsuario.isEmpty());

    }

    @Test
    void findAll() {
        Cliente cliente1=new Cliente(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        Usuario usuario1=new Usuario(2L,"johndoe123","contraseña1","carlos543@gmail.com","user",cliente1);
        Cliente cliente2=new Cliente(3L,"Tatiana","Palacios","femenino",new Date(),"988231454");
        Usuario usuario2 = new Usuario(3L, "janedoe456", "contraseña2", "tatiana123@gmail.com", "user",cliente2);
        List<Usuario> usuarios= Arrays.asList(usuario1,usuario2);


        ClienteEntity clienteEntity1 = new ClienteEntity(2L, "Juan", "Sanchez", "masculino", new Date(), "977785431");
        UsuarioEntity usuarioEntity1 = new UsuarioEntity(2L, "johndoe123", "contraseña1", "carlos543@gmail.com","user", clienteEntity1);
        ClienteEntity clienteEntity2 = new ClienteEntity(3L, "Tatiana", "Palacios", "femenino", new Date(), "988231454");
        UsuarioEntity usuarioEntity2 = new UsuarioEntity(3L, "janedoe456", "contraseña2", "tatiana123@gmail.com","user", clienteEntity2);
        List<UsuarioEntity>usuarioEntities=Arrays.asList(usuarioEntity1,usuarioEntity2);

        when(usuarioJpaRepository.findAll()).thenReturn(usuarioEntities);
        when(usuarioMapper.toUsuario(usuarioEntity1)).thenReturn(usuario1);
        when(usuarioMapper.toUsuario(usuarioEntity2)).thenReturn(usuario2);

        List<Usuario> resultado=usuarioJpaRepositoryAdapter.findAll();

        assertNotNull(resultado);
        assertEquals(usuarios.size(),resultado.size());
        assertEquals(usuarios.containsAll(resultado),resultado.containsAll(usuarios));

    }

    @Test
    void updateUsuarioExitosos() {
        Long id=1L;
        Cliente cliente=new Cliente(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        ClienteEntity clienteEntity=new ClienteEntity(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        Usuario usuario=new Usuario(id,"johndoe123","contraseña1","carlos543@gmail.com","user",cliente);
        UsuarioEntity usuarioEntity=new UsuarioEntity(id,"johndoe123","contraseña1","carlos543@gmail.com","user",clienteEntity);

        when(usuarioJpaRepository.existsById(id)).thenReturn(true);
        when(usuarioMapper.toUsuarioEntity(usuario)).thenReturn(usuarioEntity);
        when(usuarioMapper.toUsuario(usuarioEntity)).thenReturn(usuario);
        when(usuarioJpaRepository.save(usuarioEntity)).thenReturn(usuarioEntity);

        Optional<Usuario>optionalUsuario=usuarioJpaRepositoryAdapter.update(id,usuario);
        //assertTrue(optionalUsuario.isPresent());
        assertNotNull(optionalUsuario);
        Usuario usuario1=optionalUsuario.get();

        assertEquals(usuario1.getNombreUsuario(),usuario.getNombreUsuario());
        assertEquals(usuario1.getCliente().getApellido(),usuario.getCliente().getApellido());

    }

    @Test
    void update_IsEmpty(){
        Long id=5L;
        Cliente cliente=new Cliente(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        Usuario usuario=new Usuario(id,"johndoe123","contraseña1","carlos543@gmail.com","user",cliente);

        when(usuarioJpaRepository.existsById(id)).thenReturn(false);
        when(usuarioJpaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Usuario>optionalUsuario=usuarioJpaRepositoryAdapter.update(id,usuario);
        assertTrue(optionalUsuario.isEmpty());

    }

    @Test
    void deleteById_Exist() {

        Long id=3L;
        when(usuarioJpaRepository.existsById(id)).thenReturn(true);
        boolean usuarioEncontrado=usuarioJpaRepositoryAdapter.deleteById(id);

        assertTrue(usuarioEncontrado);
    }
    @Test
    void deleteById_IsEmpty(){
        Long id=2L;
        when(usuarioJpaRepository.existsById(id)).thenReturn(false);
        boolean usuarioNoEncontrado=usuarioJpaRepositoryAdapter.deleteById(id);

        assertFalse(usuarioNoEncontrado);
    }

}