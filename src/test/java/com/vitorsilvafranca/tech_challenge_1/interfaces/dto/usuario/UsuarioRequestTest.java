package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.RoleUsuario;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRequestTest {

    @Test
    void deveDefinirEObterTodosOsCampos() {
        UsuarioRequest request = new UsuarioRequest();
        Date data = new Date();
        
        request.setNome("João Silva");
        request.setEmail("joao@email.com");
        request.setLogin("joao123");
        request.setSenha("senha123");
        request.setDataAlteracao(data);
        request.setEndereco("Rua A, 123");
        request.setRole(RoleUsuario.CLIENTE);

        assertEquals("João Silva", request.getNome());
        assertEquals("joao@email.com", request.getEmail());
        assertEquals("joao123", request.getLogin());
        assertEquals("senha123", request.getSenha());
        assertEquals(data, request.getDataAlteracao());
        assertEquals("Rua A, 123", request.getEndereco());
        assertEquals(RoleUsuario.CLIENTE, request.getRole());
    }

    @Test
    void devePermitirRoleDono() {
        UsuarioRequest request = new UsuarioRequest();
        request.setRole(RoleUsuario.DONO);
        assertEquals(RoleUsuario.DONO, request.getRole());
    }

    @Test
    void devePermitirDataAlteracaoNula() {
        UsuarioRequest request = new UsuarioRequest();
        request.setDataAlteracao(null);
        assertNull(request.getDataAlteracao());
    }
}