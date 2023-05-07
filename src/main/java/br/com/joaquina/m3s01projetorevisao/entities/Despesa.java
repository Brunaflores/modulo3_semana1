package br.com.joaquina.m3s01projetorevisao.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
    @Table(name = "despesas")

public class Despesa {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String credor;

        @Column(name = "data_vencimento", nullable = false)
        private LocalDate dataVencimento;

        @Column(name = "data_pagamento")
        private LocalDate dataPagamento;

        @Column(nullable = false)
        private BigDecimal valor;

        private String descricao;

        @Column(nullable = false)
        private String status;

    }



