package com.vitorsilvafranca.tech_challenge_1.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioNotFoundExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemFormatada() {
        String id = "789";
        UsuarioNotFoundException exception = new UsuarioNotFoundException(id);

        assertEquals("Usuário 789 não encontrado.", exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void deveFormatarMensagemCorretamente() {
        UsuarioNotFoundException exception = new UsuarioNotFoundException("joao@email.com");
        assertEquals("Usuário joao@email.com não encontrado.", exception.getMessage());
    }

    @Test
    void deveSerRuntimeException() {
        UsuarioNotFoundException exception = new UsuarioNotFoundException("1");
        assertInstanceOf(RuntimeException.class, exception);
    }
}