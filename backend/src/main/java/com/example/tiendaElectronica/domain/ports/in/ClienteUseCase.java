package com.example.tiendaElectronica.domain.ports.in;

import com.example.tiendaElectronica.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteUseCase {
    Cliente crearCliente(Cliente cliente);
    Optional<Cliente> getCliente(Long id);
    List<Cliente> getAllClientes();
    Optional<Cliente> updateCliente(Long id, Cliente cliente);
    boolean deleteCliente(Long id);

}