package com.inter.desafio.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
