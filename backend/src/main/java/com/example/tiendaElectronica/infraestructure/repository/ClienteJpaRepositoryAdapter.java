package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.Cliente;
import com.example.tiendaElectronica.domain.ports.out.ClienteRepositoryPort;
import com.example.tiendaElectronica.infraestructure.entity.ClienteEntity;
import com.example.tiendaElectronica.infraestructure.mapper.ClienteMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClienteJpaRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository clienteJpaRepository;

    private final ClienteMapper clienteMapper;

    public ClienteJpaRepositoryAdapter(ClienteJpaRepository clienteJpaRepository, ClienteMapper clienteMapper) {
        this.clienteJpaRepository = clienteJpaRepository;
        this.clienteMapper=clienteMapper;
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity clienteEntity=clienteMapper.toClienteEntity(cliente);
        clienteEntity =clienteJpaRepository.save(clienteEntity);
        return clienteMapper.toCliente(clienteEntity);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteJpaRepository.findById(id).map(clienteMapper::toCliente);
    }

    @Override
    public Optional<Cliente> update(Long id, Cliente cliente) {
        if(id!=null && clienteJpaRepository.existsById(id)) {
            ClienteEntity clienteEntity = clienteMapper.toClienteEntity(cliente);
            clienteEntity.setId(id);
            ClienteEntity updateClienteEntity=clienteJpaRepository.save(clienteEntity);
            return Optional.of(clienteMapper.toCliente(updateClienteEntity));
        }
        return Optional.empty();
    }

    @Override
    public List<Cliente> findAll() {
        return clienteJpaRepository.findAll().stream()
                .map(clienteMapper::toCliente)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        if(clienteJpaRepository.existsById(id)){
            clienteJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
