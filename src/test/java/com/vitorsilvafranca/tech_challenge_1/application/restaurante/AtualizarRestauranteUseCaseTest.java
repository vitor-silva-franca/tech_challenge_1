package com.vitorsilvafranca.tech_challenge_1.application.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
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
class AtualizarRestauranteUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @Test
    void deveAtualizarRestauranteComSucesso() {
        Long id = 1L;
        Long usuarioId = 1L;

        Restaurante existente = new Restaurante();
        existente.setId(id);

        Restaurante novosDados = new Restaurante("Novo Nome", "Novo Endereço",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), usuarioId);

        Usuario usuario = new Usuario();

        when(restauranteRepository.findById(id)).thenReturn(Optional.of(existente));
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(restauranteRepository.save(existente)).thenReturn(existente);

        Restaurante resultado = atualizarRestauranteUseCase.atualizar(id, novosDados);

        assertEquals("Novo Nome", resultado.getNome());
        assertEquals("Novo Endereço", resultado.getEndereco());
        assertEquals(TipoDeCozinha.BRASILEIRA, resultado.getTipoDeCozinha());
        assertEquals(usuarioId, resultado.getUsuarioId());

        verify(restauranteRepository).findById(id);
        verify(usuarioRepository).findById(usuarioId);
        verify(restauranteRepository).save(existente);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNull() {
        Restaurante novosDados = new Restaurante();

        assertThrows(ApplicationException.class,
                () -> atualizarRestauranteUseCase.atualizar(null, novosDados));

        verifyNoInteractions(restauranteRepository, usuarioRepository);
    }

    @Test
    void deveLancarExcecaoQuandoNovosDadosForemIncompletos() {
        Long id = 1L;
        Restaurante novosDados = new Restaurante();
        novosDados.setNome(null);

        assertThrows(ApplicationException.class,
                () -> atualizarRestauranteUseCase.atualizar(id, novosDados));

        verifyNoInteractions(restauranteRepository, usuarioRepository);
    }
}
