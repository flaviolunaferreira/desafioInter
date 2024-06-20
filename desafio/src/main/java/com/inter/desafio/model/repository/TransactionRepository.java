package com.inter.desafio.model.repository;

import com.inter.desafio.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByUsuarioId(Long usuarioId);
    List<TransactionEntity> findByUsuarioIdOrDestinatarioIdOrderByDataAsc(Long usuarioId, Long destinatarioId);
    List<TransactionEntity> findByUsuarioIdAndData(Long usuarioId, LocalDateTime data);
}
