package com.inter.desafio.model.dto.user;

import com.inter.desafio.model.entity.UserEntity;
import com.inter.desafio.uteis.RetornaCpfOuCnpj;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpfCnpj;
    private String tipoPessoa;
    private double saldo;
    private LocalDateTime createdDate;

    public ResponseUserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.nome = userEntity.getNome();
        this.email = userEntity.getEmail();
        this.cpfCnpj = userEntity.getCpfCnpj();
        this.tipoPessoa = RetornaCpfOuCnpj.validarCpfOuCnpj(userEntity.getCpfCnpj());
        this.saldo = userEntity.getSaldoReal();
        this.createdDate = userEntity.getCreatedDate();
    }
}
