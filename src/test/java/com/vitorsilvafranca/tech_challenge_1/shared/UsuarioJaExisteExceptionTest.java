package com.vitorsilvafranca.tech_challenge_1.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioJaExisteExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemFormatada() {
        String campo = "email teste@email.com";
        UsuarioJaExisteException exception = new UsuarioJaExisteException(campo);

        assertEquals("Já existe um usuário com email teste@email.com", exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void deveFormatarMensagemCorretamente() {
        UsuarioJaExisteException exception = new UsuarioJaExisteException("login joao123");
        assertEquals("Já existe um usuário com login joao123", exception.getMessage());
    }

    @Test
    void deveSerRuntimeException() {
        UsuarioJaExisteException exception = new UsuarioJaExisteException("teste");
        assertInstanceOf(RuntimeException.class, exception);
    }
}