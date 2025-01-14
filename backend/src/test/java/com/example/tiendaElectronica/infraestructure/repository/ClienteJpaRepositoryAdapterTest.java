package com.example.tiendaElectronica.infraestructure.repository;


import com.example.tiendaElectronica.domain.model.Cliente;
import com.example.tiendaElectronica.infraestructure.entity.ClienteEntity;
import com.example.tiendaElectronica.infraestructure.mapper.ClienteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClienteJpaRepositoryAdapterTest {
    @Mock
    ClienteJpaRepository clienteJpaRepository;

    @Mock
    ClienteMapper clienteMapper;

    @InjectMocks
    ClienteJpaRepositoryAdapter clienteJpaRepositoryAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }





    @Test
    void saveExitosoClienteEntity() {

        Cliente cliente=new Cliente(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        ClienteEntity clienteEntity=new ClienteEntity(2L,"Juan","Sanchez","masculino",new Date(),"977785431");

        //Comportamiento de ClienteMapper
         when(clienteMapper.toClienteEntity(cliente)).thenReturn(clienteEntity);
         when(clienteMapper.toCliente(clienteEntity)).thenReturn(cliente);
        //Simulando
        when(clienteJpaRepository.save(Mockito.any(ClienteEntity.class))).thenReturn(clienteEntity);

        Cliente cliente1=clienteJpaRepositoryAdapter.save(cliente);

        assertNotNull(cliente1);
        assertEquals(cliente.getId(),cliente1.getId());
    }

    @Test
    void findByIdExitosoClienteEntity() {
    Long id=3L;

        ClienteEntity clienteEntity=new ClienteEntity(id,"Juan","Sanchez","masculino",new Date(),"977785431");

        when(clienteJpaRepository.findById(id)).thenReturn(Optional.of(clienteEntity));

        Cliente cliente=new Cliente(id,"Juan","Sanchez","masculino",new Date(),"977785431");
        when(clienteMapper.toCliente(clienteEntity)).thenReturn(cliente);


        Optional<Cliente> optionalCliente=clienteJpaRepositoryAdapter.findById(id);

        assertTrue(optionalCliente.isPresent());
        Cliente clienteAdapter=optionalCliente.get();
        assertEquals(clienteEntity.getId(),clienteAdapter.getId());
        assertEquals(clienteEntity.getNombre(),clienteAdapter.getNombre());
    }
    @Test
    void findById_IsEmpty(){
        Long id=3L;
        when(clienteJpaRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Cliente>optionalCliente=clienteJpaRepositoryAdapter.findById(id);
        assertTrue(optionalCliente.isEmpty());
    }

    @Test
    void updateExitosoClienteEntity() {
        Long id=2L;
        Cliente clienteActualizado=new Cliente(id,"Juan","Sanchez","masculino",new Date(),"977785431");
        when(clienteJpaRepository.existsById(id)).thenReturn(true);

        ClienteEntity clienteEntityActualizado = new ClienteEntity(id, "Juan", "Sanchez", "masculino", new Date(), "977785431");


        when(clienteMapper.toClienteEntity(clienteActualizado)).thenReturn(clienteEntityActualizado);
        when(clienteMapper.toCliente(clienteEntityActualizado)).thenReturn(clienteActualizado);
        when(clienteJpaRepository.save(clienteEntityActualizado)).thenReturn(clienteEntityActualizado);


        Optional<Cliente> resultado=clienteJpaRepositoryAdapter.update(id,clienteActualizado);


        assertTrue(resultado.isPresent());
        Cliente clienteResultado=resultado.get();

        assertEquals(id,clienteResultado.getId());
        assertEquals(clienteActualizado.getNombre(),clienteResultado.getNombre());

    }

    @Test
    void update_isEmpty(){
        Long id=2L;
        Cliente cliente=new Cliente(id,"Juan","Sanchez","masculino",new Date(),"977785431");

        when(clienteJpaRepository.existsById(id)).thenReturn(false);
        Optional<Cliente>optionalCliente=clienteJpaRepositoryAdapter.update(id,cliente);

        assertTrue(optionalCliente.isEmpty());
    }

    @Test
    void findAll() {
        Cliente cliente1=new Cliente(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        Cliente cliente2=new Cliente(3L,"Tatiana","Palacios","femenino",new Date(),"988231454");
        List<Cliente>clientes=Arrays.asList(cliente1,cliente2);

        ClienteEntity clienteEntity1=new ClienteEntity(2L,"Juan","Sanchez","masculino",new Date(),"977785431");
        ClienteEntity clienteEntity2=new ClienteEntity(3L,"Tatiana","Palacios","femenino",new Date(),"988231454");
        List<ClienteEntity>clienteEntities= Arrays.asList(clienteEntity1,clienteEntity2);

        when(clienteMapper.toCliente(clienteEntity1)).thenReturn(cliente1);
        when(clienteMapper.toCliente(clienteEntity2)).thenReturn(cliente2);
        when(clienteJpaRepository.findAll()).thenReturn(clienteEntities);

        List<Cliente> resultado=clienteJpaRepositoryAdapter.findAll();

        assertNotNull(resultado);
        assertEquals(clientes.size(),resultado.size());
        assertEquals(resultado.containsAll(clientes),clientes.containsAll(resultado));

    }

    @Test
    void deleteById_Exist() {
        Long id=3L;

        when(clienteJpaRepository.existsById(id)).thenReturn(true);

        boolean exitoso= clienteJpaRepositoryAdapter.deleteById(id);

        assertTrue(exitoso);
    }
    @Test
    void deleteById_IsEmpty(){
        Long id=2L;
        when(clienteJpaRepository.existsById(id)).thenReturn(false);

        boolean vacio= clienteJpaRepositoryAdapter.deleteById(id);

        assertFalse(vacio);
    }



}