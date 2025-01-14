package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.*;
import com.example.tiendaElectronica.infraestructure.entity.*;
import com.example.tiendaElectronica.infraestructure.mapper.DetallePedidoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DetallePedidoJpaRepositoryAdapterTest {
    @Mock
    DetallePedidoJpaRepository detallePedidoJpaRepository;
    @Mock
    DetallePedidoMapper detallePedidoMapper;
    @Mock
    ProductoJpaRepository productoJpaRepository;
    @Mock
    PedidoJpaRepository pedidoJpaRepository;
    @InjectMocks
    DetallePedidoJpaRepositoryAdapter detallePedidoJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Pedido pedido = new Pedido(1L, 300.00, new Usuario(), new MetodoPago());
        Producto producto = new Producto(1L, 155.154, "mouse", 20,"imagen.link.com", new HashSet<>());
        DetallePedido detallePedido=new DetallePedido(1L,pedido ,producto,30);


        PedidoEntity pedidoEntity=new PedidoEntity(1L, 300.00, new UsuarioEntity(), new MetodoPagoEntity());
        ProductoEntity productoEntity=new ProductoEntity(1L, 155.154, "mouse", "imagen.link.com",20, new HashSet<>());
        DetallePedidoEntity detallePedidoEntity=new DetallePedidoEntity(1L,pedidoEntity ,productoEntity,30);


      //  when(pedidoMapper.toPedidoEntity(pedido)).thenReturn(pedidoEntity);
       // when(productoMapper.toProductoEntity(producto)).thenReturn(productoEntity);
        when(detallePedidoMapper.toDetallePedidoEntity(detallePedido)).thenReturn(detallePedidoEntity);
        when(detallePedidoMapper.toDetallePedido(detallePedidoEntity)).thenReturn(detallePedido);
        when(productoJpaRepository.findById(1L)).thenReturn(Optional.of(productoEntity)); // Simular el producto encontrado
        when(pedidoJpaRepository.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        when(detallePedidoJpaRepository.save(detallePedidoEntity)).thenReturn(detallePedidoEntity);

        // Llamada al método save en el adapter y verificación
        DetallePedido detallePedidoGuardado = detallePedidoJpaRepositoryAdapter.save(detallePedido);

        assertNotNull(detallePedidoGuardado);
        assertEquals(detallePedido.getId(), detallePedidoGuardado.getId());
    }

    @Test
    void findById() {
        Long id=1L;
        PedidoEntity pedidoEntity=new PedidoEntity(2L, 300.00, new UsuarioEntity(), new MetodoPagoEntity());
        ProductoEntity productoEntity=new ProductoEntity(3L, 155.154, "mouse","link.com", 20, new HashSet<>());
        DetallePedidoEntity detallePedidoEntity=new DetallePedidoEntity(id,pedidoEntity ,productoEntity,30);
        Pedido pedido = new Pedido(2L, 300.00, new Usuario(), new MetodoPago());
        Producto producto = new Producto(3L, 155.154, "mouse", 20,"link.com", new HashSet<>());
        DetallePedido detallePedido=new DetallePedido(id,pedido ,producto,30);

        when(detallePedidoJpaRepository.findById(id)).thenReturn(Optional.of(detallePedidoEntity));
        when(detallePedidoMapper.toDetallePedido(detallePedidoEntity)).thenReturn(detallePedido);
        Optional<DetallePedido> detallePedidoAdapter=detallePedidoJpaRepositoryAdapter.findById(id);

        assertTrue(detallePedidoAdapter.isPresent());
        DetallePedido detallePedidoEncontrado=detallePedidoAdapter.get();

        assertEquals(detallePedidoEncontrado.getCantidad(),detallePedidoEntity.getCantidad());
        assertEquals(detallePedidoEncontrado.getId(),detallePedidoEntity.getId());


    }

    @Test
    void findAll() {
        // Datos de dominio
        Pedido pedido1 = new Pedido(1L, 300.00, new Usuario(), new MetodoPago());
        Producto producto1 = new Producto(1L, 155.154, "mouse", 20,"link.com", new HashSet<>());
        DetallePedido detallePedido1 = new DetallePedido(1L, pedido1, producto1, 30);

        Pedido pedido2 = new Pedido(2L, 500.00, new Usuario(), new MetodoPago());
        Producto producto2 = new Producto(2L, 299.99, "keyboard",15, "link2.com", new HashSet<>());
        DetallePedido detallePedido2 = new DetallePedido(2L, pedido2, producto2, 10);

        List<DetallePedido> detallesPedidos = Arrays.asList(detallePedido1, detallePedido2);

        // Datos de entidad
        PedidoEntity pedidoEntity1 = new PedidoEntity(1L, 300.00, new UsuarioEntity(), new MetodoPagoEntity());
        ProductoEntity productoEntity1 = new ProductoEntity(1L, 155.154, "mouse","link.com", 20, new HashSet<>());
        DetallePedidoEntity detallePedidoEntity1 = new DetallePedidoEntity(1L, pedidoEntity1, productoEntity1, 30);

        PedidoEntity pedidoEntity2 = new PedidoEntity(2L, 500.00, new UsuarioEntity(), new MetodoPagoEntity());
        ProductoEntity productoEntity2 = new ProductoEntity(2L, 299.99, "keyboard","link2.com", 15, new HashSet<>());
        DetallePedidoEntity detallePedidoEntity2 = new DetallePedidoEntity(2L, pedidoEntity2, productoEntity2, 10);

        List<DetallePedidoEntity> detallePedidoEntities = Arrays.asList(detallePedidoEntity1, detallePedidoEntity2);

        // Configuración de los mocks
        when(detallePedidoJpaRepository.findAll()).thenReturn(detallePedidoEntities);
        when(detallePedidoMapper.toDetallePedido(detallePedidoEntity1)).thenReturn(detallePedido1);
        when(detallePedidoMapper.toDetallePedido(detallePedidoEntity2)).thenReturn(detallePedido2);

        // Llamada al método del adaptador
        List<DetallePedido> resultado = detallePedidoJpaRepositoryAdapter.findAll();

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(detallesPedidos.size(), resultado.size());
        assertTrue(detallesPedidos.containsAll(resultado));
        assertTrue(resultado.containsAll(detallesPedidos));



    }
}