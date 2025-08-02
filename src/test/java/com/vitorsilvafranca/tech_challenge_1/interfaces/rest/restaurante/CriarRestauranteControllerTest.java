package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.restaurante;

import com.vitorsilvafranca.tech_challenge_1.application.restaurante.CriarRestauranteUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteRequest;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarRestauranteControllerTest {

    @Mock
    private CriarRestauranteUseCase criarRestauranteUseCase;

    @InjectMocks
    private CriarRestauranteController controller;

    @Test
    void deveCriarRestauranteComSucesso() {
        RestauranteRequest request = new RestauranteRequest();
        request.setNome("Restaurante Teste");
        request.setEndereco("Rua A, 123");
        request.setTipoDeCozinha(TipoDeCozinha.BRASILEIRA);
        request.setHorarioAbertura(LocalTime.of(8, 0));
        request.setHorarioFechamento(LocalTime.of(22, 0));
        request.setUsuarioId(1L);

        Restaurante restauranteCriado = new Restaurante("Restaurante Teste", "Rua A, 123",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L);

        when(criarRestauranteUseCase.criar(any(Restaurante.class))).thenReturn(restauranteCriado);

        ResponseEntity<RestauranteResponse> response = controller.criar(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Restaurante Teste", response.getBody().getNome());
        assertEquals("Rua A, 123", response.getBody().getEndereco());
        assertEquals(TipoDeCozinha.BRASILEIRA, response.getBody().getTipoDeCozinha());
        verify(criarRestauranteUseCase).criar(any(Restaurante.class));
    }
}