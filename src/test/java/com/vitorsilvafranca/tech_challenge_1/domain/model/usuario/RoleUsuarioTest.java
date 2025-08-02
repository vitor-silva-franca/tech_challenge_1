package com.vitorsilvafranca.tech_challenge_1.domain.model.usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleUsuarioTest {

    @Test
    void deveConterValoresDonoECliente() {
        RoleUsuario[] valores = RoleUsuario.values();
        
        assertEquals(2, valores.length);
        assertEquals(RoleUsuario.DONO, valores[0]);
        assertEquals(RoleUsuario.CLIENTE, valores[1]);
    }

    @Test
    void deveConverterStringParaEnum() {
        assertEquals(RoleUsuario.DONO, RoleUsuario.valueOf("DONO"));
        assertEquals(RoleUsuario.CLIENTE, RoleUsuario.valueOf("CLIENTE"));
    }

    @Test
    void deveLancarExcecaoParaValorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> 
            RoleUsuario.valueOf("INVALIDO"));
    }
}