package com.example.tiendaElectronica.infraestructure.controller;

import com.example.tiendaElectronica.domain.model.Cliente;
import com.example.tiendaElectronica.application.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
        Cliente createCliente=clienteService.crearCliente(cliente);
        return new ResponseEntity<>(createCliente, HttpStatus.CREATED);
    }
    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente>getClienteById(@PathVariable Long clienteId){
        return clienteService.getCliente(clienteId)
                .map(cli->new ResponseEntity<>(cli,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public List<Cliente> getAllClientes(){
        return clienteService.getAllClientes();
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente>updateCliente(@PathVariable Long clienteId, @RequestBody Cliente cliente){
        return clienteService.updateCliente(clienteId,cliente)
                .map(cli->new ResponseEntity<>(cli,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long clienteId){
        if(clienteService.deleteCliente(clienteId)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
