package com.inter.desafio.model.repository;

import com.inter.desafio.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    List<UserEntity> findByNomeIgnoreCase(String nome);

}
