package com.vitorsilvafranca.tech_challenge_1.application.usuario;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioJaExisteException;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class CriarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public CriarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail()) || usuarioRepository.existsByLogin(usuario.getLogin())) {
            throw new UsuarioJaExisteException("Email ou Login já em uso");
        }
        usuario.setDataAlteracao(Date.valueOf(LocalDate.now()));
        return usuarioRepository.save(usuario);
    }
}