package com.vitorsilvafranca.tech_challenge_1.application.cardapio;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.ItemRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarItemUseCaseTest {

    private ItemRepository itemRepository;
    private BuscarItemUseCase buscarItemUseCase;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        buscarItemUseCase = new BuscarItemUseCase(itemRepository);
    }

    @Test
    void deveBuscarItemPorIdComSucesso() {
        Long id = 1L;
        Item item = new Item();
        item.setId(id);

        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        Item resultado = buscarItemUseCase.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(itemRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoEncontrado() {
        Long id = 2L;
        when(itemRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> buscarItemUseCase.buscarPorId(id));
        verify(itemRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        assertThrows(ApplicationException.class, () -> buscarItemUseCase.buscarPorId(null));
        verify(itemRepository, never()).findById(any());
    }
}

