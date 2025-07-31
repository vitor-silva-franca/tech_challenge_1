package com.vitorsilvafranca.tech_challenge_1.interfaces.mapper;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteResponse;
import java.util.List;

public class RestauranteMapper {

    public static Restaurante toModel(RestauranteRequest request) {
        return new Restaurante(
                request.getNome(),
                request.getEndereco(),
                request.getTipoDeCozinha(),
                request.getHorarioAbertura(),
                request.getHorarioFechamento(),
                request.getUsuarioId()
        );
    }

    public static RestauranteResponse fromModel(Restaurante restaurante) {
        List<ItemResponse> itensResponse = restaurante.getItens().stream()
                .map(ItemMapper::fromModel)
                .toList();

        return new RestauranteResponse(
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getTipoDeCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                restaurante.getUsuarioId(),
                itensResponse
        );
    }
}