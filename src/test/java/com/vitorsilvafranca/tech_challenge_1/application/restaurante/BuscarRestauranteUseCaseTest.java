package com.vitorsilvafranca.tech_challenge_1.application.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.RestauranteNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarRestauranteUseCaseTest {

    private RestauranteRepository restauranteRepository;
    private BuscarRestauranteUseCase buscarRestauranteUseCase;

    @BeforeEach
    void setUp() {
        restauranteRepository = mock(RestauranteRepository.class);
        buscarRestauranteUseCase = new BuscarRestauranteUseCase(restauranteRepository);
    }

    @Test
    void deveBuscarRestaurantePorIdComSucesso() {
        Long id = 1L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);

        when(restauranteRepository.findById(id)).thenReturn(Optional.of(restaurante));

        Restaurante resultado = buscarRestauranteUseCase.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(restauranteRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long id = 2L;
        when(restauranteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RestauranteNotFoundException.class, () -> buscarRestauranteUseCase.buscarPorId(id));
        verify(restauranteRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        assertThrows(ApplicationException.class, () -> buscarRestauranteUseCase.buscarPorId(null));
        verify(restauranteRepository, never()).findById(any());
    }
}