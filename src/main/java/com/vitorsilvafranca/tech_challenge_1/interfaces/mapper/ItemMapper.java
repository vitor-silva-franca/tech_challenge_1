package com.vitorsilvafranca.tech_challenge_1.interfaces.mapper;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemResponse;

public class ItemMapper {

    public static Item toModel(ItemRequest request, Restaurante restaurante) {
        return new Item(
                request.getNome(),
                request.getDescricao(),
                request.getPreco(),
                request.getDelivery(),
                request.getFoto(),
                restaurante
        );
    }

    public static ItemResponse fromModel(Item item) {
        return new ItemResponse(
                item.getNome(),
                item.getDescricao(),
                item.getPreco(),
                item.getDelivery(),
                item.getFoto(),
                item.getRestaurante().getId()
        );
    }
}