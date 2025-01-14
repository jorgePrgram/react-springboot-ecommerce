package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.Categoria;
import com.example.tiendaElectronica.infraestructure.entity.CategoriaEntity;
import com.example.tiendaElectronica.infraestructure.entity.ProductoEntity;
import com.example.tiendaElectronica.infraestructure.mapper.CategoriaMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoriaJpaRepositoryAdapterTest {

    @Mock
    CategoriaJpaRepository categoriaJpaRepository;
    @Mock
    CategoriaMapper categoriaMapper;
    @Mock
    ProductoJpaRepository productoJpaRepository;

    @InjectMocks
    CategoriaJpaRepositoryAdapter categoriaJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
            MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Categoria categoria=new Categoria(2L,"almacenamiento",new HashSet<>());
        CategoriaEntity categoriaEntity=new CategoriaEntity(2L,"almacenamiento",new HashSet<>());

        when(categoriaMapper.toCategoriaEntity(categoria)).thenReturn(categoriaEntity);
        when(categoriaMapper.toCategoria(categoriaEntity)).thenReturn(categoria);

        when(categoriaJpaRepository.save(categoriaEntity)).thenReturn(categoriaEntity);

        Categoria categoriaAdapter=categoriaJpaRepositoryAdapter.save(categoria);

        assertNotNull(categoriaAdapter);

        assertEquals(categoriaAdapter.getNombre(),categoria.getNombre());
    }

    @Test
    void findById() {
    Long id=2L;
    CategoriaEntity categoriaEntity=new CategoriaEntity(id,"almacenamiento",new HashSet<>());

    when(categoriaJpaRepository.findById(id)).thenReturn(Optional.of(categoriaEntity));
    Categoria categoria=new Categoria(id,"almacenamiento",new HashSet<>());
    when(categoriaMapper.toCategoria(categoriaEntity)).thenReturn(categoria);
    Optional<Categoria> categoriaAdapter=categoriaJpaRepositoryAdapter.findById(id);

    Categoria categoriaExitoso=categoriaAdapter.get();

    assertNotNull(categoriaExitoso);

    assertEquals(categoriaExitoso.getNombre(),categoriaEntity.getNombre());
    }
    @Test
    void findAll(){
        CategoriaEntity categoriaEntity1=new CategoriaEntity(1L,"lapton",new HashSet<>());
        CategoriaEntity categoriaEntity2=new CategoriaEntity(2L,"tarjetas graficas",new HashSet<>());
        List<CategoriaEntity>categoriaEntities= Arrays.asList(categoriaEntity1,categoriaEntity2);

        when(categoriaJpaRepository.findAll()).thenReturn(categoriaEntities);

        List<Categoria> categoriaAdapter=categoriaJpaRepositoryAdapter.findAll();

        assertNotNull(categoriaAdapter);
        assertEquals(categoriaAdapter.size(),categoriaEntities.size());
        assertEquals(categoriaAdapter.containsAll(categoriaEntities),categoriaEntities.containsAll(categoriaAdapter));
    }

    @Test
    void linkProductToCategory_Success(){
        Long categoriaId=1L;
        Long productoId=1L;

        CategoriaEntity categoriaEntity=new CategoriaEntity(categoriaId,"almacenamiento",new HashSet<>());
        ProductoEntity productoEntity=new ProductoEntity(productoId,200.0,"disco duro","link.1",30, new HashSet<>());

        when(categoriaJpaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaEntity));
        when(productoJpaRepository.findById(productoId)).thenReturn(Optional.of(productoEntity));

        categoriaJpaRepositoryAdapter.linkProductToCategory(categoriaId,productoId);

        assertTrue(categoriaEntity.getProductosEntities().contains(productoEntity));
    }

    @Test
    void linkProductToCategory_CategoryNotFound(){
        Long categoriaId = 1L;
        Long productoId = 1L;
        when(categoriaJpaRepository.findById(categoriaId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                categoriaJpaRepositoryAdapter.linkProductToCategory(categoriaId, productoId)
        );
        assertEquals("Categoria no encontrada con ID: " + categoriaId, exception.getMessage());
    }

    @Test
    void linkProductToCategory_ProductNotFound() {
        Long categoriaId = 1L;
        Long productoId = 1L;

        CategoriaEntity categoriaEntity = new CategoriaEntity(categoriaId, "almacenamiento", new HashSet<>());

        when(categoriaJpaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaEntity));
        when(productoJpaRepository.findById(productoId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                categoriaJpaRepositoryAdapter.linkProductToCategory(categoriaId, productoId)
        );

        assertEquals("Producto no encontrado con ID: " + productoId, exception.getMessage());
    }

    @Test
    void deleteProductFromCategory_Success() {
        Long categoriaId = 1L;
        Long productoId = 1L;

        CategoriaEntity categoriaEntity = new CategoriaEntity(categoriaId, "almacenamiento", new HashSet<>());
        ProductoEntity productoEntity=new ProductoEntity(productoId,200.0,"disco duro","link.1",30, new HashSet<>());

        categoriaEntity.getProductosEntities().add(productoEntity);

        when(categoriaJpaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaEntity));
        when(productoJpaRepository.findById(productoId)).thenReturn(Optional.of(productoEntity));
        when(categoriaJpaRepository.existsById(categoriaId)).thenReturn(true);

        boolean result = categoriaJpaRepositoryAdapter.deleteProductFromCategory(categoriaId, productoId);

        assertTrue(result);
        assertFalse(categoriaEntity.getProductosEntities().contains(productoEntity));
        verify(categoriaJpaRepository).save(categoriaEntity); // Verifica que se guarda la categoría
    }

    @Test
    void deleteProductFromCategory_CategoryNotFound() {
        Long categoriaId = 1L;
        Long productoId = 1L;

        when(categoriaJpaRepository.existsById(categoriaId)).thenReturn(false);

        boolean result = categoriaJpaRepositoryAdapter.deleteProductFromCategory(categoriaId, productoId);

        assertFalse(result); // Debe devolver false si la categoría no existe
        verify(categoriaJpaRepository, never()).save(any()); // No debe guardar la categoría
    }


    @Test
    void deleteProductFromCategory_ProductNotFound() {
        Long categoriaId = 1L;
        Long productoId = 1L;

        CategoriaEntity categoriaEntity = new CategoriaEntity(categoriaId, "almacenamiento", new HashSet<>());

        when(categoriaJpaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaEntity));
        when(productoJpaRepository.findById(productoId)).thenReturn(Optional.empty());

        boolean result = categoriaJpaRepositoryAdapter.deleteProductFromCategory(categoriaId, productoId);

        assertFalse(result); // Debe devolver false si el producto no existe
        verify(categoriaJpaRepository, never()).save(any()); // No debe guardar la categoría
    }


}