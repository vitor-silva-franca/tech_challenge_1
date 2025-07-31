package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.restaurante;

import com.vitorsilvafranca.tech_challenge_1.application.restaurante.BuscarRestauranteUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante.RestauranteResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.RestauranteMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/buscarRestaurante")
@Tag(name = "Buscar Restaurante", description = "Endpoint para busca de restaurantes")
public class BuscarRestauranteController {

    private final BuscarRestauranteUseCase buscarRestauranteUseCase;

    public BuscarRestauranteController(BuscarRestauranteUseCase buscarRestauranteUseCase) {
        this.buscarRestauranteUseCase = buscarRestauranteUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponse> buscar(@PathVariable(required = true) Long id) {
        try {
            Restaurante restaurante = buscarRestauranteUseCase.buscarPorId(id);
            return ResponseEntity.ok(RestauranteMapper.fromModel(restaurante));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurante não encontrado: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar restaurante: " + e.getMessage());
        }
    }
}