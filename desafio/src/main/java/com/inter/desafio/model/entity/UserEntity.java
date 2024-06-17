package com.inter.desafio.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inter.desafio.model.dto.RequestUserDTO;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BasicEntity {

    @NotNull
    @NotBlank
    private String nome;

    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    private String cpfCnpj;

    public UserEntity( String nome, String email, String senha, String cpfCnpj) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpfCnpj = cpfCnpj;
    }
    
}