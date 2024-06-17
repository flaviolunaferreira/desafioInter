package com.inter.desafio.model.repository;

import com.inter.desafio.model.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletEntity, Long>{

}
