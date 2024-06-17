package com.inter.desafio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inter.desafio.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
