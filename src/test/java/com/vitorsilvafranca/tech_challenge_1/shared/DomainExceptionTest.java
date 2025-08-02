package com.vitorsilvafranca.tech_challenge_1.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {
        String mensagem = "Erro no domínio";
        DomainException exception = new DomainException(mensagem);

        assertEquals(mensagem, exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void deveSerRuntimeException() {
        DomainException exception = new DomainException("Teste");
        assertInstanceOf(RuntimeException.class, exception);
    }
}