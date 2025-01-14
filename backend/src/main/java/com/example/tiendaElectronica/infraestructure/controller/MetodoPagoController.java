package com.example.tiendaElectronica.infraestructure.controller;

import com.example.tiendaElectronica.application.service.MetodoPagoService;
import com.example.tiendaElectronica.domain.model.MetodoPago;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodopago")
public class MetodoPagoController {
    private final MetodoPagoService metodoPagoService;

    public MetodoPagoController(MetodoPagoService metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }
    @PostMapping
    public ResponseEntity<MetodoPago> createMetodoPago(@RequestBody MetodoPago metodoPago){
        // Realizar validación del método de pago aquí
        // Por ejemplo, validar con una API externa o aplicar reglas de negocio

        // Eliminar el CVV antes de guardar el método de pago
        metodoPago.setCvv(null);
        MetodoPago createMetodoPago=metodoPagoService.crearMetodoPago(metodoPago);
        return new ResponseEntity<>(createMetodoPago, HttpStatus.CREATED);
    }
    @GetMapping
    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoService.getAllMetodosPago();
    }
    @GetMapping("/{metodoPagoId}")
    public ResponseEntity<MetodoPago> getMetodoPagoById(@PathVariable Long metodoPagoId) {
        return metodoPagoService.getMetodoPago(metodoPagoId)
                .map(mp -> new ResponseEntity<>(mp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
