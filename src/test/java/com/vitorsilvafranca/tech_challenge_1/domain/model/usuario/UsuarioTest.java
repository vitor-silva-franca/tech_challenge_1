package com.vitorsilvafranca.tech_challenge_1.domain.model.usuario;

import com.vitorsilvafranca.tech_challenge_1.shared.DomainException;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveCriarUsuarioCompletoComSucesso() {
        Usuario usuario = new Usuario("João Silva", "joao@email.com", "joao123", 
                "senha123", new Date(), "Rua A, 123", RoleUsuario.CLIENTE);

        assertEquals("João Silva", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals("joao123", usuario.getLogin());
        assertEquals("senha123", usuario.getSenha());
        assertEquals("Rua A, 123", usuario.getEndereco());
        assertEquals(RoleUsuario.CLIENTE, usuario.getRole());
    }

    @Test
    void deveCriarUsuarioParaLoginComSucesso() {
        Usuario usuario = new Usuario("joao123", "senha123");

        assertEquals("joao123", usuario.getLogin());
        assertEquals("senha123", usuario.getSenha());
    }

    @Test
    void deveCriarUsuarioParaAlteracaoSenhaComSucesso() {
        Usuario usuario = new Usuario("joao@email.com", "joao123", "novaSenha123");

        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals("joao123", usuario.getLogin());
        assertEquals("novaSenha123", usuario.getSenha());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        assertThrows(DomainException.class, () -> 
            new Usuario(null, "joao@email.com", "joao123", "senha123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE));
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        assertThrows(DomainException.class, () -> 
            new Usuario("", "joao@email.com", "joao123", "senha123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE));
    }

    @Test
    void deveLancarExcecaoQuandoNomeForMuitoCurto() {
        assertThrows(DomainException.class, () -> 
            new Usuario("J", "joao@email.com", "joao123", "senha123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE));
    }

    @Test
    void deveLancarExcecaoQuandoEmailForNulo() {
        assertThrows(DomainException.class, () -> 
            new Usuario("João Silva", null, "joao123", "senha123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE));
    }

    @Test
    void deveLancarExcecaoQuandoLoginForNulo() {
        assertThrows(DomainException.class, () -> 
            new Usuario("João Silva", "joao@email.com", null, "senha123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaForNula() {
        assertThrows(DomainException.class, () -> 
            new Usuario("João Silva", "joao@email.com", "joao123", null, 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaForMuitoCurta() {
        assertThrows(DomainException.class, () -> 
            new Usuario("João Silva", "joao@email.com", "joao123", "123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE));
    }

    @Test
    void deveLancarExcecaoQuandoEnderecoForNulo() {
        assertThrows(DomainException.class, () -> 
            new Usuario("João Silva", "joao@email.com", "joao123", "senha123", 
                new Date(), null, RoleUsuario.CLIENTE));
    }

    @Test
    void deveLancarExcecaoQuandoRoleForNula() {
        assertThrows(DomainException.class, () -> 
            new Usuario("João Silva", "joao@email.com", "joao123", "senha123", 
                new Date(), "Rua A, 123", null));
    }
}