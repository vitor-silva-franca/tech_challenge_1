package com.vitorsilvafranca.tech_challenge_1.interfaces.rest.usuario;

import com.vitorsilvafranca.tech_challenge_1.application.usuario.BuscarUsuarioUseCase;
import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.UsuarioResponse;
import com.vitorsilvafranca.tech_challenge_1.interfaces.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/buscarUsuario")
@Tag(name = "Buscar Usuário", description = "Endpoint para busca de usuários")
public class BuscarUsuarioController {

    private final BuscarUsuarioUseCase buscarUsuarioUseCase;

    public BuscarUsuarioController(BuscarUsuarioUseCase buscarUsuarioUseCase) {
        this.buscarUsuarioUseCase = buscarUsuarioUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable(required = true) Long id) {
        try {
            Usuario usuario = buscarUsuarioUseCase.buscarPorId(id);
            return ResponseEntity.ok(UsuarioMapper.fromModel(usuario));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar usuário: " + e.getMessage());
        }
    }
}