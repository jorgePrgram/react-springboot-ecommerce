package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.DetallePedido;
import com.example.tiendaElectronica.domain.model.Pedido;
import com.example.tiendaElectronica.domain.model.Producto;
import com.example.tiendaElectronica.domain.ports.out.PedidoRepositoryPort;
import com.example.tiendaElectronica.infraestructure.entity.*;
import com.example.tiendaElectronica.infraestructure.mapper.PedidoMapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component

public class PedidoJpaRepositoryAdapter implements PedidoRepositoryPort {
    private final PedidoJpaRepository pedidoJpaRepository;
    private final PedidoMapper pedidoMapper;
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final MetodoPagoJpaRepository metodoPagoJpaRepository;
    private final DetallePedidoJpaRepository detallePedidoJpaRepository;
    private final ProductoJpaRepository productoJpaRepository;


    public PedidoJpaRepositoryAdapter(PedidoJpaRepository pedidoJpaRepository, PedidoMapper pedidoMapper,
                                      UsuarioJpaRepository usuarioJpaRepository,
                                      MetodoPagoJpaRepository metodoPagoJpaRepository,
                                      DetallePedidoJpaRepository detallePedidoJpaRepository,
                                      ProductoJpaRepository productoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
        this.pedidoMapper = pedidoMapper;
        this.usuarioJpaRepository=usuarioJpaRepository;
        this.metodoPagoJpaRepository=metodoPagoJpaRepository;
        this.detallePedidoJpaRepository=detallePedidoJpaRepository;
        this.productoJpaRepository=productoJpaRepository;


    }

    @Override
    public Pedido save(Pedido pedido) {
        if (pedido.getUsuario() == null) {
            throw new RuntimeException("Usuario no puede ser nulo");
        }
        Long usuarioId=pedido.getUsuario().getId();
        UsuarioEntity usuarioEntity=usuarioJpaRepository.findById(usuarioId).
                orElseThrow(()->new RuntimeException("Usuario no encontrado"));

        Long metodoPagoId=pedido.getMetodoPago().getId();
        MetodoPagoEntity metodoPagoEntity=metodoPagoJpaRepository.findById(metodoPagoId)
                .orElseThrow(()->new RuntimeException("Metodo de pago no encontrado"));
        PedidoEntity pedidoEntity=pedidoMapper.toPedidoEntity(pedido);
        pedidoEntity.setUsuarioEntity(usuarioEntity);
        pedidoEntity.setMetodoPagoEntity(metodoPagoEntity);
        if(pedido.getDetallePedidos() !=null){
            for (DetallePedido detalle : pedido.getDetallePedidos()) {
                DetallePedidoEntity detalleEntity = new DetallePedidoEntity();

                ProductoEntity productoEntity=productoJpaRepository.findById(detalle.getId()).
                        orElseThrow(()->new RuntimeException("Producto no encontrado"));
                detalleEntity.setProductoEntity(productoEntity);
                detalleEntity.setCantidad(detalle.getCantidad());
                detalleEntity.setPedidoEntity(pedidoEntity);
                detalleEntity.setProductoEntity(productoEntity);

                pedidoEntity.getDetallePedidoEntities().add(detalleEntity);
            }
        }



        PedidoEntity savePedidoEntity=pedidoJpaRepository.save(pedidoEntity);

        return pedidoMapper.toPedido(savePedidoEntity);
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        Optional<PedidoEntity> optionalPedidoEntity = pedidoJpaRepository.findById(id);
        return optionalPedidoEntity.map(pedidoEntity -> {
            return pedidoMapper.toPedido(pedidoEntity);
        });
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoJpaRepository.findAll().stream().map(pedidoMapper::toPedido).
                collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> update(Long id, Pedido pedido) {
        if(id!=null && pedidoJpaRepository.existsById(id)){
         PedidoEntity pedidoEntity=pedidoMapper.toPedidoEntity(pedido);
         pedidoEntity.setId(id);

         PedidoEntity updatePedidoEntity=pedidoJpaRepository.save(pedidoEntity);
         Pedido updatePedido=pedidoMapper.toPedido(updatePedidoEntity);
         return Optional.of(updatePedido);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if(pedidoJpaRepository.existsById(id)){
            pedidoJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }




}