package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.MetodoPago;
import com.example.tiendaElectronica.domain.model.Pedido;
import com.example.tiendaElectronica.domain.model.Usuario;
import com.example.tiendaElectronica.infraestructure.entity.MetodoPagoEntity;
import com.example.tiendaElectronica.infraestructure.entity.PedidoEntity;
import com.example.tiendaElectronica.infraestructure.entity.UsuarioEntity;
import com.example.tiendaElectronica.infraestructure.mapper.PedidoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoJpaRepositoryAdapterTest {
    @Mock
    PedidoJpaRepository pedidoJpaRepository;
    @Mock
    PedidoMapper pedidoMapper;
    @Mock
    UsuarioJpaRepository usuarioJpaRepository;
    @Mock
    MetodoPagoJpaRepository metodoPagoJpaRepository;

    @InjectMocks
    PedidoJpaRepositoryAdapter pedidoJpaRepositoryAdapter;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {


        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(1L);
        MetodoPagoEntity metodoPagoEntity = new MetodoPagoEntity();
        metodoPagoEntity.setId(1L);
        Pedido pedido=new Pedido(1L,300.00,new Usuario(),new MetodoPago());
        PedidoEntity pedidoEntity=new PedidoEntity(1L,300.00,usuarioEntity,metodoPagoEntity);

        when(usuarioJpaRepository.findById(pedido.getUsuario().getId())).thenReturn(Optional.of(usuarioEntity));
        when(metodoPagoJpaRepository.findById(pedido.getMetodoPago().getId())).thenReturn(Optional.of(metodoPagoEntity));

        assertNotNull(pedido.getUsuario(), "Usuario no puede ser nulo");
        assertTrue(usuarioJpaRepository.findById(pedido.getUsuario().getId()).isPresent(), "Usuario no existe");

        assertNotNull(pedido.getMetodoPago(), "Método de pago no puede ser nulo");
        assertTrue(metodoPagoJpaRepository.findById(pedido.getMetodoPago().getId()).isPresent(), "Método de pago no existe");

        when(pedidoMapper.toPedido(pedidoEntity)).thenReturn(pedido);
        when(pedidoMapper.toPedidoEntity(pedido)).thenReturn(pedidoEntity);

        when(pedidoJpaRepository.save(pedidoEntity)).thenReturn(pedidoEntity);

        Pedido resultado = pedidoJpaRepositoryAdapter.save(pedido);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(pedido.getId(), resultado.getId());
        assertEquals(pedido.getMontoTotal(), resultado.getMontoTotal());


    }

    @Test
    void findByIdExitoso() {
        Long id=1L;
        Pedido pedido=new Pedido(id,300.00,new Usuario(),new MetodoPago());
        PedidoEntity pedidoEntity=new PedidoEntity(id,300.00,new UsuarioEntity(),new MetodoPagoEntity());

        when(pedidoJpaRepository.findById(id)).thenReturn(Optional.of(pedidoEntity));

        when(pedidoMapper.toPedido(pedidoEntity)).thenReturn(pedido);

        Optional<Pedido>optionalPedido=pedidoJpaRepositoryAdapter.findById(id);
        Pedido pedidoEncontrado=optionalPedido.get();

        assertTrue(optionalPedido.isPresent());


        assertEquals(300,pedidoEncontrado.getMontoTotal());


    }


    @Test
    void findAll() {
        // Datos de ejemplo con double
        Pedido pedido1 = new Pedido(1L, 300.00, new Usuario(), new MetodoPago());
        Pedido pedido2 = new Pedido(2L, 250.00, new Usuario(), new MetodoPago());
        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        // Mapeo a entidades
        PedidoEntity pedidoEntity1 = new PedidoEntity(1L, 300.00, new UsuarioEntity(), new MetodoPagoEntity());
        PedidoEntity pedidoEntity2 = new PedidoEntity(2L, 250.00, new UsuarioEntity(), new MetodoPagoEntity());
        List<PedidoEntity> pedidoEntities = Arrays.asList(pedidoEntity1, pedidoEntity2);

        // Configurar comportamiento simulado del repositorio y del mapper
        when(pedidoMapper.toPedido(pedidoEntity1)).thenReturn(pedido1);
        when(pedidoMapper.toPedido(pedidoEntity2)).thenReturn(pedido2);
        when(pedidoJpaRepository.findAll()).thenReturn(pedidoEntities);

        // Ejecutar el método bajo prueba
        List<Pedido> resultado = pedidoJpaRepositoryAdapter.findAll();

        // Verificar resultados
        assertNotNull(resultado);
        assertEquals(pedidos.size(), resultado.size());
        assertTrue(resultado.containsAll(pedidos) && pedidos.containsAll(resultado));
    }

    @Test
    void update() {
        Long id=1L;
        Pedido pedidoActualizado=new Pedido(id,300.00,new Usuario(),new MetodoPago());
        PedidoEntity pedidoEntityActualizado=new PedidoEntity(id,300.00,new UsuarioEntity(),new MetodoPagoEntity());


        when(pedidoJpaRepository.existsById(id)).thenReturn(true);
        when(pedidoMapper.toPedidoEntity(pedidoActualizado)).thenReturn(pedidoEntityActualizado);
        when(pedidoJpaRepository.save(pedidoEntityActualizado)).thenReturn(pedidoEntityActualizado);
        when(pedidoMapper.toPedido(pedidoEntityActualizado)).thenReturn(pedidoActualizado);

        Optional<Pedido> resultado = pedidoJpaRepositoryAdapter.update(id, pedidoActualizado);

        assertTrue(resultado.isPresent());
        Pedido pedidoResultado = resultado.get();
        assertEquals(id, pedidoResultado.getId());
        assertEquals(300.00, pedidoResultado.getMontoTotal());
    }

    @Test
    void deleteById() {
        Long id = 1L;
        when(pedidoJpaRepository.existsById(id)).thenReturn(true);
        boolean exitoso = pedidoJpaRepositoryAdapter.deleteById(id);

        assertTrue(exitoso);
    }

    @Test
    void deleteById_NoExiste(){
        Long productId = 1L;
        when(pedidoJpaRepository.existsById(productId)).thenReturn(false);

        boolean vacio=pedidoJpaRepositoryAdapter.deleteById(productId);

        assertFalse(vacio);
    }
}