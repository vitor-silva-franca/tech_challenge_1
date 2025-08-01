package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.AtualizarUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.UsuarioRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.UsuarioResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/atualizarUsuario")
@Tag(name = "Atualizar Usuário", description = "Atualização de usuários")
public class AtualizarUsuarioController {

    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    public AtualizarUsuarioController(AtualizarUsuarioUseCase atualizarUsuarioUseCase) {
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable(required = true) Long id, @Valid @RequestBody UsuarioRequest request) {
        Usuario atualizado = atualizarUsuarioUseCase.atualizar(id, UsuarioMapper.toModel(request));
        return ResponseEntity.ok(UsuarioMapper.fromModel(atualizado));
    }
}