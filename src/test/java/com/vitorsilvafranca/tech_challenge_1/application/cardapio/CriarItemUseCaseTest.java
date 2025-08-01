package com.vitorsilvafranca.tech_challenge_1.application.cardapio;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.ItemRepository;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemRequest;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.RestauranteNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarItemUseCaseTest {

    private ItemRepository itemRepository;
    private RestauranteRepository restauranteRepository;
    private CriarItemUseCase criarItemUseCase;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        restauranteRepository = mock(RestauranteRepository.class);
        criarItemUseCase = new CriarItemUseCase(itemRepository, restauranteRepository);
    }

    @Test
    void deveCriarItemComSucesso() {
        Long restauranteId = 1L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);

        ItemRequest request = new ItemRequest();
        request.setNome("Item Teste");
        request.setDescricao("Desc");
        request.setPreco(new BigDecimal("15.00"));
        request.setDelivery(true);
        request.setFoto("foto.jpg");
        request.setRestauranteId(restauranteId);

        Item itemSalvo = new Item();
        itemSalvo.setId(10L);

        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));
        when(itemRepository.save(any(Item.class))).thenReturn(itemSalvo);

        Item resultado = criarItemUseCase.criar(request);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        verify(restauranteRepository).findById(restauranteId);
        verify(itemRepository).save(any(Item.class));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long restauranteId = 2L;
        ItemRequest request = new ItemRequest();
        request.setRestauranteId(restauranteId);

        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.empty());

        assertThrows(RestauranteNotFoundException.class, () -> criarItemUseCase.criar(request));
        verify(restauranteRepository).findById(restauranteId);
        verify(itemRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoPrecoZero() {
        Long restauranteId = 3L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);

        ItemRequest request = new ItemRequest();
        request.setRestauranteId(restauranteId);
        request.setPreco(BigDecimal.ZERO);

        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));

        assertThrows(ApplicationException.class, () -> criarItemUseCase.criar(request));
        verify(restauranteRepository).findById(restauranteId);
        verify(itemRepository, never()).save(any());
    }
}

