package com.vitorsilvafranca.tech_challenge_1.shared;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void deveCriarErrorResponseComSucesso() {
        LocalDateTime timestamp = LocalDateTime.now();
        int status = 400;
        String mensagem = "Erro de validação";

        ErrorResponse errorResponse = new ErrorResponse(timestamp, status, mensagem);

        assertEquals(timestamp, errorResponse.getTimestamp());
        assertEquals(status, errorResponse.getStatus());
        assertEquals(mensagem, errorResponse.getMensagem());
    }

    @Test
    void deveRetornarValoresCorretos() {
        LocalDateTime timestamp = LocalDateTime.of(2023, 12, 25, 10, 30, 45);
        ErrorResponse errorResponse = new ErrorResponse(timestamp, 404, "Não encontrado");

        assertEquals(timestamp, errorResponse.getTimestamp());
        assertEquals(404, errorResponse.getStatus());
        assertEquals("Não encontrado", errorResponse.getMensagem());
    }
}