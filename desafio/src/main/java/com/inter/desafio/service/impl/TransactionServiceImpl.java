package com.inter.desafio.service.impl;

import com.inter.desafio.exception.NotFound;
import com.inter.desafio.model.TipoDeMoeda;
import com.inter.desafio.model.dto.Transaction.RequestTransactionDTO;
import com.inter.desafio.model.entity.CotacaoEntity;
import com.inter.desafio.model.entity.TransactionEntity;
import com.inter.desafio.model.entity.UserEntity;
import com.inter.desafio.model.repository.TransactionRepository;
import com.inter.desafio.model.repository.UserRepository;
import com.inter.desafio.service.CotacaoService;
import com.inter.desafio.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transacaoRepository;

    @Autowired
    private CotacaoService cotacaoService;

    @Transactional
    public String transferir(RequestTransactionDTO transaction) {

        // Obter a cotação do dia atual
        CotacaoEntity cotacao = cotacaoService.getCotacao(LocalDate.now());
        UserEntity usuario = userRepository.findById(transaction.getUsuario()).orElseThrow(() -> new NotFound("Usuário não encontrado"));
        UserEntity destinatario = userRepository.findById(transaction.getDestinatario()).orElseThrow(() -> new NotFound("Destinatário não encontrado"));

        double valorConvertido = 0;

        if (transaction.getMoeda().getCodigo().equals("USD")) {
            valorConvertido = transaction.getValor() * cotacao.getCotacaoCompra();
        } else {
            valorConvertido = transaction.getValor();
        }
        if (usuario.getSaldoReal() < valorConvertido) {
            return "Saldo insuficiente";
        }

        usuario.setSaldoReal(usuario.getSaldoReal() - valorConvertido);
        destinatario.setSaldoReal(destinatario.getSaldoReal() + valorConvertido);
        usuario.setSaldoDolar(usuario.getSaldoReal() / cotacao.getCotacaoCompra());
        destinatario.setSaldoDolar(destinatario.getSaldoReal() / cotacao.getCotacaoCompra());

        userRepository.save(usuario);
        userRepository.save(destinatario);

        TransactionEntity transacao = new TransactionEntity();
        transacao.setUsuario(usuario);
        transacao.setDestinatario(destinatario);
        transacao.setValor(valorConvertido);
        transacao.setData(LocalDateTime.now());

        transacaoRepository.save(transacao);

        return "Transferência realizada com sucesso";
    }

    public List<TransactionEntity> listarTransacoesPorUsuario(Long usuarioId) {
        return transacaoRepository.findByUsuarioIdOrDestinatarioId(usuarioId, usuarioId);
    }

}
