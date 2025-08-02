package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginUsuarioRequestTest {

    @Test
    void deveDefinirEObterLogin() {
        LoginUsuarioRequest request = new LoginUsuarioRequest();
        
        request.setLogin("joao123");
        assertEquals("joao123", request.getLogin());
    }

    @Test
    void deveDefinirEObterSenha() {
        LoginUsuarioRequest request = new LoginUsuarioRequest();
        
        request.setSenha("senha123");
        assertEquals("senha123", request.getSenha());
    }

    @Test
    void deveDefinirEObterTodosOsCampos() {
        LoginUsuarioRequest request = new LoginUsuarioRequest();
        
        request.setLogin("usuario");
        request.setSenha("minhasenha");

        assertEquals("usuario", request.getLogin());
        assertEquals("minhasenha", request.getSenha());
    }

    @Test
    void devePermitirValoresNulos() {
        LoginUsuarioRequest request = new LoginUsuarioRequest();
        
        request.setLogin(null);
        request.setSenha(null);

        assertNull(request.getLogin());
        assertNull(request.getSenha());
    }
}