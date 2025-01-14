package com.example.tiendaElectronica.application.service;

import com.example.tiendaElectronica.domain.model.Cliente;
import com.example.tiendaElectronica.domain.ports.in.ClienteUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ClienteUseCase {

    private final ClienteUseCase clienteUseCase;

    public ClienteService(ClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteUseCase.crearCliente(cliente);
    }


    @Override
    public Optional<Cliente> getCliente(Long id) {
        return clienteUseCase.getCliente(id);
    }


    @Override
    public List<Cliente> getAllClientes() {
        return clienteUseCase.getAllClientes();
    }

    @Override
    public Optional<Cliente> updateCliente(Long id, Cliente cliente) {
        return clienteUseCase.updateCliente(id, cliente);
    }

    @Override
    public boolean deleteCliente(Long id) {
        return clienteUseCase.deleteCliente(id);
    }
}
