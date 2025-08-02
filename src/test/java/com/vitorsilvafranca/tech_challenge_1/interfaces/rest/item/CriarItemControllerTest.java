package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.item;

import com.vitorsilvafranca.tech_challenge_1.application.cardapio.CriarItemUseCase;
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
class CriarItemControllerTest {

    @Mock
    private CriarItemUseCase criarItemUseCase;

    @InjectMocks
    private CriarItemController controller;

    @Test
    void deveCriarItemComSucesso() {
        ItemRequest request = new ItemRequest();
        request.setNome("Pizza");
        request.setDescricao("Deliciosa");
        request.setPreco(new BigDecimal("25.90"));
        request.setDelivery(true);
        request.setRestauranteId(1L);

        Restaurante restaurante = new Restaurante("Restaurante", "Endereço",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L);
        Item item = new Item("Pizza", "Deliciosa", new BigDecimal("25.90"), true, null, restaurante);

        when(criarItemUseCase.criar(request)).thenReturn(item);

        ResponseEntity<ItemResponse> response = controller.criar(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Pizza", response.getBody().getNome());
        verify(criarItemUseCase).criar(request);
    }
}