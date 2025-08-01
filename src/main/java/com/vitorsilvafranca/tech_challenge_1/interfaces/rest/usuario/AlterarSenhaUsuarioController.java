package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.AlterarSenhaUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.TrocarSenhaRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/alterarSenhaUsuario")
@Tag(name = "Alterar Senha de Usuário", description = "Alteração de senha do usuário")
public class AlterarSenhaUsuarioController {

    private final AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase;

    public AlterarSenhaUsuarioController(AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase) {
        this.alterarSenhaUsuarioUseCase = alterarSenhaUsuarioUseCase;
    }


    @PutMapping
    public ResponseEntity<String> alterar(@Valid @RequestBody TrocarSenhaRequest request) {
        Usuario usuario = UsuarioMapper.toSenhaNova(request);
        String resposta = alterarSenhaUsuarioUseCase.alterar(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}