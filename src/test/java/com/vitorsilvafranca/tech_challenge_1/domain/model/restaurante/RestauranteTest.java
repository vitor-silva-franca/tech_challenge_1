package com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante;

import com.vitorsilvafranca.tech_challenge_1.shared.DomainException;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    @Test
    void deveCriarRestauranteComSucesso() {
        Restaurante restaurante = new Restaurante("Restaurante Teste", "Rua A, 123",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L);

        assertEquals("Restaurante Teste", restaurante.getNome());
        assertEquals("Rua A, 123", restaurante.getEndereco());
        assertEquals(TipoDeCozinha.BRASILEIRA, restaurante.getTipoDeCozinha());
        assertEquals(LocalTime.of(8, 0), restaurante.getHorarioAbertura());
        assertEquals(LocalTime.of(22, 0), restaurante.getHorarioFechamento());
        assertEquals(1L, restaurante.getUsuarioId());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        assertThrows(DomainException.class, () -> 
            new Restaurante(null, "Rua A, 123", TipoDeCozinha.BRASILEIRA, 
                LocalTime.of(8, 0), LocalTime.of(22, 0), 1L));
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        assertThrows(DomainException.class, () -> 
            new Restaurante("", "Rua A, 123", TipoDeCozinha.BRASILEIRA, 
                LocalTime.of(8, 0), LocalTime.of(22, 0), 1L));
    }

    @Test
    void deveLancarExcecaoQuandoNomeForMuitoCurto() {
        assertThrows(DomainException.class, () -> 
            new Restaurante("A", "Rua A, 123", TipoDeCozinha.BRASILEIRA, 
                LocalTime.of(8, 0), LocalTime.of(22, 0), 1L));
    }

    @Test
    void deveLancarExcecaoQuandoEnderecoForNulo() {
        assertThrows(DomainException.class, () -> 
            new Restaurante("Restaurante Teste", null, TipoDeCozinha.BRASILEIRA, 
                LocalTime.of(8, 0), LocalTime.of(22, 0), 1L));
    }

    @Test
    void deveLancarExcecaoQuandoHorarioAberturaForNulo() {
        assertThrows(DomainException.class, () -> 
            new Restaurante("Restaurante Teste", "Rua A, 123", TipoDeCozinha.BRASILEIRA, 
                null, LocalTime.of(22, 0), 1L));
    }

    @Test
    void deveLancarExcecaoQuandoHorarioFechamentoForNulo() {
        assertThrows(DomainException.class, () -> 
            new Restaurante("Restaurante Teste", "Rua A, 123", TipoDeCozinha.BRASILEIRA, 
                LocalTime.of(8, 0), null, 1L));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioIdForNulo() {
        assertThrows(DomainException.class, () -> 
            new Restaurante("Restaurante Teste", "Rua A, 123", TipoDeCozinha.BRASILEIRA, 
                LocalTime.of(8, 0), LocalTime.of(22, 0), null));
    }

    @Test
    void deveLancarExcecaoQuandoTipoDeCozinhaForInvalido() {
        assertThrows(DomainException.class, () -> 
            new Restaurante("Restaurante Teste", "Rua A, 123", null, 
                LocalTime.of(8, 0), LocalTime.of(22, 0), 1L));
    }
}