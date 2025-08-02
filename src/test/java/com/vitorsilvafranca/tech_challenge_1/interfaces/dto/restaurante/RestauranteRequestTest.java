package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteRequestTest {

    @Test
    void deveDefinirEObterTodosOsCampos() {
        RestauranteRequest request = new RestauranteRequest();
        
        request.setNome("Restaurante Teste");
        request.setEndereco("Rua A, 123");
        request.setTipoDeCozinha(TipoDeCozinha.BRASILEIRA);
        request.setHorarioAbertura(LocalTime.of(8, 0));
        request.setHorarioFechamento(LocalTime.of(22, 0));
        request.setUsuarioId(1L);

        assertEquals("Restaurante Teste", request.getNome());
        assertEquals("Rua A, 123", request.getEndereco());
        assertEquals(TipoDeCozinha.BRASILEIRA, request.getTipoDeCozinha());
        assertEquals(LocalTime.of(8, 0), request.getHorarioAbertura());
        assertEquals(LocalTime.of(22, 0), request.getHorarioFechamento());
        assertEquals(1L, request.getUsuarioId());
    }

    @Test
    void devePermitirTipoDeCozinhaEstrangeira() {
        RestauranteRequest request = new RestauranteRequest();
        request.setTipoDeCozinha(TipoDeCozinha.ESTRANGEIRA);
        assertEquals(TipoDeCozinha.ESTRANGEIRA, request.getTipoDeCozinha());
    }
}