package com.vitorsilvafranca.tech_challenge_1.application;

import com.vitorsilvafranca.tech_challenge_1.domain.model.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioJaExisteException;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioNotFoundException;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class AtualizarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public AtualizarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario atualizar(Long id, Usuario novosDados) {
        if (id == null) {
            throw new ApplicationException("ID do usuário é obrigatório.");
        }
        if (novosDados.getNome() == null || novosDados.getEmail() == null || novosDados.getLogin() == null || novosDados.getSenha() == null || novosDados.getEndereco() == null || novosDados.getRole() == null) {
            throw new ApplicationException("Todos os dados do usuário são obrigatórios.");
        }

        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id.toString()));

        //Se o email já estiver em uso lança Exception
        boolean emailAlterado = !existente.getEmail().equals(novosDados.getEmail());
        if (emailAlterado && usuarioRepository.existsByEmail(novosDados.getEmail())) {
            throw new UsuarioJaExisteException(novosDados.getEmail());
        }

        //Se o login já estiver em uso lança Exception
        boolean loginAlterado = !existente.getLogin().equals(novosDados.getLogin());
        if (loginAlterado && usuarioRepository.existsByLogin(novosDados.getLogin())) {
            throw new UsuarioJaExisteException(novosDados.getLogin());
        }

        existente.setNome(novosDados.getNome());
        existente.setEmail(novosDados.getEmail());
        existente.setLogin(novosDados.getLogin());
        existente.setSenha(novosDados.getSenha());
        existente.setEndereco(novosDados.getEndereco());
        existente.setRole(novosDados.getRole());
        existente.setDataAlteracao(Date.valueOf(LocalDate.now()));

        return usuarioRepository.save(existente);
    }

}
