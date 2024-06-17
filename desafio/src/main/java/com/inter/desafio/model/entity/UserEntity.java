package com.inter.desafio.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BasicEntity {

    private String nome;
    private String email;
    private String senha;
    private String cpfCnpj;
    
}