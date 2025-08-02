package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.restaurante;

import com.vitorsilvafranca.tech_challenge_1.application.restaurante.BuscarRestauranteUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarRestauranteControllerTest {

    @Mock
    private BuscarRestauranteUseCase buscarRestauranteUseCase;

    @InjectMocks
    private BuscarRestauranteController controller;

    @Test
    void deveBuscarRestauranteComSucesso() {
        Long id = 1L;
        Restaurante restaurante = new Restaurante("Restaurante Teste", "Rua A, 123",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L);

        when(buscarRestauranteUseCase.buscarPorId(id)).thenReturn(restaurante);

        ResponseEntity<RestauranteResponse> response = controller.buscar(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Restaurante Teste", response.getBody().getNome());
        assertEquals("Rua A, 123", response.getBody().getEndereco());
        assertEquals(TipoDeCozinha.BRASILEIRA, response.getBody().getTipoDeCozinha());
        verify(buscarRestauranteUseCase).buscarPorId(id);
    }
}