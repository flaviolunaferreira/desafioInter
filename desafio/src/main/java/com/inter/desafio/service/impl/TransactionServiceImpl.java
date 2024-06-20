package com.inter.desafio.service.impl;

import com.inter.desafio.exception.NotFound;
import com.inter.desafio.model.dto.Transaction.RequestTransactionDTO;
import com.inter.desafio.model.dto.Transaction.ResponseTrasactionDTO;
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
import java.util.ArrayList;
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

        double valorConvertido;

        if (transaction.getMoeda().getCodigo().equals("USD")) {
            valorConvertido = transaction.getValor() * cotacao.getCotacaoCompra();
        } else {
            valorConvertido = transaction.getValor();
        }

        //caso não tenha saldo.
        if (usuario.getSaldoReal() < valorConvertido) { // se for em real o valor convertido continua sendo em real
            return "Saldo insuficiente, seu saldo altual é: R$" + usuario.getSaldoReal();
        }


        TransactionEntity transacao = new TransactionEntity();
        transacao.setUsuario(usuario);
        transacao.setDestinatario(destinatario);
        transacao.setValor(valorConvertido);
        transacao.setData(LocalDateTime.now());
        transacao.setSaldoAnterior(usuario.getSaldoReal());
        transacaoRepository.save(transacao);

        usuario.setSaldoReal(usuario.getSaldoReal() - valorConvertido);
        destinatario.setSaldoReal(destinatario.getSaldoReal() + valorConvertido);
        usuario.setSaldoDolar(usuario.getSaldoReal() / cotacao.getCotacaoCompra());
        destinatario.setSaldoDolar(destinatario.getSaldoReal() / cotacao.getCotacaoCompra());

        userRepository.save(usuario);
        userRepository.save(destinatario);

        return "Transferência realizada com sucesso";
    }

    public List<ResponseTrasactionDTO> listarTransacoesPorUsuario(Long usuarioId) {
        // Busca todas as transações onde o usuário é remetente ou destinatário, em ordem cronológica
        List<TransactionEntity> lista = transacaoRepository.findByUsuarioIdOrDestinatarioIdOrderByDataAsc(usuarioId, usuarioId);

        List<ResponseTrasactionDTO> result = new ArrayList<>();

        // Inicializa o saldo atual com o saldo inicial do usuário
        UserEntity usuario = userRepository.findById(usuarioId).orElseThrow(() -> new NotFound("Usuário não encontrado"));
        double saldoAtual = usuario.getSaldoReal();

        // Itera sobre cada transação encontrada
        for (TransactionEntity x : lista) {
            ResponseTrasactionDTO aux = new ResponseTrasactionDTO();
            aux.setTransactionId(x.getId());
            aux.setEmissor(x.getUsuario().getNome());
            aux.setReceptor(x.getDestinatario().getNome());
            aux.setDataTransaction(x.getData());

            // Define o saldo anterior na DTO antes de ajustar o saldo atual
            aux.setSaldoAnterior(saldoAtual);

            // Verifica se o usuário é o remetente (débito) ou o destinatário (crédito)
            if (x.getUsuario().getId().equals(usuarioId)) {
                aux.setDebito(x.getValor());
                aux.setCredito(0.0);
                saldoAtual -= x.getValor();  // Ajusta o saldo atual
            } else if (x.getDestinatario().getId().equals(usuarioId)) {
                aux.setDebito(0.0);
                aux.setCredito(x.getValor());
                saldoAtual += x.getValor();  // Ajusta o saldo atual
            }

            // Define o saldo atual na DTO
            aux.setSaldoAtual(saldoAtual);
            result.add(aux);  // Adiciona a transação ao resultado
        }

        return result;
    }


}
