package com.vitorsilvafranca.tech_challenge_1.domain.repository;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import java.util.Optional;

public interface ItemRepository {

    public Optional<Item> findById(Long id);

    public Item save(Item item);

    public void deleteById(Long id);
}