package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.DeletarUsuarioUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/deletarUsuario")
@Tag(name = "Deletar Usuário pelo ID", description = "Remoção de usuários")
public class DeletarUsuarioController {

    private final DeletarUsuarioUseCase deletarUsuarioUseCase;

    public DeletarUsuarioController(DeletarUsuarioUseCase deletarUsuarioUseCase) {
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(required = true) Long id) {
        deletarUsuarioUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }
}