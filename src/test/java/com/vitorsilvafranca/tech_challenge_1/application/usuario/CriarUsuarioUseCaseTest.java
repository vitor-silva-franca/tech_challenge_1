package com.vitorsilvafranca.tech_challenge_1.application.usuario;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.RoleUsuario;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioJaExisteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @Test
    void deveCriarUsuarioComSucesso() {
        Usuario usuario = new Usuario("João", "joao@email.com", "joao123", "senha123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE);

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(usuarioRepository.existsByLogin(usuario.getLogin())).thenReturn(false);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = criarUsuarioUseCase.criar(usuario);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        assertNotNull(resultado.getDataAlteracao());
        verify(usuarioRepository).existsByEmail(usuario.getEmail());
        verify(usuarioRepository).existsByLogin(usuario.getLogin());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        Usuario usuario = new Usuario("João", "joao@email.com", "joao123", "senha123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE);

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);

        assertThrows(UsuarioJaExisteException.class, () -> criarUsuarioUseCase.criar(usuario));
        verify(usuarioRepository).existsByEmail(usuario.getEmail());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoLoginJaExiste() {
        Usuario usuario = new Usuario("João", "joao@email.com", "joao123", "senha123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE);

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(usuarioRepository.existsByLogin(usuario.getLogin())).thenReturn(true);

        assertThrows(UsuarioJaExisteException.class, () -> criarUsuarioUseCase.criar(usuario));
        verify(usuarioRepository).existsByEmail(usuario.getEmail());
        verify(usuarioRepository).existsByLogin(usuario.getLogin());
        verify(usuarioRepository, never()).save(any());
    }
}