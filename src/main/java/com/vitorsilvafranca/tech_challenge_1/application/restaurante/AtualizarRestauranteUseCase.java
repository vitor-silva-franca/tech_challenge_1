package com.vitorsilvafranca.tech_challenge_1.application.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.RestauranteNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AtualizarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;

    public AtualizarRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante atualizar(Long id, Restaurante novosDados) {
        if (id == null) {
            throw new ApplicationException("ID do restaurante é obrigatório.");
        }
        if (novosDados.getNome() == null || novosDados.getEndereco() == null || novosDados.getTipoDeCozinha() == null || novosDados.getHorarioAbertura() == null || novosDados.getHorarioFechamento() == null || novosDados.getUsuarioId() == null) {
            throw new ApplicationException("Novos dados do restaurante são obrigatórios.");
        }

        Restaurante existente = restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNotFoundException("Restaurante não encontrado."));

        existente.setNome(novosDados.getNome());
        existente.setEndereco(novosDados.getEndereco());
        existente.setTipoDeCozinha(novosDados.getTipoDeCozinha());
        existente.setHorarioAbertura(novosDados.getHorarioAbertura());
        existente.setHorarioFechamento(novosDados.getHorarioFechamento());
        existente.setUsuarioId(novosDados.getUsuarioId());

        return restauranteRepository.save(existente);
    }
}