package com.vitorsilvafranca.tech_challenge_1.application.usuario;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public LoginUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String login(Usuario usuario) {
        if (usuarioRepository.existsByLogin(usuario.getLogin())) {
            Usuario usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin())
                    .orElseThrow(() -> new UsuarioNotFoundException(usuario.getLogin()));
            if (usuario.getSenha().equals(usuarioExistente.getSenha())) {
                return "Usuário logado com sucesso!";
            }

        }
        return "Acesso negado!";
    }
}