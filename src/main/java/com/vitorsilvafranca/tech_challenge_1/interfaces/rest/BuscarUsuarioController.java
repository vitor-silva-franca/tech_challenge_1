package com.vitorsilvafranca.tech_challenge_1.interfaces.rest;

import com.vitorsilvafranca.tech_challenge_1.application.BuscarUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.UsuarioResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Buscar Usuário pelo ID", description = "Busca de usuários")
@RestController
@RequestMapping("/api/buscarUsuario")
public class BuscarUsuarioController {

    private final BuscarUsuarioUseCase buscarUsuarioUseCase;

    public BuscarUsuarioController(BuscarUsuarioUseCase buscarUsuarioUseCase) {
        this.buscarUsuarioUseCase = buscarUsuarioUseCase;
    }

    /**
     * Busca um usuário pelo ID
     *
     * @param id
     * @return
     */
    @Operation(summary = "Busca um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable(required = true) Long id) {
        System.out.println(id);
        Usuario usuario = buscarUsuarioUseCase.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.fromModel(usuario));
    }

}
