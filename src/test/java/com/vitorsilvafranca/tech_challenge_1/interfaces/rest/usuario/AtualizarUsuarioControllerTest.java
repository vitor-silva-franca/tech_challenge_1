package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.AtualizarUsuarioUseCase;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarUsuarioControllerTest {

    @Mock
    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @InjectMocks
    private AtualizarUsuarioController controller;

    @Test
    void deveAtualizarUsuarioComSucesso() {
        Long id = 1L;
        UsuarioRequest request = new UsuarioRequest();
        request.setNome("João Silva Atualizado");
        request.setEmail("joao.novo@email.com");
        request.setLogin("joao.novo");
        request.setSenha("novaSenha123");
        request.setEndereco("Nova Rua, 456");
        request.setRole(RoleUsuario.DONO);

        Usuario usuarioAtualizado = new Usuario("João Silva Atualizado", "joao.novo@email.com", "joao.novo",
                "novaSenha123", new Date(), "Nova Rua, 456", RoleUsuario.DONO);
        usuarioAtualizado.setId(id);

        when(atualizarUsuarioUseCase.atualizar(eq(id), any(Usuario.class))).thenReturn(usuarioAtualizado);

        ResponseEntity<UsuarioResponse> response = controller.atualizar(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("João Silva Atualizado", response.getBody().getNome());
        assertEquals("joao.novo@email.com", response.getBody().getEmail());
        assertEquals("joao.novo", response.getBody().getLogin());
        assertEquals(RoleUsuario.DONO, response.getBody().getRole());
        verify(atualizarUsuarioUseCase).atualizar(eq(id), any(Usuario.class));
    }
}