package com.vitorsilvafranca.tech_challenge_1.application.usuario;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
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
class LoginUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private LoginUsuarioUseCase loginUsuarioUseCase;

    @Test
    void deveRealizarLoginComSucesso() {
        Usuario usuarioLogin = new Usuario("joao123", "senha123");
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setLogin("joao123");
        usuarioExistente.setSenha("senha123");

        when(usuarioRepository.existsByLogin("joao123")).thenReturn(true);
        when(usuarioRepository.findByLogin("joao123")).thenReturn(Optional.of(usuarioExistente));

        String resultado = loginUsuarioUseCase.login(usuarioLogin);

        assertEquals("Usuário logado com sucesso!", resultado);
        verify(usuarioRepository).existsByLogin("joao123");
        verify(usuarioRepository).findByLogin("joao123");
    }

    @Test
    void deveRetornarAcessoNegadoQuandoSenhaIncorreta() {
        Usuario usuarioLogin = new Usuario("joao123", "senhaErrada");
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setLogin("joao123");
        usuarioExistente.setSenha("senha123");

        when(usuarioRepository.existsByLogin("joao123")).thenReturn(true);
        when(usuarioRepository.findByLogin("joao123")).thenReturn(Optional.of(usuarioExistente));

        String resultado = loginUsuarioUseCase.login(usuarioLogin);

        assertEquals("Acesso negado!", resultado);
        verify(usuarioRepository).existsByLogin("joao123");
        verify(usuarioRepository).findByLogin("joao123");
    }

    @Test
    void deveRetornarAcessoNegadoQuandoUsuarioNaoExiste() {
        Usuario usuarioLogin = new Usuario("inexistente", "senha123");

        when(usuarioRepository.existsByLogin("inexistente")).thenReturn(false);

        String resultado = loginUsuarioUseCase.login(usuarioLogin);

        assertEquals("Acesso negado!", resultado);
        verify(usuarioRepository).existsByLogin("inexistente");
        verify(usuarioRepository, never()).findByLogin(any());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioExisteNaVerificacaoMasNaoEncontrado() {
        Usuario usuarioLogin = new Usuario("joao123", "senha123");

        when(usuarioRepository.existsByLogin("joao123")).thenReturn(true);
        when(usuarioRepository.findByLogin("joao123")).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> loginUsuarioUseCase.login(usuarioLogin));
        verify(usuarioRepository).existsByLogin("joao123");
        verify(usuarioRepository).findByLogin("joao123");
    }
}