package com.vitorsilvafranca.tech_challenge_1.infrastructure.repository;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.ItemRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ItemRepositoryJpa extends ItemRepository, JpaRepository<Item, Long> {

    public Optional<Item> findById(Long id);

    public Item save(Item item);

    public void deleteById(Long id);
}