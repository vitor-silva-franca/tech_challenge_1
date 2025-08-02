package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.BuscarUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.RoleUsuario;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarUsuarioControllerTest {

    @Mock
    private BuscarUsuarioUseCase buscarUsuarioUseCase;

    @InjectMocks
    private BuscarUsuarioController controller;

    @Test
    void deveBuscarUsuarioComSucesso() {
        Long id = 1L;
        Usuario usuario = new Usuario("João Silva", "joao@email.com", "joao123",
                "senha123", new Date(), "Rua A, 123", RoleUsuario.CLIENTE);
        usuario.setId(id);

        when(buscarUsuarioUseCase.buscarPorId(id)).thenReturn(usuario);

        ResponseEntity<UsuarioResponse> response = controller.buscar(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("João Silva", response.getBody().getNome());
        assertEquals("joao@email.com", response.getBody().getEmail());
        assertEquals("joao123", response.getBody().getLogin());
        assertEquals(id, response.getBody().getId());
        verify(buscarUsuarioUseCase).buscarPorId(id);
    }
}