package com.vitorsilvafranca.tech_challenge_1.domain.repository;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.RoleUsuario;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        usuario = new Usuario("João Silva", "joao@email.com", "joao123", 
                "senha123", new Date(), "Rua A, 123", RoleUsuario.CLIENTE);
        usuario.setId(1L);
    }

    @Test
    void deveBuscarUsuarioPorId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioRepository.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void deveBuscarUsuarioPorEmail() {
        when(usuarioRepository.findByEmail("joao@email.com")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioRepository.findByEmail("joao@email.com");

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
        verify(usuarioRepository).findByEmail("joao@email.com");
    }

    @Test
    void deveBuscarUsuarioPorLogin() {
        when(usuarioRepository.findByLogin("joao123")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioRepository.findByLogin("joao123");

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
        verify(usuarioRepository).findByLogin("joao123");
    }

    @Test
    void deveVerificarSeEmailExiste() {
        when(usuarioRepository.existsByEmail("joao@email.com")).thenReturn(true);

        boolean resultado = usuarioRepository.existsByEmail("joao@email.com");

        assertTrue(resultado);
        verify(usuarioRepository).existsByEmail("joao@email.com");
    }

    @Test
    void deveVerificarSeLoginExiste() {
        when(usuarioRepository.existsByLogin("joao123")).thenReturn(true);

        boolean resultado = usuarioRepository.existsByLogin("joao123");

        assertTrue(resultado);
        verify(usuarioRepository).existsByLogin("joao123");
    }

    @Test
    void deveSalvarUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioRepository.save(usuario);

        assertEquals(usuario, resultado);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deveDeletarUsuarioPorId() {
        doNothing().when(usuarioRepository).deleteById(1L);

        assertDoesNotThrow(() -> usuarioRepository.deleteById(1L));
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void deveRetornarVazioQuandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Usuario> resultado = usuarioRepository.findById(999L);

        assertFalse(resultado.isPresent());
        verify(usuarioRepository).findById(999L);
    }

    @Test
    void deveRetornarFalseQuandoEmailNaoExiste() {
        when(usuarioRepository.existsByEmail("inexistente@email.com")).thenReturn(false);

        boolean resultado = usuarioRepository.existsByEmail("inexistente@email.com");

        assertFalse(resultado);
        verify(usuarioRepository).existsByEmail("inexistente@email.com");
    }
}