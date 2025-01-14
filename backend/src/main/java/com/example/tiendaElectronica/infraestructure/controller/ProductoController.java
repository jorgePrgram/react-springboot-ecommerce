package com.example.tiendaElectronica.infraestructure.controller;

import com.example.tiendaElectronica.application.service.ProductoService;
import com.example.tiendaElectronica.domain.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;


    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto){
        try {
            Producto createProducto =productoService.crearProducto(producto);
            return new ResponseEntity<>(createProducto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }


    }
    @GetMapping("/{productoId}")
    public ResponseEntity<Producto> findByIdProducto(@PathVariable Long productoId){
        return productoService.getProducto(productoId).
                map(pro->new ResponseEntity<>(pro,HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public List<Producto> getAllProducto(){
        return productoService.getAllProductos();
    }
    @PutMapping("/{productoId}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long productoId, @RequestBody Producto producto){
        return productoService.actualizasProducto(productoId,producto).
                map(pro->new ResponseEntity<>(pro,HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @DeleteMapping("/{productoId}")
    public ResponseEntity<Void> deleteProductoById(@PathVariable Long productoId){
        if(productoService.deleteProducto(productoId)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
        public ResponseEntity<List<Producto>> findByNombre(@RequestParam String nombre){
            List<Producto> productos=productoService.buscarPorNombre(nombre);
            if(productos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productos,HttpStatus.OK);
        }
    }



