package com.vitorsilvafranca.tech_challenge_1.application.cardapio;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.ItemRepository;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.ItemMapper;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.RestauranteNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CriarItemUseCase {

    private final ItemRepository itemRepository;
    private final RestauranteRepository restauranteRepository;

    public CriarItemUseCase(ItemRepository itemRepository, RestauranteRepository restauranteRepository) {
        this.itemRepository = itemRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Item criar(ItemRequest request) {
        Restaurante restaurante = restauranteRepository.findById(request.getRestauranteId())
                .orElseThrow(() -> new RestauranteNotFoundException("Restaurante não encontrado"));
        if (request.getPreco() != null && request.getPreco().compareTo(BigDecimal.ZERO) == 0) {
            throw new ApplicationException("Preço do item não pode ser zero");
        }
        Item item = ItemMapper.toModel(request, restaurante);
        return itemRepository.save(item);
    }
}