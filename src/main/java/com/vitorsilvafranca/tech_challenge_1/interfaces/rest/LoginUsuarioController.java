package com.vitorsilvafranca.tech_challenge_1.interfaces.rest;

import com.vitorsilvafranca.tech_challenge_1.application.LoginUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.LoginUsuarioRequest;
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

@Tag(name = "Login Usuário", description = "Acesso de usuários")
@RestController
@RequestMapping("/api/loginUsuario")
public class LoginUsuarioController {

    private final LoginUsuarioUseCase loginUsuarioUseCase;

    public LoginUsuarioController(LoginUsuarioUseCase loginUsuarioUseCase) {
        this.loginUsuarioUseCase = loginUsuarioUseCase;
    }

    /**
     * Usuário faz acesso ao sistema
     * @param request
     * @return
     */
    @Operation(summary = "Usuário faz acesso ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário logado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<String> login(@Valid @RequestBody LoginUsuarioRequest request) {
        Usuario usuario = UsuarioMapper.toLogin(request);
        String resposta = loginUsuarioUseCase.login(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

}
