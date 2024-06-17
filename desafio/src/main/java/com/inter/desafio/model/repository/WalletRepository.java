package com.inter.desafio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inter.desafio.model.entity.WalletEntity;

public interface WalletRepository extends JpaRepository<WalletEntity, Long>{

}
