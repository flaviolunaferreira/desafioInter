package com.inter.desafio.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CotacaoEntity extends BasicEntity {

    private LocalDate dataCotacao;
    private Double cotacaoCompra;
    private Double cotacaoVenda;

}
