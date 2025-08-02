package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.item;

import com.vitorsilvafranca.tech_challenge_1.application.cardapio.AtualizarItemUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarItemControllerTest {

    @Mock
    private AtualizarItemUseCase atualizarItemUseCase;

    @InjectMocks
    private AtualizarItemController controller;

    @Test
    void deveAtualizarItemComSucesso() {
        Long id = 1L;
        ItemRequest request = new ItemRequest();
        request.setNome("Pizza Atualizada");
        request.setDescricao("Nova descrição");
        request.setPreco(new BigDecimal("30.00"));

        Restaurante restaurante = new Restaurante("Restaurante", "Endereço",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L);
        restaurante.setId(1L);
        Item itemAtualizado = new Item("Pizza Atualizada", "Nova descrição", 
                new BigDecimal("30.00"), true, null, restaurante);

        when(atualizarItemUseCase.atualizar(id, request)).thenReturn(itemAtualizado);

        ResponseEntity<ItemResponse> response = controller.atualizar(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pizza Atualizada", response.getBody().getNome());
        assertEquals("Nova descrição", response.getBody().getDescricao());
        verify(atualizarItemUseCase).atualizar(id, request);
    }
}