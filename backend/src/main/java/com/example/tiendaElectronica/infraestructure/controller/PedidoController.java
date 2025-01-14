package com.example.tiendaElectronica.infraestructure.controller;

import com.example.tiendaElectronica.application.service.PedidoService;
import com.example.tiendaElectronica.domain.model.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido){
        Pedido createPedido=pedidoService.crearPedido(pedido);
        return new ResponseEntity<>(createPedido, HttpStatus.CREATED);
    }
    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido>getPedidoById(@PathVariable Long pedidoId){
        return pedidoService.getPedido(pedidoId)
                .map(pedido -> new ResponseEntity<>(pedido,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public List<Pedido> getAllPedidos(){
        return pedidoService.getAllPedido();
    }
    @PutMapping("/{pedidoId}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long pedidoId,@RequestBody Pedido pedido){
        return pedidoService.updatePedido(pedidoId,pedido).map(ped ->new ResponseEntity<>(ped,HttpStatus.OK) )
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void>deletePedidoById(@PathVariable Long pedidoId){
        if(pedidoService.deletePedido(pedidoId)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}
