package com.vitorsilvafranca.tech_challenge_1.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemNotFoundExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemFormatada() {
        String id = "123";
        ItemNotFoundException exception = new ItemNotFoundException(id);

        assertEquals("Item 123 não encontrado.", exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void deveFormatarMensagemCorretamente() {
        ItemNotFoundException exception = new ItemNotFoundException("ABC");
        assertEquals("Item ABC não encontrado.", exception.getMessage());
    }

    @Test
    void deveSerRuntimeException() {
        ItemNotFoundException exception = new ItemNotFoundException("1");
        assertInstanceOf(RuntimeException.class, exception);
    }
}