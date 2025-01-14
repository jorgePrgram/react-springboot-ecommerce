package com.example.tiendaElectronica.infraestructure.controller;

import com.example.tiendaElectronica.application.service.UsuarioService;
import com.example.tiendaElectronica.domain.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        Usuario createUsuario = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(createUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long usuarioId){
        return usuarioService.getUsuario(usuarioId)
                .map(usr -> new ResponseEntity<>(usr, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long usuarioId, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(usuarioId, usuario)
                .map(usr -> new ResponseEntity<>(usr, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long usuarioId){
        if(usuarioService.deleteUsuario(usuarioId)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody(required = true)Map<String,String> requestMap){
        return usuarioService.login(requestMap);
    }



}