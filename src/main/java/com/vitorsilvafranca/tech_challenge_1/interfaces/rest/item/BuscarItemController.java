package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.item;

import com.vitorsilvafranca.tech_challenge_1.application.cardapio.BuscarItemUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.ItemMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/buscarItem")
@Tag(name = "Buscar Item", description = "Endpoint para busca de itens do cardápio")
public class BuscarItemController {

    private final BuscarItemUseCase buscarItemUseCase;

    public BuscarItemController(BuscarItemUseCase buscarItemUseCase) {
        this.buscarItemUseCase = buscarItemUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> buscar(@PathVariable(required = true) Long id) {
        try {
            Item item = buscarItemUseCase.buscarPorId(id);
            return ResponseEntity.ok(ItemMapper.fromModel(item));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar item: " + e.getMessage());
        }
    }
}