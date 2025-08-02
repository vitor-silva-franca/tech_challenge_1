package com.vitorsilvafranca.tech_challenge_1.application.usuario;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.RoleUsuario;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioJaExisteException;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @Test
    void deveAtualizarUsuarioComSucesso() {
        Long id = 1L;
        Usuario existente = new Usuario("João", "joao@email.com", "joao123", "senha123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE);
        existente.setId(id);

        Usuario novosDados = new Usuario("João Silva", "joao.silva@email.com", "joao.silva", "novaSenha123", 
                new Date(), "Rua B, 456", RoleUsuario.DONO);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(existente));
        when(usuarioRepository.existsByEmail("joao.silva@email.com")).thenReturn(false);
        when(usuarioRepository.existsByLogin("joao.silva")).thenReturn(false);
        when(usuarioRepository.save(existente)).thenReturn(existente);

        Usuario resultado = atualizarUsuarioUseCase.atualizar(id, novosDados);

        assertEquals("João Silva", resultado.getNome());
        assertEquals("joao.silva@email.com", resultado.getEmail());
        assertEquals("joao.silva", resultado.getLogin());
        assertEquals(RoleUsuario.DONO, resultado.getRole());
        assertNotNull(resultado.getDataAlteracao());
        verify(usuarioRepository).save(existente);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        Usuario novosDados = new Usuario();
        assertThrows(ApplicationException.class, () -> atualizarUsuarioUseCase.atualizar(null, novosDados));
        verifyNoInteractions(usuarioRepository);
    }

    @Test
    void deveLancarExcecaoQuandoDadosIncompletos() {
        Long id = 1L;
        Usuario novosDados = new Usuario();
        novosDados.setNome(null);

        assertThrows(ApplicationException.class, () -> atualizarUsuarioUseCase.atualizar(id, novosDados));
        verifyNoInteractions(usuarioRepository);
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        Long id = 1L;
        Usuario existente = new Usuario("João", "joao@email.com", "joao123", "senha123", 
                new Date(), "Rua A, 123", RoleUsuario.CLIENTE);
        existente.setId(id);

        Usuario novosDados = new Usuario("João Silva", "outro@email.com", "joao.silva", "novaSenha123", 
                new Date(), "Rua B, 456", RoleUsuario.DONO);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(existente));
        when(usuarioRepository.existsByEmail("outro@email.com")).thenReturn(true);

        assertThrows(UsuarioJaExisteException.class, () -> atualizarUsuarioUseCase.atualizar(id, novosDados));
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long id = 999L;
        Usuario novosDados = new Usuario("João Silva", "joao.silva@email.com", "joao.silva", "novaSenha123", 
                new Date(), "Rua B, 456", RoleUsuario.DONO);

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> atualizarUsuarioUseCase.atualizar(id, novosDados));
        verify(usuarioRepository, never()).save(any());
    }
}