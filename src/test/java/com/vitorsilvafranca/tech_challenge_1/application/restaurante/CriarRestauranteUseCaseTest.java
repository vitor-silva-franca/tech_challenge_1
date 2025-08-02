package com.vitorsilvafranca.tech_challenge_1.application.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarRestauranteUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CriarRestauranteUseCase criarRestauranteUseCase;

    @Test
    void deveCriarRestauranteComSucesso() {
        Long usuarioId = 1L;
        Restaurante restaurante = new Restaurante("Restaurante Teste", "Rua A, 123",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), usuarioId);
        
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(restauranteRepository.save(restaurante)).thenReturn(restaurante);

        Restaurante resultado = criarRestauranteUseCase.criar(restaurante);

        assertNotNull(resultado);
        assertEquals("Restaurante Teste", resultado.getNome());
        verify(usuarioRepository).findById(usuarioId);
        verify(restauranteRepository).save(restaurante);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long usuarioId = 999L;
        Restaurante restaurante = new Restaurante("Restaurante Teste", "Rua A, 123",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), usuarioId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> criarRestauranteUseCase.criar(restaurante));
        verify(usuarioRepository).findById(usuarioId);
        verify(restauranteRepository, never()).save(any());
    }
}