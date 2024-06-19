package com.inter.desafio.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inter.desafio.model.entity.CotacaoEntity;
import com.inter.desafio.model.repository.CotacaoRepository;
import com.inter.desafio.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CotacaoServiceImpl implements CotacaoService {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CotacaoServiceImpl() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public CotacaoEntity getCotacao(LocalDate date) {
        // Ajustar data para o último dia útil, se necessário
        LocalDate consultaDate = ajustarParaUltimoDiaUtil(date);

        // Buscar cotação recursivamente até encontrar uma válida
        return buscarCotacaoRecursivamente(consultaDate);
    }

    private CotacaoEntity buscarCotacaoRecursivamente(LocalDate date) {
        // Verificar se a cotação já está no banco de dados
        Optional<CotacaoEntity> cotacaoOptional = cotacaoRepository.findByDataCotacao(date);
        if (cotacaoOptional.isPresent()) {
            return cotacaoOptional.get();
        }

        // Formatar data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        // Construir URL
        String url = String.format("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='%s'&$top=100&$format=json&$select=cotacaoCompra,cotacaoVenda",
                date.format(formatter));

        // Fazer requisição
        String response = restTemplate.getForObject(url, String.class);

        // Processar resposta
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            if (jsonNode.has("value") && jsonNode.get("value").isArray() && jsonNode.get("value").size() > 0) {
                JsonNode cotacaoData = jsonNode.get("value").get(0);

                Double cotacaoCompra = cotacaoData.get("cotacaoCompra").asDouble();
                Double cotacaoVenda = cotacaoData.get("cotacaoVenda").asDouble();

                // Salvar no banco de dados
                CotacaoEntity cotacao = new CotacaoEntity();
                cotacao.setDataCotacao(date);
                cotacao.setCotacaoCompra(cotacaoCompra);
                cotacao.setCotacaoVenda(cotacaoVenda);
                cotacaoRepository.save(cotacao);

                return cotacao;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Se não encontrou a cotação, tentar o dia anterior
        LocalDate previousDate = ajustarParaUltimoDiaUtil(date.minusDays(1));
        return buscarCotacaoRecursivamente(previousDate);
    }

    // Ajustando a data para sexta-feira quando cair no final de semana
    private LocalDate ajustarParaUltimoDiaUtil(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            return date.minusDays(1);
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            return date.minusDays(2);
        }
        return date;
    }
}
