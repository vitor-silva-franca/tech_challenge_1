package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.restaurante;

import com.vitorsilvafranca.tech_challenge_1.application.restaurante.DeletarRestauranteUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/deletarRestaurante")
@Tag(name = "Deletar Restaurante pelo ID", description = "Remoção de restaurantes")
public class DeletarRestauranteController {

    private final DeletarRestauranteUseCase deletarRestauranteUseCase;

    public DeletarRestauranteController(DeletarRestauranteUseCase deletarRestauranteUseCase) {
        this.deletarRestauranteUseCase = deletarRestauranteUseCase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(required = true) Long id) {
        try {
            deletarRestauranteUseCase.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurante não encontrado", e);
        }
    }
}