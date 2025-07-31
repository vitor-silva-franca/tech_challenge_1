package com.vitorsilvafranca.tech_challenge_1.application.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.RestauranteNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BuscarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;

    public BuscarRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante buscarPorId(Long id) {
        if (id == null) {
            throw new ApplicationException("ID do restaurante é obrigatório.");
        }
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNotFoundException(id.toString()));
    }
}