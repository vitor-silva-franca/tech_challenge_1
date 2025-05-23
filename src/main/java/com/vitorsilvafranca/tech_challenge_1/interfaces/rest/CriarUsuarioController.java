package com.vitorsilvafranca.tech_challenge_1.interfaces.rest;

import com.vitorsilvafranca.tech_challenge_1.application.CriarUsuarioUseCase;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Criar Usuário", description = "Criação de usuários")
@RestController
@RequestMapping("/api/criarUsuario")
public class CriarUsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;

    public CriarUsuarioController(CriarUsuarioUseCase criarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
    }

    /**
     * Cria um novo usuário
     *
     * @param request
     * @return
     */
    @Operation(summary = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = UsuarioMapper.toModel(request);
        Usuario usuarioCriado = criarUsuarioUseCase.criar(usuario);
        UsuarioResponse response = UsuarioMapper.fromModel(usuarioCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
