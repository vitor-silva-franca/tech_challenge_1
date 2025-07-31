package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.item;

import com.vitorsilvafranca.tech_challenge_1.application.cardapio.DeletarItemUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/deletarItem")
@Tag(name = "Deletar Item pelo ID", description = "Remoção de itens do cardápio")
public class DeletarItemController {

    private final DeletarItemUseCase deletarItemUseCase;

    public DeletarItemController(DeletarItemUseCase deletarItemUseCase) {
        this.deletarItemUseCase = deletarItemUseCase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(required = true) Long id) {
        try {
            deletarItemUseCase.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao deletar item: " + e.getMessage());
        }
    }
}