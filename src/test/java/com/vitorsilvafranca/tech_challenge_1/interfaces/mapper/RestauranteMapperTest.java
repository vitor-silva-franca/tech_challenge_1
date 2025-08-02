package com.vitorsilvafranca.tech_challenge_1.interfaces.mapper;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteMapperTest {

    private RestauranteRequest restauranteRequest;
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restauranteRequest = new RestauranteRequest();
        restauranteRequest.setNome("Restaurante Teste");
        restauranteRequest.setEndereco("Rua A, 123");
        restauranteRequest.setTipoDeCozinha(TipoDeCozinha.BRASILEIRA);
        restauranteRequest.setHorarioAbertura(LocalTime.of(8, 0));
        restauranteRequest.setHorarioFechamento(LocalTime.of(22, 0));
        restauranteRequest.setUsuarioId(1L);

        restaurante = new Restaurante("Restaurante Teste", "Rua A, 123",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L);
        restaurante.setId(1L);
    }

    @Test
    void deveConverterRestauranteRequestParaModel() {
        Restaurante resultado = RestauranteMapper.toModel(restauranteRequest);

        assertEquals("Restaurante Teste", resultado.getNome());
        assertEquals("Rua A, 123", resultado.getEndereco());
        assertEquals(TipoDeCozinha.BRASILEIRA, resultado.getTipoDeCozinha());
        assertEquals(LocalTime.of(8, 0), resultado.getHorarioAbertura());
        assertEquals(LocalTime.of(22, 0), resultado.getHorarioFechamento());
        assertEquals(1L, resultado.getUsuarioId());
    }

    @Test
    void deveConverterRestauranteParaResponseSemItens() {
        RestauranteResponse resultado = RestauranteMapper.fromModel(restaurante);

        assertEquals("Restaurante Teste", resultado.getNome());
        assertEquals("Rua A, 123", resultado.getEndereco());
        assertEquals(TipoDeCozinha.BRASILEIRA, resultado.getTipoDeCozinha());
        assertEquals(LocalTime.of(8, 0), resultado.getHorarioAbertura());
        assertEquals(LocalTime.of(22, 0), resultado.getHorarioFechamento());
        assertEquals(1L, resultado.getUsuarioId());
        assertTrue(resultado.getItens().isEmpty());
    }

    @Test
    void deveConverterRestauranteParaResponseComItens() {
        Item item = new Item("Pizza", "Deliciosa", new BigDecimal("25.90"), 
                true, "foto.jpg", restaurante);
        restaurante.setItens(List.of(item));

        RestauranteResponse resultado = RestauranteMapper.fromModel(restaurante);

        assertEquals(1, resultado.getItens().size());
        assertEquals("Pizza", resultado.getItens().get(0).getNome());
    }
}