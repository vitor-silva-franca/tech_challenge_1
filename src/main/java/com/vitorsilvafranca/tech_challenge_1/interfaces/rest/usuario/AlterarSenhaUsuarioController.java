package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.AlterarSenhaUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.TrocarSenhaRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.UsuarioMapper;
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
public class AlterarSenhaUsuarioController {

    private final AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase;

    public AlterarSenhaUsuarioController(AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase) {
        this.alterarSenhaUsuarioUseCase = alterarSenhaUsuarioUseCase;
    }


    @PutMapping
    public ResponseEntity<String> alterar(@Valid @RequestBody TrocarSenhaRequest request) {
        try {
            Usuario usuario = UsuarioMapper.toSenhaNova(request);
            String resposta = alterarSenhaUsuarioUseCase.alterar(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(resposta);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar senha do usuário: " + e.getMessage());
        }
    }
}