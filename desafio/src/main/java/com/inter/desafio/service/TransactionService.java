package com.inter.desafio.service;

import com.inter.desafio.model.dto.Transaction.RequestTransactionDTO;
import com.inter.desafio.model.dto.Transaction.ResponseTrasactionDTO;
import com.inter.desafio.model.entity.TransactionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    List<ResponseTrasactionDTO> listarTransacoesPorUsuario(Long usuarioId);

    String transferir(RequestTransactionDTO transaction);
}
