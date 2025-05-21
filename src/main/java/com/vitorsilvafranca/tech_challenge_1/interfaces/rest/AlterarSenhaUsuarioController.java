package com.vitorsilvafranca.tech_challenge_1.interfaces.rest;

import com.vitorsilvafranca.tech_challenge_1.application.AlterarSenhaUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.TrocarSenhaRequest;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "Alterar Senha de Usuário", description = "Alteração de senha de usuários")
@RestController
@RequestMapping("/api/alterarSenhaUsuario")
public class AlterarSenhaUsuarioController {

    private final AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase;

    public AlterarSenhaUsuarioController(AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase) {
        this.alterarSenhaUsuarioUseCase = alterarSenhaUsuarioUseCase;
    }

    /**
     * Alterar a senha de um usuário
     * @param request
     * @return
     */
    @Operation(summary = "Altera a senha de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha do usuário atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping
    public ResponseEntity<String> alterar(@Valid @RequestBody TrocarSenhaRequest request) {
        Usuario usuario = UsuarioMapper.toSenhaNova(request);
        String resposta = alterarSenhaUsuarioUseCase.alterar(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}
