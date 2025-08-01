package com.vitorsilvafranca.tech_challenge_1.application.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.RestauranteRepository;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.RestauranteNotFoundException;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AtualizarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;

    public AtualizarRestauranteUseCase(RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Restaurante atualizar(Long id, Restaurante novosDados) {
        validarId(id);
        validarNovosDados(novosDados);
        validarUsuario(novosDados.getUsuarioId());

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

    private void validarId(Long id) {
        if (id == null) {
            throw new ApplicationException("ID do restaurante é obrigatório.");
        }
    }

    private void validarNovosDados(Restaurante novosDados) {
        if (novosDados.getNome() == null || novosDados.getEndereco() == null ||
                novosDados.getTipoDeCozinha() == null || novosDados.getHorarioAbertura() == null ||
                novosDados.getHorarioFechamento() == null || novosDados.getUsuarioId() == null) {
            throw new ApplicationException("Novos dados do restaurante são obrigatórios.");
        }
    }

    private void validarUsuario(Long usuarioId) {
        if (usuarioRepository.findById(usuarioId).isEmpty()) {
            throw new UsuarioNotFoundException("Usuário não encontrado.");
        }
    }
}