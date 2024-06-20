package com.inter.desafio.model.dto.Transaction;

import com.inter.desafio.model.entity.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTrasactionDTO {

    private Long transactionId;
    private String emissor;
    private String receptor;
    private LocalDateTime dataTransaction;
    private Double saldoAnterior;
    private Double credito;
    private Double debito;
    private double saldoAtual;

}
