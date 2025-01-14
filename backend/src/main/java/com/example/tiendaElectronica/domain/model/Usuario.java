package com.example.tiendaElectronica.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Usuario {
    private Long id;
    private String nombreUsuario;
    private String contrasenia;
    private String correoElectronico;
    private String role;

    private Cliente cliente;
    private List<Pedido>pedidos;

    public Usuario() {
    }
    public Usuario(Long id, String nombreUsuario, String contrasenia, String correoElectronico,String role, Cliente cliente) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.role=role;
        this.cliente = cliente;
    }

    public Usuario(Long id, String nombreUsuario, String contrasenia, String correoElectronico, String role,Cliente cliente, List<Pedido> pedidos) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.cliente = cliente;
        this.role=role;
        this.pedidos = pedidos;
    }


}
