package com.example.tiendaElectronica.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name="metodo_pago")
public class MetodoPagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaVencimiento;
    private Integer cvv;
    private String cuentaBancaria;
    private String tipo;


    public MetodoPagoEntity() {
    }

    public MetodoPagoEntity(Long id, Date fechaVencimiento, Integer cvv, String cuentaBancaria, String tipo) {
        this.id = id;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        this.cuentaBancaria = cuentaBancaria;
        this.tipo = tipo;
    }

}
