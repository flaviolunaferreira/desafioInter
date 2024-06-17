package com.inter.desafio.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity extends BasicEntity {

    private String nome;
    private String email;
    private String senha;
    private String cpfCnpj;
    
}