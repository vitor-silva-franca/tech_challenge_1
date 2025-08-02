package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.restaurante;

import com.vitorsilvafranca.tech_challenge_1.application.restaurante.DeletarRestauranteUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarRestauranteControllerTest {

    @Mock
    private DeletarRestauranteUseCase deletarRestauranteUseCase;

    @InjectMocks
    private DeletarRestauranteController controller;

    @Test
    void deveDeletarRestauranteComSucesso() {
        Long id = 1L;

        doNothing().when(deletarRestauranteUseCase).deletar(id);

        ResponseEntity<Void> response = controller.deletar(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(deletarRestauranteUseCase).deletar(id);
    }
}