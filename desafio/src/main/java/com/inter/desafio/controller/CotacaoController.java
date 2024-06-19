package com.inter.desafio.controller;

import com.inter.desafio.model.entity.CotacaoEntity;
import com.inter.desafio.model.repository.CotacaoRepository;
import com.inter.desafio.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cotacao")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @GetMapping("/")
    public CotacaoEntity getCotacao() {
        String data = LocalDate.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(data, formatter);
        return cotacaoService.getCotacao(date);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<CotacaoEntity>> getAll() {
        return ResponseEntity.ok().body(cotacaoRepository.findAll());
    }


}
