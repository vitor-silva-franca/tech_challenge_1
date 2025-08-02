package com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoDeCozinhaTest {

    @Test
    void deveConterValoresBrasileiraEEstrangeira() {
        TipoDeCozinha[] valores = TipoDeCozinha.values();
        
        assertEquals(2, valores.length);
        assertEquals(TipoDeCozinha.BRASILEIRA, valores[0]);
        assertEquals(TipoDeCozinha.ESTRANGEIRA, valores[1]);
    }

    @Test
    void deveConverterStringParaEnum() {
        assertEquals(TipoDeCozinha.BRASILEIRA, TipoDeCozinha.valueOf("BRASILEIRA"));
        assertEquals(TipoDeCozinha.ESTRANGEIRA, TipoDeCozinha.valueOf("ESTRANGEIRA"));
    }

    @Test
    void deveLancarExcecaoParaValorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> 
            TipoDeCozinha.valueOf("INVALIDO"));
    }
}