package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.restaurante;

import com.vitorsilvafranca.tech_challenge_1.application.restaurante.AtualizarRestauranteUseCase;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarRestauranteControllerTest {

    @Mock
    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @InjectMocks
    private AtualizarRestauranteController controller;

    @Test
    void deveAtualizarRestauranteComSucesso() {
        Long id = 1L;
        RestauranteRequest request = new RestauranteRequest();
        request.setNome("Restaurante Atualizado");
        request.setEndereco("Nova Rua, 456");
        request.setTipoDeCozinha(TipoDeCozinha.ESTRANGEIRA);
        request.setHorarioAbertura(LocalTime.of(9, 0));
        request.setHorarioFechamento(LocalTime.of(23, 0));
        request.setUsuarioId(1L);

        Restaurante restauranteAtualizado = new Restaurante("Restaurante Atualizado", "Nova Rua, 456",
                TipoDeCozinha.ESTRANGEIRA, LocalTime.of(9, 0), LocalTime.of(23, 0), 1L);

        when(atualizarRestauranteUseCase.atualizar(eq(id), any(Restaurante.class))).thenReturn(restauranteAtualizado);

        ResponseEntity<RestauranteResponse> response = controller.atualizar(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Restaurante Atualizado", response.getBody().getNome());
        assertEquals("Nova Rua, 456", response.getBody().getEndereco());
        assertEquals(TipoDeCozinha.ESTRANGEIRA, response.getBody().getTipoDeCozinha());
        verify(atualizarRestauranteUseCase).atualizar(eq(id), any(Restaurante.class));
    }
}