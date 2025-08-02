package com.vitorsilvafranca.tech_challenge_1.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteNotFoundExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemFormatada() {
        String id = "456";
        RestauranteNotFoundException exception = new RestauranteNotFoundException(id);

        assertEquals("Restaurante 456 não encontrado.", exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void deveFormatarMensagemCorretamente() {
        RestauranteNotFoundException exception = new RestauranteNotFoundException("XYZ");
        assertEquals("Restaurante XYZ não encontrado.", exception.getMessage());
    }

    @Test
    void deveSerRuntimeException() {
        RestauranteNotFoundException exception = new RestauranteNotFoundException("1");
        assertInstanceOf(RuntimeException.class, exception);
    }
}