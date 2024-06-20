package com.inter.desafio.service.impl;

import com.inter.desafio.exception.NotFound;
import com.inter.desafio.model.TipoDeMoeda;
import com.inter.desafio.model.dto.Transaction.RequestTransactionDTO;
import com.inter.desafio.model.dto.Transaction.ResponseTrasactionDTO;
import com.inter.desafio.model.entity.CotacaoEntity;
import com.inter.desafio.model.entity.TransactionEntity;
import com.inter.desafio.model.entity.UserEntity;
import com.inter.desafio.model.repository.TransactionRepository;
import com.inter.desafio.model.repository.UserRepository;
import com.inter.desafio.service.CotacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transacaoRepository;

    @Mock
    private CotacaoService cotacaoService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferirComSucesso() {
        RequestTransactionDTO transactionDTO = new RequestTransactionDTO();
        transactionDTO.setUsuario(1L);
        transactionDTO.setDestinatario(2L);
        transactionDTO.setValor(100.0);
        transactionDTO.setMoeda(TipoDeMoeda.DOLAR);

        UserEntity usuario = new UserEntity();
        usuario.setId(1L);
        usuario.setSaldoReal(1000.0);

        UserEntity destinatario = new UserEntity();
        destinatario.setId(2L);
        destinatario.setSaldoReal(500.0);

        CotacaoEntity cotacao = new CotacaoEntity();
        cotacao.setCotacaoCompra(5.0);
        cotacao.setCotacaoVenda(5.1);

        when(userRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(userRepository.findById(2L)).thenReturn(Optional.of(destinatario));
        when(cotacaoService.getCotacao(any(LocalDate.class))).thenReturn(cotacao);

        String result = transactionService.transferir(transactionDTO);

        assertEquals("Transferência realizada com sucesso", result);
        assertEquals(500.0, usuario.getSaldoReal());
        assertEquals(1000.0, destinatario.getSaldoReal());

        verify(transacaoRepository, times(1)).save(any(TransactionEntity.class));
        verify(userRepository, times(1)).save(usuario);
        verify(userRepository, times(1)).save(destinatario);
    }

    @Test
    void testTransferirSaldoInsuficiente() {
        RequestTransactionDTO transactionDTO = new RequestTransactionDTO();
        transactionDTO.setUsuario(1L);
        transactionDTO.setDestinatario(2L);
        transactionDTO.setValor(1000.0);
        transactionDTO.setMoeda(TipoDeMoeda.DOLAR);

        UserEntity usuario = new UserEntity();
        usuario.setId(1L);
        usuario.setSaldoReal(500.0);

        UserEntity destinatario = new UserEntity();
        destinatario.setId(2L);
        destinatario.setSaldoReal(500.0);

        CotacaoEntity cotacao = new CotacaoEntity();
        cotacao.setCotacaoCompra(5.0);
        cotacao.setCotacaoVenda(5.1);

        when(userRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(userRepository.findById(2L)).thenReturn(Optional.of(destinatario));
        when(cotacaoService.getCotacao(any(LocalDate.class))).thenReturn(cotacao);

        String result = transactionService.transferir(transactionDTO);

        assertEquals("Saldo insuficiente", result);
        assertEquals(500.0, usuario.getSaldoReal());
        assertEquals(500.0, destinatario.getSaldoReal());

        verify(transacaoRepository, never()).save(any(TransactionEntity.class));
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void testListarTransacoesPorUsuario() {
        Long usuarioId = 1L;

        UserEntity usuario = new UserEntity();
        usuario.setId(1L);
        usuario.setNome("Usuario");

        UserEntity destinatario = new UserEntity();
        destinatario.setId(2L);
        destinatario.setNome("Destinatario");

        TransactionEntity transaction = new TransactionEntity();
        transaction.setId(1L);
        transaction.setUsuario(usuario);
        transaction.setDestinatario(destinatario);
        transaction.setValor(100.0);
        transaction.setData(LocalDateTime.now());
        transaction.setSaldoAnterior(1000.0);

        when(transacaoRepository.findByUsuarioIdOrDestinatarioIdOrderByDataAsc(usuarioId, usuarioId)).thenReturn(List.of(transaction));

        List<ResponseTrasactionDTO> result = transactionService.listarTransacoesPorUsuario(usuarioId);

        assertEquals(1, result.size());
        ResponseTrasactionDTO dto = result.get(0);
        assertEquals(1L, dto.getTransactionId());
        assertEquals("Usuario", dto.getEmissor());
        assertEquals("Destinatario", dto.getReceptor());
        assertEquals(100.0, dto.getDebito());
        assertEquals(0.0, dto.getCredito());
        assertEquals(900.0, dto.getSaldoAtual());
    }
}
