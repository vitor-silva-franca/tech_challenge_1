package com.vitorsilvafranca.tech_challenge_1.interfaces.mapper;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.RoleUsuario;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.LoginUsuarioRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.TrocarSenhaRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.UsuarioRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.UsuarioResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperTest {

    private UsuarioRequest usuarioRequest;
    private LoginUsuarioRequest loginRequest;
    private TrocarSenhaRequest trocarSenhaRequest;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioRequest = new UsuarioRequest();
        usuarioRequest.setNome("João Silva");
        usuarioRequest.setEmail("joao@email.com");
        usuarioRequest.setLogin("joao123");
        usuarioRequest.setSenha("senha123");
        usuarioRequest.setEndereco("Rua A, 123");
        usuarioRequest.setRole(RoleUsuario.CLIENTE);

        loginRequest = new LoginUsuarioRequest();
        loginRequest.setLogin("joao123");
        loginRequest.setSenha("senha123");

        trocarSenhaRequest = new TrocarSenhaRequest();
        trocarSenhaRequest.setEmail("joao@email.com");
        trocarSenhaRequest.setLogin("joao123");
        trocarSenhaRequest.setSenhaNova("novaSenha123");

        usuario = new Usuario("João Silva", "joao@email.com", "joao123", 
                "senha123", new Date(), "Rua A, 123", RoleUsuario.CLIENTE);
        usuario.setId(1L);
    }

    @Test
    void deveConverterUsuarioRequestParaModel() {
        Usuario resultado = UsuarioMapper.toModel(usuarioRequest);

        assertEquals("João Silva", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
        assertEquals("joao123", resultado.getLogin());
        assertEquals("senha123", resultado.getSenha());
        assertEquals("Rua A, 123", resultado.getEndereco());
        assertEquals(RoleUsuario.CLIENTE, resultado.getRole());
        assertNotNull(resultado.getDataAlteracao());
    }

    @Test
    void deveConverterLoginRequestParaModel() {
        Usuario resultado = UsuarioMapper.toLogin(loginRequest);

        assertEquals("joao123", resultado.getLogin());
        assertEquals("senha123", resultado.getSenha());
    }

    @Test
    void deveConverterTrocarSenhaRequestParaModel() {
        Usuario resultado = UsuarioMapper.toSenhaNova(trocarSenhaRequest);

        assertEquals("joao@email.com", resultado.getEmail());
        assertEquals("joao123", resultado.getLogin());
        assertEquals("novaSenha123", resultado.getSenha());
    }

    @Test
    void deveConverterUsuarioParaResponse() {
        UsuarioResponse resultado = UsuarioMapper.fromModel(usuario);

        assertEquals(1L, resultado.getId());
        assertEquals("João Silva", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
        assertEquals("joao123", resultado.getLogin());
        assertEquals("Rua A, 123", resultado.getEndereco());
        assertEquals(RoleUsuario.CLIENTE, resultado.getRole());
    }

    @Test
    void deveConverterComRoleDono() {
        usuarioRequest.setRole(RoleUsuario.DONO);
        Usuario resultado = UsuarioMapper.toModel(usuarioRequest);
        assertEquals(RoleUsuario.DONO, resultado.getRole());
    }
}