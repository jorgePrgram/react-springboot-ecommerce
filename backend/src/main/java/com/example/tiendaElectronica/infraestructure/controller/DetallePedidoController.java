package com.example.tiendaElectronica.infraestructure.controller;

import com.example.tiendaElectronica.application.service.DetallePedidoService;
import com.example.tiendaElectronica.domain.model.DetallePedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detallepedido")
public class DetallePedidoController {
    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @PostMapping
    public ResponseEntity<DetallePedido>createDetallePedido(@RequestBody DetallePedido detallePedido){
        DetallePedido createDetallePedido=detallePedidoService.crearDetallePedido(detallePedido);
        return new ResponseEntity<>(createDetallePedido, HttpStatus.CREATED);
    }
    @GetMapping("/{detallePedidoId}")
    public ResponseEntity<DetallePedido>getDetallePedidoById(@PathVariable Long detallePedidoId){
        return detallePedidoService.getDetallePedido(detallePedidoId).
                map(detallePedido -> new ResponseEntity<>(detallePedido,HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public List<DetallePedido>getDetallePedidoAll(){
        return detallePedidoService.getAllDetallePedidos();
    }





}
