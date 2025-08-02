package com.vitorsilvafranca.tech_challenge_1.application.usuario;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarUsuarioUseCaseTest {

    private UsuarioRepository usuarioRepository;
    private BuscarUsuarioUseCase buscarUsuarioUseCase;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        buscarUsuarioUseCase = new BuscarUsuarioUseCase(usuarioRepository);
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = buscarUsuarioUseCase.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(usuarioRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long id = 2L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> buscarUsuarioUseCase.buscarPorId(id));
        verify(usuarioRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        assertThrows(ApplicationException.class, () -> buscarUsuarioUseCase.buscarPorId(null));
        verify(usuarioRepository, never()).findById(any());
    }
}