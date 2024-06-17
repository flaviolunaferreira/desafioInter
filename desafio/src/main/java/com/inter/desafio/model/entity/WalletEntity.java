package com.inter.desafio.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WalletEntity extends BasicEntity {

    private Long userId;
    private BigDecimal saldoReal;
    private BigDecimal SaldoDolar;

}
