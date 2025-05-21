package com.vitorsilvafranca.tech_challenge_1.interfaces.rest;

import com.vitorsilvafranca.tech_challenge_1.application.DeletarUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Deletar Usuário pelo ID", description = "Remoção de usuários")
@RestController
@RequestMapping("/api/deletarUsuario")
public class DeletarUsuarioController {

    private final DeletarUsuarioUseCase deletarUsuarioUseCase;

    public DeletarUsuarioController(DeletarUsuarioUseCase deletarUsuarioUseCase) {
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
    }

    /**
     * Deleta um usuário pelo ID
     * @param id
     * @return
     */
    @Operation(summary = "Deleta um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUsuarioUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
