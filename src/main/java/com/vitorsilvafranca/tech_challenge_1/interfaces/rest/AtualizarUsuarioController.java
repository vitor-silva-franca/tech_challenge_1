package com.vitorsilvafranca.tech_challenge_1.interfaces.rest;

import com.vitorsilvafranca.tech_challenge_1.application.AtualizarUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.UsuarioRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.UsuarioResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Atualizar Usuário", description = "Atualização de usuários")
@RestController
@RequestMapping("/api/atualizarUsuario")
public class AtualizarUsuarioController {

    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    public AtualizarUsuarioController(AtualizarUsuarioUseCase atualizarUsuarioUseCase) {
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
    }

    /**
     * Atualiza um usuário pelo ID
     *
     * @param id
     * @param request
     * @return
     */
    @Operation(summary = "Atualiza um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable(required = true) Long id, @Valid @RequestBody UsuarioRequest request) {
        Usuario atualizado = atualizarUsuarioUseCase.atualizar(id, UsuarioMapper.toModel(request));
        return ResponseEntity.ok(UsuarioMapper.fromModel(atualizado));
    }

}
