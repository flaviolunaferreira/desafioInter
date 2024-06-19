package com.inter.desafio.model.dto.Transaction;

import com.inter.desafio.model.TipoDeMoeda;
import com.inter.desafio.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransactionDTO {

    private Long usuario;
    private Long destinatario;
    private TipoDeMoeda moeda;
    private Double valor;

}
