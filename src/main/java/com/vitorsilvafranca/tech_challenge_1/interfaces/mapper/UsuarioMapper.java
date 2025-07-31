package com.vitorsilvafranca.tech_challenge_1.interfaces.mapper;

import com.vitorsilvafranca.tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.LoginUsuarioRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.TrocarSenhaRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.UsuarioRequest;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.usuario.UsuarioResponse;
import java.sql.Date;
import java.time.LocalDate;

public class UsuarioMapper {

    public static Usuario toModel(UsuarioRequest request) {
        return new Usuario(
                request.getNome(),
                request.getEmail(),
                request.getLogin(),
                request.getSenha(),
                Date.valueOf(LocalDate.now()),
                request.getEndereco(),
                request.getRole());
    }

    public static Usuario toLogin(LoginUsuarioRequest request) {
        return new Usuario(
                request.getLogin(),
                request.getSenha());
    }

    public static Usuario toSenhaNova(TrocarSenhaRequest request) {
        return new Usuario(
                request.getEmail(),
                request.getLogin(),
                request.getSenhaNova());
    }

    public static UsuarioResponse fromModel(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getEndereco(),
                usuario.getRole()
        );
    }

}