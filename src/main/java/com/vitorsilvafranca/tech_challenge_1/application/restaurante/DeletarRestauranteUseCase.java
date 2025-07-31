package com.vitorsilvafranca.tech_challenge_1.application.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.RestauranteNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeletarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;

    public DeletarRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new ApplicationException("ID do restaurante é obrigatório.");
        }
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNotFoundException(id.toString()));

        restauranteRepository.deleteById(restaurante.getId());
    }
}