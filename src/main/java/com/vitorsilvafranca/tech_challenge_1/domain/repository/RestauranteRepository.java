package com.vitorsilvafranca.tech_challenge_1.domain.repository;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import java.util.Optional;

public interface RestauranteRepository {

    Optional<Restaurante> findById(Long id);

    Restaurante save(Restaurante restaurante);

    void deleteById(Long id);
}