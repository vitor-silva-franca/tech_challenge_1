package com.vitorsilvafranca.tech_challenge_1.interfaces.mapper;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

    private Restaurante restaurante;
    private ItemRequest itemRequest;
    private Item item;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Restaurante Teste", "Rua A, 123",
                TipoDeCozinha.BRASILEIRA, LocalTime.of(8, 0), LocalTime.of(22, 0), 1L);
        restaurante.setId(1L);

        itemRequest = new ItemRequest();
        itemRequest.setNome("Pizza");
        itemRequest.setDescricao("Pizza deliciosa");
        itemRequest.setPreco(new BigDecimal("25.90"));
        itemRequest.setDelivery(true);
        itemRequest.setFoto("foto.jpg");
        itemRequest.setRestauranteId(1L);

        item = new Item("Pizza", "Pizza deliciosa", new BigDecimal("25.90"), 
                true, "foto.jpg", restaurante);
    }

    @Test
    void deveConverterItemRequestParaModel() {
        Item resultado = ItemMapper.toModel(itemRequest, restaurante);

        assertEquals("Pizza", resultado.getNome());
        assertEquals("Pizza deliciosa", resultado.getDescricao());
        assertEquals(new BigDecimal("25.90"), resultado.getPreco());
        assertTrue(resultado.getDelivery());
        assertEquals("foto.jpg", resultado.getFoto());
        assertEquals(restaurante, resultado.getRestaurante());
    }

    @Test
    void deveConverterItemParaResponse() {
        ItemResponse resultado = ItemMapper.fromModel(item);

        assertEquals("Pizza", resultado.getNome());
        assertEquals("Pizza deliciosa", resultado.getDescricao());
        assertEquals(new BigDecimal("25.90"), resultado.getPreco());
        assertTrue(resultado.getDelivery());
        assertEquals("foto.jpg", resultado.getFoto());
        assertEquals(1L, resultado.getRestauranteId());
    }

    @Test
    void deveConverterComFotoNula() {
        itemRequest.setFoto(null);
        Item resultado = ItemMapper.toModel(itemRequest, restaurante);
        assertNull(resultado.getFoto());
    }
}