package com.vitorsilvafranca.tech_challenge_1.domain.repository;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestauranteRepositoryTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        restaurante = new Restaurante("Restaurante Teste", "Rua A, 123",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L);
        restaurante.setId(1L);
    }

    @Test
    void deveBuscarRestaurantePorId() {
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restaurante));

        Optional<Restaurante> resultado = restauranteRepository.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(restaurante, resultado.get());
        verify(restauranteRepository).findById(1L);
    }

    @Test
    void deveRetornarVazioQuandoRestauranteNaoEncontrado() {
        when(restauranteRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Restaurante> resultado = restauranteRepository.findById(999L);

        assertFalse(resultado.isPresent());
        verify(restauranteRepository).findById(999L);
    }

    @Test
    void deveSalvarRestaurante() {
        when(restauranteRepository.save(restaurante)).thenReturn(restaurante);

        Restaurante resultado = restauranteRepository.save(restaurante);

        assertEquals(restaurante, resultado);
        verify(restauranteRepository).save(restaurante);
    }

    @Test
    void deveDeletarRestaurantePorId() {
        doNothing().when(restauranteRepository).deleteById(1L);

        assertDoesNotThrow(() -> restauranteRepository.deleteById(1L));
        verify(restauranteRepository).deleteById(1L);
    }
}