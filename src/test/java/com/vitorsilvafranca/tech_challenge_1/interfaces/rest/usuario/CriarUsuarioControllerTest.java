package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.CriarUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.RoleUsuario;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.UsuarioRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.UsuarioResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioControllerTest {

    @Mock
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @InjectMocks
    private CriarUsuarioController controller;

    @Test
    void deveCriarUsuarioComSucesso() {
        UsuarioRequest request = new UsuarioRequest();
        request.setNome("João Silva");
        request.setEmail("joao@email.com");
        request.setLogin("joao123");
        request.setSenha("senha123");
        request.setEndereco("Rua A, 123");
        request.setRole(RoleUsuario.CLIENTE);

        Usuario usuarioCriado = new Usuario("João Silva", "joao@email.com", "joao123",
                "senha123", new Date(), "Rua A, 123", RoleUsuario.CLIENTE);
        usuarioCriado.setId(1L);

        when(criarUsuarioUseCase.criar(any(Usuario.class))).thenReturn(usuarioCriado);

        ResponseEntity<UsuarioResponse> response = controller.criar(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("João Silva", response.getBody().getNome());
        assertEquals("joao@email.com", response.getBody().getEmail());
        assertEquals(1L, response.getBody().getId());
        verify(criarUsuarioUseCase).criar(any(Usuario.class));
    }
}