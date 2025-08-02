package com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import com.vitorsilvafranca.tech_challenge_1.shared.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Restaurante Teste", "Rua A, 123",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L);
    }

    @Test
    void deveCriarItemComSucesso() {
        Item item = new Item("Pizza Margherita", "Pizza com molho de tomate e queijo",
                new BigDecimal("25.90"), true, "foto.jpg", restaurante);

        assertEquals("Pizza Margherita", item.getNome());
        assertEquals("Pizza com molho de tomate e queijo", item.getDescricao());
        assertEquals(new BigDecimal("25.90"), item.getPreco());
        assertTrue(item.getDelivery());
        assertEquals("foto.jpg", item.getFoto());
        assertEquals(restaurante, item.getRestaurante());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        assertThrows(DomainException.class, () -> 
            new Item(null, "Descrição", new BigDecimal("25.90"), true, "foto.jpg", restaurante));
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        assertThrows(DomainException.class, () -> 
            new Item("", "Descrição", new BigDecimal("25.90"), true, "foto.jpg", restaurante));
    }

    @Test
    void deveLancarExcecaoQuandoNomeForMuitoCurto() {
        assertThrows(DomainException.class, () -> 
            new Item("A", "Descrição", new BigDecimal("25.90"), true, "foto.jpg", restaurante));
    }

    @Test
    void deveLancarExcecaoQuandoDescricaoForNula() {
        assertThrows(DomainException.class, () -> 
            new Item("Pizza", null, new BigDecimal("25.90"), true, "foto.jpg", restaurante));
    }

    @Test
    void deveLancarExcecaoQuandoDescricaoForVazia() {
        assertThrows(DomainException.class, () -> 
            new Item("Pizza", "", new BigDecimal("25.90"), true, "foto.jpg", restaurante));
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForNulo() {
        assertThrows(DomainException.class, () -> 
            new Item("Pizza", "Descrição", null, true, "foto.jpg", restaurante));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteForNulo() {
        assertThrows(DomainException.class, () -> 
            new Item("Pizza", "Descrição", new BigDecimal("25.90"), true, "foto.jpg", null));
    }

    @Test
    void devePermitirDeliveryFalse() {
        Item item = new Item("Pizza", "Descrição", new BigDecimal("25.90"), false, "foto.jpg", restaurante);
        assertFalse(item.getDelivery());
    }

    @Test
    void devePermitirFotoNula() {
        Item item = new Item("Pizza", "Descrição", new BigDecimal("25.90"), true, null, restaurante);
        assertNull(item.getFoto());
    }
}