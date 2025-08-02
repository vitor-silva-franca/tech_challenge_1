package com.vitorsilvafranca.tech_challenge_1.domain.repository;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemRepositoryTest {

    @Mock
    private ItemRepository itemRepository;

    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        Restaurante restaurante = new Restaurante("Restaurante Teste", "Rua A, 123",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L);
        
        item = new Item("Pizza", "Pizza deliciosa", new BigDecimal("25.90"), 
                true, "foto.jpg", restaurante);
        item.setId(1L);
    }

    @Test
    void deveBuscarItemPorId() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Optional<Item> resultado = itemRepository.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(item, resultado.get());
        verify(itemRepository).findById(1L);
    }

    @Test
    void deveRetornarVazioQuandoItemNaoEncontrado() {
        when(itemRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Item> resultado = itemRepository.findById(999L);

        assertFalse(resultado.isPresent());
        verify(itemRepository).findById(999L);
    }

    @Test
    void deveSalvarItem() {
        when(itemRepository.save(item)).thenReturn(item);

        Item resultado = itemRepository.save(item);

        assertEquals(item, resultado);
        verify(itemRepository).save(item);
    }

    @Test
    void deveDeletarItemPorId() {
        doNothing().when(itemRepository).deleteById(1L);

        assertDoesNotThrow(() -> itemRepository.deleteById(1L));
        verify(itemRepository).deleteById(1L);
    }
}