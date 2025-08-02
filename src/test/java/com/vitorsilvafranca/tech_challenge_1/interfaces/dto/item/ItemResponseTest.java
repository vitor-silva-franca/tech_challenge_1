package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemResponseTest {

    @Test
    void deveCriarItemResponseComConstrutorCompleto() {
        ItemResponse response = new ItemResponse("Pizza", "Deliciosa pizza", 
                new BigDecimal("30.00"), true, "foto.jpg", 1L);

        assertEquals("Pizza", response.getNome());
        assertEquals("Deliciosa pizza", response.getDescricao());
        assertEquals(new BigDecimal("30.00"), response.getPreco());
        assertTrue(response.getDelivery());
        assertEquals("foto.jpg", response.getFoto());
        assertEquals(1L, response.getRestauranteId());
    }

    @Test
    void devePermitirAlterarValoresComSetters() {
        ItemResponse response = new ItemResponse("Pizza", "Descrição", 
                new BigDecimal("25.00"), false, null, 1L);

        response.setNome("Hambúrguer");
        response.setDescricao("Nova descrição");
        response.setPreco(new BigDecimal("35.00"));
        response.setDelivery(true);
        response.setFoto("nova_foto.jpg");
        response.setRestauranteId(2L);

        assertEquals("Hambúrguer", response.getNome());
        assertEquals("Nova descrição", response.getDescricao());
        assertEquals(new BigDecimal("35.00"), response.getPreco());
        assertTrue(response.getDelivery());
        assertEquals("nova_foto.jpg", response.getFoto());
        assertEquals(2L, response.getRestauranteId());
    }
}