package com.vitorsilvafranca.tech_challenge_1.infrastructure.repository;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepositoryJpa extends UsuarioRepository, JpaRepository<Usuario, Long> {

    public Optional<Usuario> findById(Long id);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByLogin(String login);

    public boolean existsByEmail(String email);

    public boolean existsByLogin(String login);

    public Usuario save(Usuario u);

    public void deleteById(Long id);

}