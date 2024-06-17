package com.inter.desafio.model.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

public class ResponseUserDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cpfCnpj;
    private String createdBY;
    private String createdDate;

}
