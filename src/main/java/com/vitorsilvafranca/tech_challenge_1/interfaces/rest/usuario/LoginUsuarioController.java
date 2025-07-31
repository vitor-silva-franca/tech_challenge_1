package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.LoginUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.LoginUsuarioRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.UsuarioMapper;
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
@RequestMapping("/api/loginUsuario")
@Tag(name = "Login Usuário", description = "Acesso de usuários")
public class LoginUsuarioController {

    private final LoginUsuarioUseCase loginUsuarioUseCase;

    public LoginUsuarioController(LoginUsuarioUseCase loginUsuarioUseCase) {
        this.loginUsuarioUseCase = loginUsuarioUseCase;
    }


    @PostMapping
    public ResponseEntity<String> login(@Valid @RequestBody LoginUsuarioRequest request) {
        try {
            Usuario usuario = UsuarioMapper.toLogin(request);
            String resposta = loginUsuarioUseCase.login(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(resposta);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro no login: " + e.getMessage());
        }
    }
}