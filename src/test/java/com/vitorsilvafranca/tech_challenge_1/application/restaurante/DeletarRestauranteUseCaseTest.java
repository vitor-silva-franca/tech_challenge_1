package com.vitorsilvafranca.tech_challenge_1.application.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.RestauranteNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarRestauranteUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private DeletarRestauranteUseCase deletarRestauranteUseCase;

    @Test
    void deveDeletarRestauranteComSucesso() {
        Long id = 1L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);

        when(restauranteRepository.findById(id)).thenReturn(Optional.of(restaurante));

        assertDoesNotThrow(() -> deletarRestauranteUseCase.deletar(id));

        verify(restauranteRepository).findById(id);
        verify(restauranteRepository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        assertThrows(ApplicationException.class, () -> deletarRestauranteUseCase.deletar(null));
        verify(restauranteRepository, never()).findById(any());
        verify(restauranteRepository, never()).deleteById(any());
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long id = 999L;
        when(restauranteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RestauranteNotFoundException.class, () -> deletarRestauranteUseCase.deletar(id));
        verify(restauranteRepository).findById(id);
        verify(restauranteRepository, never()).deleteById(any());
    }
}