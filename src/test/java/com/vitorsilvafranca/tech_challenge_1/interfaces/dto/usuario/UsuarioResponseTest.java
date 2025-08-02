package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.RoleUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioResponseTest {

    @Test
    void deveCriarUsuarioResponseComConstrutorCompleto() {
        UsuarioResponse response = new UsuarioResponse(1L, "João Silva", 
                "joao@email.com", "joao123", "Rua A, 123", RoleUsuario.CLIENTE);

        assertEquals(1L, response.getId());
        assertEquals("João Silva", response.getNome());
        assertEquals("joao@email.com", response.getEmail());
        assertEquals("joao123", response.getLogin());
        assertEquals("Rua A, 123", response.getEndereco());
        assertEquals(RoleUsuario.CLIENTE, response.getRole());
    }

    @Test
    void deveRetornarValoresCorretosComRoleDono() {
        UsuarioResponse response = new UsuarioResponse(2L, "Maria", 
                "maria@email.com", "maria123", "Rua B, 456", RoleUsuario.DONO);

        assertEquals(2L, response.getId());
        assertEquals("Maria", response.getNome());
        assertEquals("maria@email.com", response.getEmail());
        assertEquals("maria123", response.getLogin());
        assertEquals("Rua B, 456", response.getEndereco());
        assertEquals(RoleUsuario.DONO, response.getRole());
    }
}