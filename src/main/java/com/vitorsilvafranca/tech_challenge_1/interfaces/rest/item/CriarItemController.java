package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.item;

import com.vitorsilvafranca.tech_challenge_1.application.cardapio.CriarItemUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.ItemMapper;
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
@RequestMapping("/api/criarItem")
@Tag(name = "Criar Item", description = "Endpoint para criação de itens do cardápio")
public class CriarItemController {

    private final CriarItemUseCase criarItemUseCase;

    public CriarItemController(CriarItemUseCase criarItemUseCase) {
        this.criarItemUseCase = criarItemUseCase;
    }

    @PostMapping
    public ResponseEntity<ItemResponse> criar(@Valid @RequestBody ItemRequest request) {
        try {
            Item itemCriado = criarItemUseCase.criar(request);
            ItemResponse response = ItemMapper.fromModel(itemCriado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar item: " + e.getMessage());
        }
    }
}