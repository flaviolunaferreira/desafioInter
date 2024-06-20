package com.inter.desafio.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inter.desafio.model.entity.CotacaoEntity;
import com.inter.desafio.model.repository.CotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CotacaoServiceImplTest {

    @Mock
    private CotacaoRepository cotacaoRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CotacaoServiceImpl cotacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCotacaoFromDatabase() {
        LocalDate date = LocalDate.of(2024, 6, 18);
        CotacaoEntity cotacaoEntity = new CotacaoEntity();
        cotacaoEntity.setDataCotacao(date);
        cotacaoEntity.setCotacaoCompra(5.0);
        cotacaoEntity.setCotacaoVenda(5.1);

        when(cotacaoRepository.findByDataCotacao(date)).thenReturn(Optional.of(cotacaoEntity));

        CotacaoEntity result = cotacaoService.getCotacao(date);
        assertNotNull(result);
        assertEquals(5.0, result.getCotacaoCompra());
        assertEquals(5.1, result.getCotacaoVenda());

        verify(cotacaoRepository, times(1)).findByDataCotacao(date);
        verifyNoMoreInteractions(cotacaoRepository);
        verifyNoInteractions(restTemplate);
    }

    @Test
    void testAjustarParaUltimoDiaUtil() {
        LocalDate saturday = LocalDate.of(2024, 6, 22);
        LocalDate sunday = LocalDate.of(2024, 6, 23);
        LocalDate monday = LocalDate.of(2024, 6, 24);

        assertEquals(LocalDate.of(2024, 6, 21), cotacaoService.ajustarParaUltimoDiaUtil(saturday));
        assertEquals(LocalDate.of(2024, 6, 21), cotacaoService.ajustarParaUltimoDiaUtil(sunday));
        assertEquals(LocalDate.of(2024, 6, 24), cotacaoService.ajustarParaUltimoDiaUtil(monday));
    }
}
