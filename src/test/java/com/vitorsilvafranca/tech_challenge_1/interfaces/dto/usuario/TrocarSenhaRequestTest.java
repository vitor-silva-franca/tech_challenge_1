package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrocarSenhaRequestTest {

    @Test
    void deveDefinirEObterTodosOsCampos() {
        TrocarSenhaRequest request = new TrocarSenhaRequest();
        
        request.setEmail("joao@email.com");
        request.setLogin("joao123");
        request.setSenhaNova("novaSenha123");

        assertEquals("joao@email.com", request.getEmail());
        assertEquals("joao123", request.getLogin());
        assertEquals("novaSenha123", request.getSenhaNova());
    }

    @Test
    void deveDefinirEObterEmail() {
        TrocarSenhaRequest request = new TrocarSenhaRequest();
        
        request.setEmail("teste@email.com");
        assertEquals("teste@email.com", request.getEmail());
    }

    @Test
    void deveDefinirEObterLogin() {
        TrocarSenhaRequest request = new TrocarSenhaRequest();
        
        request.setLogin("usuario123");
        assertEquals("usuario123", request.getLogin());
    }

    @Test
    void deveDefinirEObterSenhaNova() {
        TrocarSenhaRequest request = new TrocarSenhaRequest();
        
        request.setSenhaNova("senhaNova456");
        assertEquals("senhaNova456", request.getSenhaNova());
    }

    @Test
    void devePermitirValoresNulos() {
        TrocarSenhaRequest request = new TrocarSenhaRequest();
        
        request.setEmail(null);
        request.setLogin(null);
        request.setSenhaNova(null);

        assertNull(request.getEmail());
        assertNull(request.getLogin());
        assertNull(request.getSenhaNova());
    }
}