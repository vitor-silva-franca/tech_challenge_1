package com.vitorsilvafranca.tech_challenge_1.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {
        String mensagem = "Erro na aplicação";
        ApplicationException exception = new ApplicationException(mensagem);

        assertEquals(mensagem, exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void deveSerRuntimeException() {
        ApplicationException exception = new ApplicationException("Teste");
        assertInstanceOf(RuntimeException.class, exception);
    }
}