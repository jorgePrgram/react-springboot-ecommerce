package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.MetodoPago;
import com.example.tiendaElectronica.domain.ports.out.MetodoPagoRepositoryPort;
import com.example.tiendaElectronica.infraestructure.entity.MetodoPagoEntity;
import com.example.tiendaElectronica.infraestructure.mapper.MetodoPagoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MetodoPagoJpaRepositoryAdapter implements MetodoPagoRepositoryPort {
    private final MetodoPagoJpaRepository metodoPagoJpaRepository;
    private final MetodoPagoMapper metodoPagoMapper;

    public MetodoPagoJpaRepositoryAdapter(MetodoPagoJpaRepository metodoPagoJpaRepository, MetodoPagoMapper metodoPagoMapper) {
        this.metodoPagoJpaRepository = metodoPagoJpaRepository;
        this.metodoPagoMapper = metodoPagoMapper;
    }

    @Override
    public MetodoPago save(MetodoPago metodoPago) {
        MetodoPagoEntity metodoPagoEntity=metodoPagoMapper.toMetodoPagoEntity(metodoPago);
        MetodoPagoEntity saveMetodoPagoEntity=metodoPagoJpaRepository.save(metodoPagoEntity);
        return metodoPagoMapper.toMetodoPago(saveMetodoPagoEntity);
    }

    @Override
    public Optional<MetodoPago> findById(Long id) {
        return metodoPagoJpaRepository.findById(id).map(metodoPagoMapper::toMetodoPago);
    }
    @Override
    public List<MetodoPago> findAll() {
        return metodoPagoJpaRepository.findAll().stream()
                .map(metodoPagoMapper::toMetodoPago)
                .collect(Collectors.toList());
    }
}
