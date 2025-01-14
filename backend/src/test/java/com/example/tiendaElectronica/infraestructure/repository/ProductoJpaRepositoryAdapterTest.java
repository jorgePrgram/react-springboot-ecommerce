package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.Producto;
import com.example.tiendaElectronica.infraestructure.entity.ProductoEntity;
import com.example.tiendaElectronica.infraestructure.mapper.ProductoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductoJpaRepositoryAdapterTest {
    @Mock
    ProductoJpaRepository productoJpaRepository;
    @Mock
    ProductoMapper productoMapper;
    @InjectMocks
    ProductoJpaRepositoryAdapter productoJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Producto producto=new Producto(1L,155.154,"mouse",20,"link1.com",new HashSet<>());

        ProductoEntity productoEntity=new ProductoEntity(1L,155.154,"mouse","link1.com",20,new HashSet<>());
        when(productoJpaRepository.save(productoEntity)).thenReturn(productoEntity);
        when(productoMapper.toProductoEntity(producto)).thenReturn(productoEntity);
        when(productoMapper.toProducto(productoEntity)).thenReturn(producto);

        Producto productoAdapter=productoJpaRepositoryAdapter.save(producto);

        assertNotNull(productoAdapter);

        assertEquals(productoAdapter.getNombre(),producto.getNombre());
        assertEquals(productoAdapter.getPrecio(),producto.getPrecio());

    }

    @Test
    void saveExistente(){
        Producto productoExistente=new Producto(1L,155.154,"mouse",20,"link.com",new HashSet<>());
        Long id=productoExistente.getId();
        when(productoJpaRepository.existsById(id)).thenReturn(true);

        assertThrows(RuntimeException.class,()->{
           productoJpaRepositoryAdapter.save(productoExistente);
        });
    }

    @Test
    void findById() {
        Long id = 1L;
        ProductoEntity productoEntity = new ProductoEntity(id, 156.23,"teclado","link1.com" ,100,new HashSet<>());
        Producto producto=new Producto(id, 156.23,"teclado",100,"link1.com",new HashSet<>());
        when(productoJpaRepository.findById(id)).thenReturn(Optional.of(productoEntity));
        when(productoMapper.toProducto(productoEntity)).thenReturn(producto);

        Optional<Producto>productoOptional=productoJpaRepositoryAdapter.findById(id);
        assertNotNull(productoOptional);
        Producto productoExitoso=productoOptional.get();

        assertEquals(id,productoExitoso.getId());
        assertEquals(productoEntity.getNombre(),productoExitoso.getNombre());
    }

    @Test
    void getAllProducto() {
        Producto producto1 = new Producto(1L, 156.23, "teclado", 100,"link1.com", new HashSet<>());
        Producto producto2 = new Producto(2L, 299.99, "mouse", 50,"link22.com", new HashSet<>());
        List<Producto> productos = Arrays.asList(producto1, producto2);

        ProductoEntity productoEntity1 = new ProductoEntity(1L, 156.23, "teclado","link1.com",100, new HashSet<>());
        ProductoEntity productoEntity2 = new ProductoEntity(2L, 299.99, "mouse", "link22.com",50, new HashSet<>());
        List<ProductoEntity> productoEntities = Arrays.asList(productoEntity1, productoEntity2);
        // Configuración de mocks
        when(productoMapper.toProducto(productoEntity1)).thenReturn(producto1);
        when(productoMapper.toProducto(productoEntity2)).thenReturn(producto2);
        when(productoJpaRepository.findAll()).thenReturn(productoEntities);

        // Llamada al método bajo prueba
        List<Producto> resultado = productoJpaRepositoryAdapter.getAllProducto();

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(productos.size(), resultado.size());
        assertTrue(resultado.containsAll(productos));
        assertTrue(productos.containsAll(resultado));

    }

    @Test
    void updateProductoExitoso() {
        Long id = 1L;
        Producto productoActualizado = new Producto(id, 156.23, "teclado actualizado", 100,"link1.com", new HashSet<>());


        when(productoJpaRepository.existsById(id)).thenReturn(true);

        ProductoEntity productoEntityActualizado = new ProductoEntity(id, 156.23, "teclado actualizado", "link1.com",100, new HashSet<>());

        when(productoMapper.toProductoEntity(productoActualizado)).thenReturn(productoEntityActualizado);
        when(productoMapper.toProducto(productoEntityActualizado)).thenReturn(productoActualizado);
        when(productoJpaRepository.save(productoEntityActualizado)).thenReturn(productoEntityActualizado);


        Optional<Producto> resultado = productoJpaRepositoryAdapter.update(id, productoActualizado);

        // Verificaciones
        assertTrue(resultado.isPresent());
        Producto productoResultado = resultado.get();

        assertEquals(id, productoResultado.getId());
        assertEquals(productoActualizado.getNombre(), productoResultado.getNombre());
    }
    @Test
    void updateProducto_NoExiste(){
        Long id = 1L;
        Producto producto = new Producto(id, 156.23, "teclado",100,"link2.com",  new HashSet<>());

        when(productoJpaRepository.existsById(id)).thenReturn(false);


        Optional<Producto> resultado = productoJpaRepositoryAdapter.update(id, producto);

        // Verificación
        assertTrue(resultado.isEmpty());

    }

    @Test
    void deleteById() {
        Long id = 1L;
        when(productoJpaRepository.existsById(id)).thenReturn(true);
        boolean exitoso = productoJpaRepositoryAdapter.deleteById(id);
        assertTrue(exitoso);

    }
    @Test
    void deleteByIdNoExiste(){
        Long id = 1L;

        when(productoJpaRepository.existsById(id)).thenReturn(false);

        boolean vacio = productoJpaRepositoryAdapter.deleteById(id);

        assertFalse(vacio);
    }
}