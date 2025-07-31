package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.restaurante;

import com.vitorsilvafranca.tech_challenge_1.application.restaurante.AtualizarRestauranteUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.RestauranteMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/atualizarRestaurante")
@Tag(name = "Atualizar Restaurante", description = "Atualização de restaurantes")
public class AtualizarRestauranteController {

    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    public AtualizarRestauranteController(AtualizarRestauranteUseCase atualizarRestauranteUseCase) {
        this.atualizarRestauranteUseCase = atualizarRestauranteUseCase;
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponse> atualizar(@PathVariable(required = true) Long id, @Valid @RequestBody RestauranteRequest request) {
        try {
            Restaurante atualizado = atualizarRestauranteUseCase.atualizar(id, RestauranteMapper.toModel(request));
            return ResponseEntity.ok(RestauranteMapper.fromModel(atualizado));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurante não encontrado: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar restaurante: " + e.getMessage());
        }
    }
}