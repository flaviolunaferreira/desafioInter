package com.inter.desafio.service;

import com.inter.desafio.model.entity.CotacaoEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface CotacaoService {
    CotacaoEntity getCotacao(LocalDate date);
}
