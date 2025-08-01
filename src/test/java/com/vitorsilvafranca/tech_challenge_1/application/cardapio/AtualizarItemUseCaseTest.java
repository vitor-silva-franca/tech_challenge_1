package com.vitorsilvafranca.tech_challenge_1.application.cardapio;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.ItemRepository;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemRequest;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.ItemNotFoundException;
import com.vitorsilvafranca.tech_challenge_1.shared.RestauranteNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarItemUseCaseTest {

    private ItemRepository itemRepository;
    private RestauranteRepository restauranteRepository;
    private AtualizarItemUseCase atualizarItemUseCase;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        restauranteRepository = mock(RestauranteRepository.class);
        atualizarItemUseCase = new AtualizarItemUseCase(itemRepository, restauranteRepository);
    }

    @Test
    void deveAtualizarItemComSucesso() {
        Long itemId = 1L;
        Long restauranteId = 2L;
        Item item = new Item();
        item.setId(itemId);
        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);

        ItemRequest request = new ItemRequest();
        request.setNome("Novo Nome");
        request.setDescricao("Nova Desc");
        request.setPreco(new BigDecimal("10.00"));
        request.setDelivery(true);
        request.setFoto("foto.jpg");
        request.setRestauranteId(restauranteId);

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));
        when(itemRepository.save(any(Item.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Item atualizado = atualizarItemUseCase.atualizar(itemId, request);

        assertEquals("Novo Nome", atualizado.getNome());
        assertEquals("Nova Desc", atualizado.getDescricao());
        assertEquals(new BigDecimal("10.00"), atualizado.getPreco());
        assertTrue(atualizado.getDelivery());
        assertEquals("foto.jpg", atualizado.getFoto());
        assertEquals(restaurante, atualizado.getRestaurante());
        verify(itemRepository).save(item);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoEncontrado() {
        Long itemId = 1L;
        ItemRequest request = new ItemRequest();
        request.setRestauranteId(2L);

        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> atualizarItemUseCase.atualizar(itemId, request));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long itemId = 1L;
        Long restauranteId = 2L;
        Item item = new Item();
        item.setId(itemId);

        ItemRequest request = new ItemRequest();
        request.setRestauranteId(restauranteId);

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.empty());

        assertThrows(RestauranteNotFoundException.class, () -> atualizarItemUseCase.atualizar(itemId, request));
    }

    @Test
    void deveLancarExcecaoQuandoPrecoZero() {
        Long itemId = 1L;
        Long restauranteId = 2L;
        Item item = new Item();
        item.setId(itemId);
        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);

        ItemRequest request = new ItemRequest();
        request.setRestauranteId(restauranteId);
        request.setPreco(BigDecimal.ZERO);

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));

        assertThrows(ApplicationException.class, () -> atualizarItemUseCase.atualizar(itemId, request));
    }
}

