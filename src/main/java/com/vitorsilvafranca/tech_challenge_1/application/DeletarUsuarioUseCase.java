package com.vitorsilvafranca.tech_challenge_1.application;

import com.vitorsilvafranca.tech_challenge_1.domain.model.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeletarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public DeletarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new ApplicationException("ID do usuário é obrigatório.");
        }
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id.toString()));

        usuarioRepository.deleteById(usuario.getId());
    }

}
