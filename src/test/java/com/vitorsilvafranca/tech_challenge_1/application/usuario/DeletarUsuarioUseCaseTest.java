package com.vitorsilvafranca.tech_challenge_1.application.usuario;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private DeletarUsuarioUseCase deletarUsuarioUseCase;

    @Test
    void deveDeletarUsuarioComSucesso() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        assertDoesNotThrow(() -> deletarUsuarioUseCase.deletar(id));

        verify(usuarioRepository).findById(id);
        verify(usuarioRepository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        assertThrows(ApplicationException.class, () -> deletarUsuarioUseCase.deletar(null));
        verify(usuarioRepository, never()).findById(any());
        verify(usuarioRepository, never()).deleteById(any());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long id = 999L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> deletarUsuarioUseCase.deletar(id));
        verify(usuarioRepository).findById(id);
        verify(usuarioRepository, never()).deleteById(any());
    }
}