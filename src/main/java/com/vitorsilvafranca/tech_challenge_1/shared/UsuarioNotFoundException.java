package com.vitorsilvafranca.tech_challenge_1.shared;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super("Usuário " + message + " não encontrado.");
    }
}