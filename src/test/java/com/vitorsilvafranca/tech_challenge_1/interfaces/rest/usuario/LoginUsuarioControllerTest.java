package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.LoginUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.LoginUsuarioRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUsuarioControllerTest {

    @Mock
    private LoginUsuarioUseCase loginUsuarioUseCase;

    @InjectMocks
    private LoginUsuarioController controller;

    @Test
    void deveRealizarLoginComSucesso() {
        LoginUsuarioRequest request = new LoginUsuarioRequest();
        request.setLogin("joao123");
        request.setSenha("senha123");

        String mensagemSucesso = "Usuário logado com sucesso!";
        when(loginUsuarioUseCase.login(any())).thenReturn(mensagemSucesso);

        ResponseEntity<String> response = controller.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagemSucesso, response.getBody());
        verify(loginUsuarioUseCase).login(any());
    }

    @Test
    void deveRetornarAcessoNegado() {
        LoginUsuarioRequest request = new LoginUsuarioRequest();
        request.setLogin("joao123");
        request.setSenha("senhaErrada");

        String mensagemNegado = "Acesso negado!";
        when(loginUsuarioUseCase.login(any())).thenReturn(mensagemNegado);

        ResponseEntity<String> response = controller.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagemNegado, response.getBody());
        verify(loginUsuarioUseCase).login(any());
    }
}