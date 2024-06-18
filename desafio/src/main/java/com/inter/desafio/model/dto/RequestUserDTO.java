package com.inter.desafio.model.dto;

import com.inter.desafio.model.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RequestUser", description = "Este é o modelo de entrada das informações necessárias para o cadastro de um novo usuário")
public class RequestUserDTO {

    @Schema(description = "Informe o nome do usuário com até 255 caracteres.")
    private String nome;

    private String email;

    private String senha;

    private String cpfCnpj;

    public UserEntity newUser() {
        return new UserEntity(nome, email, senha, cpfCnpj);
    }
}
