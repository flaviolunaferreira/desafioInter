package com.inter.desafio.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inter.desafio.uteis.CapitalizeNames;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Description;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BasicEntity {

    @Schema(description = "Nome do Titular da conta.")
    private String nome;

    @Email(message = "Preciso que você informe um email válido.")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    private String cpfCnpj;

    public UserEntity( String nome, String email, String senha, String cpfCnpj) {
        this.nome = CapitalizeNames.capitalizeName(nome);
        this.email = email;
        this.senha = senha;
        this.cpfCnpj = cpfCnpj;
    }
    
}