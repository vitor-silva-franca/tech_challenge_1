package com.vitorsilvafranca.tech_challenge_1.application.usuario;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.UsuarioNotFoundException;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class AlterarSenhaUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public AlterarSenhaUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String alterar(Usuario usuarioComNovaSenha) {

        if (usuarioComNovaSenha.getEmail() == null || usuarioComNovaSenha.getLogin() == null || usuarioComNovaSenha.getSenha() == null) {
            throw new ApplicationException("Email, login e senha são obrigatórios para realizar a troca da senha");
        }

        //Verifica se existe Usuário cadastrado com login recebido
        if (!usuarioRepository.existsByLogin(usuarioComNovaSenha.getLogin())) {
            throw new UsuarioNotFoundException("Login não encontrado");
        }

        //Verifica se existe Usuário cadastrado com email recebido
        if (!usuarioRepository.existsByEmail(usuarioComNovaSenha.getEmail())) {
            throw new UsuarioNotFoundException("Email não encontrado");
        }

        //Guarda na memória Usuário com Email recebido
        Usuario existenteEmail = usuarioRepository.findByEmail(usuarioComNovaSenha.getEmail())
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioComNovaSenha.getEmail()));

        //Guarda na memória Usuário com Login recebido
        Usuario existenteLogin = usuarioRepository.findByLogin(usuarioComNovaSenha.getLogin())
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioComNovaSenha.getLogin()));

        //Se o Login e Email recebidos pertecem ao mesmo Usuário -> realiza troca de senha
        if (existenteEmail.getEmail().equals(existenteLogin.getEmail()) && existenteEmail.getLogin().equals(existenteLogin.getLogin())) {
            Usuario u = (usuarioRepository.findById(existenteEmail.getId())
                    .orElseThrow(() -> new UsuarioNotFoundException(usuarioComNovaSenha.getEmail())));
            u.setSenha(usuarioComNovaSenha.getSenha());
            u.setDataAlteracao(Date.valueOf(LocalDate.now()));
            usuarioRepository.save(u);
            return "Senha alterada com sucesso";
        }

        return "Solicitação inválida";
    }

}