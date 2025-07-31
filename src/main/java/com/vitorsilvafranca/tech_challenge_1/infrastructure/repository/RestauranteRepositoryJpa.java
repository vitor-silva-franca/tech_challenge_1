package com.vitorsilvafranca.tech_challenge_1.infrastructure.repository;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RestauranteRepositoryJpa extends RestauranteRepository, JpaRepository<Restaurante, Long> {

    public Optional<Restaurante> findById(Long id);

    public Restaurante save(Restaurante restaurante);

    public void deleteById(Long id);
}
