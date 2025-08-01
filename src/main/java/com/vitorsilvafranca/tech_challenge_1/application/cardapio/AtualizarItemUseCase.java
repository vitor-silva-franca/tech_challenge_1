package com.vitorsilvafranca.tech_challenge_1.application.cardapio;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.ItemRepository;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemRequest;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.ItemNotFoundException;
import com.vitorsilvafranca.tech_challenge_1.shared.RestauranteNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AtualizarItemUseCase {

    private final ItemRepository itemRepository;
    private final RestauranteRepository restauranteRepository;

    public AtualizarItemUseCase(ItemRepository itemRepository, RestauranteRepository restauranteRepository) {
        this.itemRepository = itemRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Item atualizar(Long id, ItemRequest request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item não encontrado"));

        Restaurante restaurante = restauranteRepository.findById(request.getRestauranteId())
                .orElseThrow(() -> new RestauranteNotFoundException("Restaurante não encontrado"));

        if (request.getPreco() != null && request.getPreco().compareTo(BigDecimal.ZERO) == 0) {
            throw new ApplicationException("Preço do item não pode ser zero");
        }

        item.setNome(request.getNome());
        item.setDescricao(request.getDescricao());
        item.setPreco(request.getPreco());
        item.setDelivery(request.getDelivery());
        item.setFoto(request.getFoto());
        item.setRestaurante(restaurante);

        return itemRepository.save(item);
    }
}