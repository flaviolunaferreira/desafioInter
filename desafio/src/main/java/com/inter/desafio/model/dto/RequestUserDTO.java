package com.inter.desafio.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUserDTO {


    private String nome;

    private String email;

    private String senha;

    private String cpfCnpj;

}
