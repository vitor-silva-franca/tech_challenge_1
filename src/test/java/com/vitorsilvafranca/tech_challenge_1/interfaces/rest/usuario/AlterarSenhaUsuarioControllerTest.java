package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.AlterarSenhaUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.TrocarSenhaRequest;
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
class AlterarSenhaUsuarioControllerTest {

    @Mock
    private AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase;

    @InjectMocks
    private AlterarSenhaUsuarioController controller;

    @Test
    void deveAlterarSenhaComSucesso() {
        TrocarSenhaRequest request = new TrocarSenhaRequest();
        request.setEmail("joao@email.com");
        request.setLogin("joao123");
        request.setSenhaNova("novaSenha123");

        String mensagemSucesso = "Senha alterada com sucesso";
        when(alterarSenhaUsuarioUseCase.alterar(any())).thenReturn(mensagemSucesso);

        ResponseEntity<String> response = controller.alterar(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagemSucesso, response.getBody());
        verify(alterarSenhaUsuarioUseCase).alterar(any());
    }

    @Test
    void deveRetornarSolicitacaoInvalida() {
        TrocarSenhaRequest request = new TrocarSenhaRequest();
        request.setEmail("joao@email.com");
        request.setLogin("maria123");
        request.setSenhaNova("novaSenha123");

        String mensagemInvalida = "Solicitação inválida";
        when(alterarSenhaUsuarioUseCase.alterar(any())).thenReturn(mensagemInvalida);

        ResponseEntity<String> response = controller.alterar(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagemInvalida, response.getBody());
        verify(alterarSenhaUsuarioUseCase).alterar(any());
    }
}