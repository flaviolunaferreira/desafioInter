package com.inter.desafio.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wallet")
@NoArgsConstructor
@AllArgsConstructor
public class WalletEntity extends BasicEntity {

    private Long userId;
    private BigDecimal saldoReal;
    private BigDecimal SaldoDolar;

}
