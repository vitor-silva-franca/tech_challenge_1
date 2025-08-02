package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteResponseTest {

    @Test
    void deveCriarRestauranteResponseComConstrutorCompleto() {
        ItemResponse item = new ItemResponse("Pizza", "Deliciosa", 
                new BigDecimal("25.00"), true, "foto.jpg", 1L);
        List<ItemResponse> itens = List.of(item);

        RestauranteResponse response = new RestauranteResponse("Restaurante Teste", 
                "Rua A, 123", TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), 
                LocalTime.of(22, 0), 1L, itens);

        assertEquals("Restaurante Teste", response.getNome());
        assertEquals("Rua A, 123", response.getEndereco());
        assertEquals(TipoDeCozinha.BRASILEIRA, response.getTipoDeCozinha());
        assertEquals(LocalTime.of(8, 0), response.getHorarioAbertura());
        assertEquals(LocalTime.of(22, 0), response.getHorarioFechamento());
        assertEquals(1L, response.getUsuarioId());
        assertEquals(itens, response.getItens());
    }

    @Test
    void devePermitirAlterarValoresComSetters() {
        RestauranteResponse response = new RestauranteResponse("Nome", "Endereço", 
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L, null);

        response.setNome("Novo Nome");
        response.setEndereco("Novo Endereço");
        response.setTipoDeCozinha(TipoDeCozinha.ESTRANGEIRA);
        response.setHorarioAbertura(LocalTime.of(9, 0));
        response.setHorarioFechamento(LocalTime.of(23, 0));
        response.setUsuarioId(2L);
        response.setItens(List.of());

        assertEquals("Novo Nome", response.getNome());
        assertEquals("Novo Endereço", response.getEndereco());
        assertEquals(TipoDeCozinha.ESTRANGEIRA, response.getTipoDeCozinha());
        assertEquals(LocalTime.of(9, 0), response.getHorarioAbertura());
        assertEquals(LocalTime.of(23, 0), response.getHorarioFechamento());
        assertEquals(2L, response.getUsuarioId());
        assertEquals(List.of(), response.getItens());
    }
}