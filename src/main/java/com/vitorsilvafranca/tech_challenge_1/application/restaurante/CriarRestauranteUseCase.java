package com.vitorsilvafranca.tech_challenge_1.application.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;

    public CriarRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante criar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }
}