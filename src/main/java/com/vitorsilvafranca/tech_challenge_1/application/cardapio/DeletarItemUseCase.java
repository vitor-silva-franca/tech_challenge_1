package com.vitorsilvafranca.tech_challenge_1.application.cardapio;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.ItemRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.ItemNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeletarItemUseCase {

    private final ItemRepository itemRepository;

    public DeletarItemUseCase(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new ApplicationException("ID do item é obrigatório.");
        }
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id.toString()));

        itemRepository.deleteById(id);
    }
}