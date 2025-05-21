package com.vitorsilvafranca.tech_challenge_1.shared;

public class UsuarioJaExisteException extends RuntimeException {
    public UsuarioJaExisteException(String message) {
        super("Já existe um usuário com o e-mail "+ message);
    }
}
