package com.inter.desafio.model.dto;

import com.inter.desafio.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDTO {


    private String nome;

    private String email;

    private String senha;

    private String cpfCnpj;

    public UserEntity newUser() {
        return new UserEntity(nome, email, senha, cpfCnpj);
    }
}
