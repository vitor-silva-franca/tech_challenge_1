package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.item;

import com.vitorsilvafranca.tech_challenge_1.application.cardapio.AtualizarItemUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.ItemMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/atualizarItem")
@Tag(name = "Atualizar Item", description = "Atualização de itens do cardápio")
public class AtualizarItemController {

    private final AtualizarItemUseCase atualizarItemUseCase;

    public AtualizarItemController(AtualizarItemUseCase atualizarItemUseCase) {
        this.atualizarItemUseCase = atualizarItemUseCase;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ItemRequest request) {
        try {
            Item atualizado = atualizarItemUseCase.atualizar(id, request);
            return ResponseEntity.ok(ItemMapper.fromModel(atualizado));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar item: " + e.getMessage());
        }
    }
}