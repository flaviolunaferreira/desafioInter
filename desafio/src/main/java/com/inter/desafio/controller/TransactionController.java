package com.inter.desafio.controller;

import com.inter.desafio.model.dto.Transaction.RequestTransactionDTO;
import com.inter.desafio.model.entity.CotacaoEntity;
import com.inter.desafio.model.entity.TransactionEntity;
import com.inter.desafio.service.CotacaoService;
import com.inter.desafio.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CotacaoService cotacaoService;

    @PostMapping("/transferir")
    public String transferir(@RequestBody RequestTransactionDTO transaction) {
            return transactionService.transferir(transaction);
    }

    @GetMapping("/listar/{usuarioId}")
    public List<TransactionEntity> listarTransacoesPorUsuario(@PathVariable Long usuarioId) {
        return transactionService.listarTransacoesPorUsuario(usuarioId);
    }
}
