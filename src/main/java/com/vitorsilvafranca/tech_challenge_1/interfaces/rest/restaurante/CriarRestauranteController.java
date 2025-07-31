package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.restaurante;

import com.vitorsilvafranca.tech_challenge_1.application.restaurante.CriarRestauranteUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.RestauranteMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/criarRestaurante")
@Tag(name = "Criar Restaurante", description = "Endpoint para criação de restaurantes")
public class CriarRestauranteController {

    private final CriarRestauranteUseCase criarRestauranteUseCase;

    public CriarRestauranteController(CriarRestauranteUseCase criarRestauranteUseCase) {
        this.criarRestauranteUseCase = criarRestauranteUseCase;
    }

    @PostMapping
    public ResponseEntity<RestauranteResponse> criar(@Valid @RequestBody RestauranteRequest request) {
        try {
            Restaurante restaurante = RestauranteMapper.toModel(request);
            Restaurante restauranteCriado = criarRestauranteUseCase.criar(restaurante);
            RestauranteResponse response = RestauranteMapper.fromModel(restauranteCriado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar restaurante: " + e.getMessage());
        }
    }
}