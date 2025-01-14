package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.MetodoPago;
import com.example.tiendaElectronica.infraestructure.entity.MetodoPagoEntity;
import com.example.tiendaElectronica.infraestructure.mapper.MetodoPagoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MetodoPagoJpaRepositoryAdapterTest {
    @Mock
    MetodoPagoJpaRepository metodoPagoJpaRepository;

    @Mock
    MetodoPagoMapper metodoPagoMapper;

    @InjectMocks
    MetodoPagoJpaRepositoryAdapter metodoPagoJpaRepositoryAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void save() {
        MetodoPago metodoPago=new MetodoPago(2L,new Date(),578,"545475754574","debito");
        MetodoPagoEntity metodoPagoEntity=new MetodoPagoEntity(2L,new Date(),578,"545475754574","debito");

        when(metodoPagoMapper.toMetodoPago(metodoPagoEntity)).thenReturn(metodoPago);
        when(metodoPagoMapper.toMetodoPagoEntity(metodoPago)).thenReturn(metodoPagoEntity);

        when(metodoPagoJpaRepository.save(metodoPagoEntity)).thenReturn(metodoPagoEntity);

        MetodoPago saveMetodoPago=metodoPagoJpaRepositoryAdapter.save(metodoPago);

        assertNotNull(saveMetodoPago);
        assertEquals(metodoPago.getCuentaBancaria(),saveMetodoPago.getCuentaBancaria());
    }

    @Test
    void findByIdMetodoPago() {
        Long id=2L;
        MetodoPagoEntity metodoPagoEntity=new MetodoPagoEntity(id,new Date(),578,"545475754574","debito");

        when(metodoPagoJpaRepository.findById(id)).thenReturn(Optional.of(metodoPagoEntity));

        MetodoPago metodoPago=new MetodoPago(id,new Date(),578,"545475754574","debito");
        when(metodoPagoMapper.toMetodoPago(metodoPagoEntity)).thenReturn(metodoPago);


        Optional<MetodoPago> result = metodoPagoJpaRepositoryAdapter.findById(id);

        assertTrue(result.isPresent());
        assertEquals(metodoPago.getCuentaBancaria(), result.get().getCuentaBancaria());


    }

    @Test
    void findAll() {
        MetodoPago metodoPago1=new MetodoPago(2L,new Date(),578,"545475754574","debito");
        MetodoPago metodoPago2=new MetodoPago(3l,new Date(),457,"2085754641321","credito");
        List<MetodoPago>metodoPagos= Arrays.asList(metodoPago1,metodoPago2);
        MetodoPagoEntity metodoPagoEntity1 = new MetodoPagoEntity(2L,new Date(),578,"545475754574","debito");

        MetodoPagoEntity metodoPagoEntity2 = new MetodoPagoEntity(3l,new Date(),457,"2085754641321","credito");
        List<MetodoPagoEntity> metodoPagosEntities = Arrays.asList(metodoPagoEntity1, metodoPagoEntity2);
        when(metodoPagoJpaRepository.findAll()).thenReturn(metodoPagosEntities);
        when(metodoPagoMapper.toMetodoPago(metodoPagoEntity1)).thenReturn(metodoPago1);
        when(metodoPagoMapper.toMetodoPago(metodoPagoEntity2)).thenReturn(metodoPago2);


        List<MetodoPago> result = metodoPagoJpaRepositoryAdapter.findAll();
        assertEquals(metodoPagos.size(), result.size());
        assertEquals(metodoPagos.containsAll(result),result.containsAll(metodoPagos));

    }
}