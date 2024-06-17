package com.inter.desafio.model.dto;

import com.inter.desafio.model.entity.UserEntity;
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
    private String createdBY;
    private LocalDateTime createdDate;

    public ResponseUserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.nome = userEntity.getNome();
        this.email = userEntity.getEmail();
        this.cpfCnpj = userEntity.getCpfCnpj();
        this.createdBY = userEntity.getCreatedBy();
        this.createdDate = userEntity.getCreatedDate();
    }
}
