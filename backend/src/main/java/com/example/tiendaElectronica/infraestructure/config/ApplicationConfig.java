package com.example.tiendaElectronica.infraestructure.config;

import com.example.tiendaElectronica.application.service.*;
import com.example.tiendaElectronica.application.usecase.*;
import com.example.tiendaElectronica.domain.ports.out.*;
import com.example.tiendaElectronica.infraestructure.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ClienteService clienteService(ClienteRepositoryPort clienteRepositoryPort){
        return new ClienteService(new ClienteUseCaseImpl(clienteRepositoryPort));
    }
    @Bean
    public ClienteRepositoryPort clienteRepositoryPort(ClienteJpaRepositoryAdapter clienteJpaRepositoryAdapter){
        return clienteJpaRepositoryAdapter;
    }
    @Bean
    public UsuarioService usuarioService(UsuarioRepositoryPort usuarioRepositoryPort){
        return new UsuarioService(new UsuarioUseCaseImpl(usuarioRepositoryPort));
    }


    @Bean
    public UsuarioRepositoryPort usuarioRepositoryPort(UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter){
        return usuarioJpaRepositoryAdapter;
    }
    @Bean
    public CategoriaService categoriaService(CategoriaRepositoryPort categoriaRepositoryPort){
        return new CategoriaService(new CategoriaUseCaseImpl(categoriaRepositoryPort));
    }
    @Bean
    public CategoriaRepositoryPort categoriaRepositoryPort(CategoriaJpaRepositoryAdapter categoriaJpaRepositoryAdapter){
        return categoriaJpaRepositoryAdapter;
    }
    @Bean
    public MetodoPagoService metodoPagoService(MetodoPagoRepositoryPort metodoPagoRepositoryPort){
        return new MetodoPagoService(new MetodoPagoUseCaseImpl(metodoPagoRepositoryPort));
    }
    @Bean
    public MetodoPagoRepositoryPort metodoPagoRepositoryPort(MetodoPagoJpaRepositoryAdapter metodoPagoJpaRepositoryAdapter){
        return metodoPagoJpaRepositoryAdapter;
    }
    @Bean
    public PedidoService pedidoService(PedidoRepositoryPort pedidoRepositoryPort){
        return new PedidoService((new PedidoUseCaseImpl(pedidoRepositoryPort)));
    }
    @Bean
    public PedidoRepositoryPort pedidoRepositoryPort(PedidoJpaRepositoryAdapter pedidoJpaRepositoryAdapter){
        return pedidoJpaRepositoryAdapter;
    }
    @Bean
    public ProductoService productoService(ProductoRepositoryPort productoRepositoryPort) {
        return new ProductoService(new ProductoUseCaseImpl(productoRepositoryPort));
    }

    @Bean
    public ProductoRepositoryPort productoRepositoryPort(ProductoJpaRepositoryAdapter productoJpaRepositoryAdapter) {
        return productoJpaRepositoryAdapter;
    }
    @Bean
    DetallePedidoService detallePedidoService(DetallePedidoRepositoryPort detallePedidoRepositoryPort){
        return new DetallePedidoService(new DetallePedidoUseCaseImpl(detallePedidoRepositoryPort));
    }
    @Bean
    public DetallePedidoRepositoryPort detallePedidoRepositoryPort(DetallePedidoJpaRepositoryAdapter detallePedidoJpaRepositoryAdapter){
        return detallePedidoJpaRepositoryAdapter;
    }


}
