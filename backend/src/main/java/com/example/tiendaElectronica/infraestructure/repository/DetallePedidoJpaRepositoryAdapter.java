package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.DetallePedido;
import com.example.tiendaElectronica.domain.model.Producto;
import com.example.tiendaElectronica.domain.ports.out.DetallePedidoRepositoryPort;
import com.example.tiendaElectronica.infraestructure.entity.DetallePedidoEntity;
import com.example.tiendaElectronica.infraestructure.entity.PedidoEntity;
import com.example.tiendaElectronica.infraestructure.entity.ProductoEntity;
import com.example.tiendaElectronica.infraestructure.mapper.DetallePedidoMapper;
import com.example.tiendaElectronica.infraestructure.mapper.ProductoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DetallePedidoJpaRepositoryAdapter implements DetallePedidoRepositoryPort {
    private final DetallePedidoJpaRepository detallePedidoJpaRepository;
    private final DetallePedidoMapper detallePedidoMapper;
    private final ProductoJpaRepository productoJpaRepository;
    private final PedidoJpaRepository pedidoJpaRepository;


    public DetallePedidoJpaRepositoryAdapter(DetallePedidoJpaRepository detallePedidoJpaRepository, DetallePedidoMapper detallePedidoMapper,
                                             ProductoJpaRepository productoJpaRepository, PedidoJpaRepository pedidoJpaRepository) {
        this.detallePedidoJpaRepository = detallePedidoJpaRepository;
        this.detallePedidoMapper = detallePedidoMapper;
        this.productoJpaRepository=productoJpaRepository;
        this.pedidoJpaRepository=pedidoJpaRepository;

    }

    @Override
    public DetallePedido save(DetallePedido detallePedido) {

            DetallePedidoEntity detallePedidoEntity=detallePedidoMapper.toDetallePedidoEntity(detallePedido);
            Long productoId= detallePedidoEntity.getProductoEntity().getId();
            // Recuperar ProductoEntity
            ProductoEntity productoEntity = productoJpaRepository.findById(productoId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            detallePedidoEntity.setProductoEntity(productoEntity);

            Long pedidoId=detallePedido.getPedido().getId();
            PedidoEntity pedidoEntity=pedidoJpaRepository.findById(pedidoId).
                    orElseThrow(()->new RuntimeException("Pedido no encontrado"));
            detallePedidoEntity.setPedidoEntity(pedidoEntity);

            DetallePedidoEntity saveDetallePedidoEntity=detallePedidoJpaRepository.save(detallePedidoEntity);
             return detallePedidoMapper.toDetallePedido(saveDetallePedidoEntity);
    }

    @Override
    public Optional<DetallePedido> findById(Long id) {
        return detallePedidoJpaRepository.findById(id).map(detallePedidoMapper::toDetallePedido);
    }

    @Override
    public List<DetallePedido> findAll() {
        return detallePedidoJpaRepository.findAll().stream().
                map(detallePedidoMapper::toDetallePedido).collect(Collectors.toList());
    }
}
