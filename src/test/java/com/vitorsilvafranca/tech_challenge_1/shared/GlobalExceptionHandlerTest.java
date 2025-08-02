package com.vitorsilvafranca.tech_challenge_1.shared;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void deveHandleDomainException() {
        DomainException exception = new DomainException("Erro de domínio");

        ResponseEntity<ErrorResponse> response = handler.handleDomainException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro de domínio", response.getBody().getMensagem());
        assertEquals(400, response.getBody().getStatus());
    }

    @Test
    void deveHandleApplicationException() {
        ApplicationException exception = new ApplicationException("Erro de aplicação");

        ResponseEntity<ErrorResponse> response = handler.handleDomainException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro de aplicação", response.getBody().getMensagem());
    }

    @Test
    void deveHandleUsuarioNotFoundException() {
        UsuarioNotFoundException exception = new UsuarioNotFoundException("123");

        ResponseEntity<ErrorResponse> response = handler.handleUsuarioNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuário 123 não encontrado.", response.getBody().getMensagem());
    }

    @Test
    void deveHandleRestauranteNotFoundException() {
        RestauranteNotFoundException exception = new RestauranteNotFoundException("456");

        ResponseEntity<ErrorResponse> response = handler.handleRestauranteNotFound(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Restaurante 456 não encontrado.", response.getBody().getMensagem());
    }

    @Test
    void deveHandleItemNotFoundException() {
        ItemNotFoundException exception = new ItemNotFoundException("789");

        ResponseEntity<ErrorResponse> response = handler.handleItemNotFound(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Item 789 não encontrado.", response.getBody().getMensagem());
    }

    @Test
    void deveHandleUsuarioJaExisteException() {
        UsuarioJaExisteException exception = new UsuarioJaExisteException("email teste@email.com");

        ResponseEntity<ErrorResponse> response = handler.handleUsuarioJaExiste(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Já existe um usuário com email teste@email.com", response.getBody().getMensagem());
    }

    @Test
    void deveHandleMethodArgumentTypeMismatchException() {
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);

        ResponseEntity<String> response = handler.handleTypeMismatch(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("ID inválido. Informe um ID na URL.", response.getBody());
    }

    @Test
    void deveHandleMissingPathVariableException() {
        MissingPathVariableException exception = mock(MissingPathVariableException.class);
        when(exception.getVariableName()).thenReturn("id");

        ResponseEntity<String> response = handler.handleMissingPathVariable(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Path variable is missing: id", response.getBody());
    }

    @Test
    void deveHandleMethodArgumentNotValidException() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objeto", "campo", "mensagem de erro");

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<ErrorResponse> response = handler.handleValidationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("campo: mensagem de erro", response.getBody().getMensagem());
    }

    @Test
    void deveHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException exception = mock(HttpMessageNotReadableException.class);
        when(exception.getCause()).thenReturn(null);

        ResponseEntity<ErrorResponse> response = handler.handleJsonParseError(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro ao interpretar o JSON", response.getBody().getMensagem());
    }

    @Test
    void deveHandleGeneralException() {
        Exception exception = new Exception("Erro genérico");

        ResponseEntity<ErrorResponse> response = handler.handleGeneralException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno no servidor", response.getBody().getMensagem());
        assertEquals(500, response.getBody().getStatus());
    }
}