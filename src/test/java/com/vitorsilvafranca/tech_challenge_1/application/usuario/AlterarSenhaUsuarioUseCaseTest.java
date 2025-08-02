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
class AlterarSenhaUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase;

    @Test
    void deveAlterarSenhaComSucesso() {
        Usuario usuarioComNovaSenha = new Usuario("joao@email.com", "joao123", "novaSenha123");
        
        Usuario existenteEmail = new Usuario();
        existenteEmail.setId(1L);
        existenteEmail.setEmail("joao@email.com");
        existenteEmail.setLogin("joao123");
        
        Usuario existenteLogin = new Usuario();
        existenteLogin.setEmail("joao@email.com");
        existenteLogin.setLogin("joao123");

        when(usuarioRepository.existsByLogin("joao123")).thenReturn(true);
        when(usuarioRepository.existsByEmail("joao@email.com")).thenReturn(true);
        when(usuarioRepository.findByEmail("joao@email.com")).thenReturn(Optional.of(existenteEmail));
        when(usuarioRepository.findByLogin("joao123")).thenReturn(Optional.of(existenteLogin));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existenteEmail));
        when(usuarioRepository.save(existenteEmail)).thenReturn(existenteEmail);

        String resultado = alterarSenhaUsuarioUseCase.alterar(usuarioComNovaSenha);

        assertEquals("Senha alterada com sucesso", resultado);
        assertEquals("novaSenha123", existenteEmail.getSenha());
        assertNotNull(existenteEmail.getDataAlteracao());
        verify(usuarioRepository).save(existenteEmail);
    }

    @Test
    void deveLancarExcecaoQuandoDadosObrigatoriosNulos() {
        Usuario usuarioIncompleto = new Usuario();
        usuarioIncompleto.setEmail(null);

        assertThrows(ApplicationException.class, () -> alterarSenhaUsuarioUseCase.alterar(usuarioIncompleto));
        verifyNoInteractions(usuarioRepository);
    }

    @Test
    void deveLancarExcecaoQuandoLoginNaoEncontrado() {
        Usuario usuarioComNovaSenha = new Usuario("joao@email.com", "loginInexistente", "novaSenha123");

        when(usuarioRepository.existsByLogin("loginInexistente")).thenReturn(false);

        assertThrows(UsuarioNotFoundException.class, () -> alterarSenhaUsuarioUseCase.alterar(usuarioComNovaSenha));
        verify(usuarioRepository).existsByLogin("loginInexistente");
        verify(usuarioRepository, never()).existsByEmail(any());
    }

    @Test
    void deveLancarExcecaoQuandoEmailNaoEncontrado() {
        Usuario usuarioComNovaSenha = new Usuario("emailInexistente@email.com", "joao123", "novaSenha123");

        when(usuarioRepository.existsByLogin("joao123")).thenReturn(true);
        when(usuarioRepository.existsByEmail("emailInexistente@email.com")).thenReturn(false);

        assertThrows(UsuarioNotFoundException.class, () -> alterarSenhaUsuarioUseCase.alterar(usuarioComNovaSenha));
        verify(usuarioRepository).existsByLogin("joao123");
        verify(usuarioRepository).existsByEmail("emailInexistente@email.com");
    }

    @Test
    void deveRetornarSolicitacaoInvalidaQuandoEmailELoginNaoPertencem() {
        Usuario usuarioComNovaSenha = new Usuario("joao@email.com", "maria123", "novaSenha123");
        
        Usuario existenteEmail = new Usuario();
        existenteEmail.setEmail("joao@email.com");
        existenteEmail.setLogin("joao123");
        
        Usuario existenteLogin = new Usuario();
        existenteLogin.setEmail("maria@email.com");
        existenteLogin.setLogin("maria123");

        when(usuarioRepository.existsByLogin("maria123")).thenReturn(true);
        when(usuarioRepository.existsByEmail("joao@email.com")).thenReturn(true);
        when(usuarioRepository.findByEmail("joao@email.com")).thenReturn(Optional.of(existenteEmail));
        when(usuarioRepository.findByLogin("maria123")).thenReturn(Optional.of(existenteLogin));

        String resultado = alterarSenhaUsuarioUseCase.alterar(usuarioComNovaSenha);

        assertEquals("Solicitação inválida", resultado);
        verify(usuarioRepository, never()).save(any());
    }
}