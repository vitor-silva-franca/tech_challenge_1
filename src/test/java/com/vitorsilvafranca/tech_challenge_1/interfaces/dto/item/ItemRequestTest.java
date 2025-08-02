package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemRequestTest {

    @Test
    void deveDefinirEObterTodosOsCampos() {
        ItemRequest request = new ItemRequest();
        
        request.setNome("Pizza Margherita");
        request.setDescricao("Pizza com molho de tomate e queijo");
        request.setPreco(new BigDecimal("25.90"));
        request.setDelivery(true);
        request.setFoto("foto.jpg");
        request.setRestauranteId(1L);

        assertEquals("Pizza Margherita", request.getNome());
        assertEquals("Pizza com molho de tomate e queijo", request.getDescricao());
        assertEquals(new BigDecimal("25.90"), request.getPreco());
        assertTrue(request.getDelivery());
        assertEquals("foto.jpg", request.getFoto());
        assertEquals(1L, request.getRestauranteId());
    }

    @Test
    void devePermitirFotoNula() {
        ItemRequest request = new ItemRequest();
        request.setFoto(null);
        assertNull(request.getFoto());
    }

    @Test
    void devePermitirDeliveryFalse() {
        ItemRequest request = new ItemRequest();
        request.setDelivery(false);
        assertFalse(request.getDelivery());
    }
}