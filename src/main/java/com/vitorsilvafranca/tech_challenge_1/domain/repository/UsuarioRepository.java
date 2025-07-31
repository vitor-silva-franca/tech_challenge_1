package com.vitorsilvafranca.tech_challenge_1.domain.repository;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByLogin(String login);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    Usuario save(Usuario usuario);

    void deleteById(Long id);

}