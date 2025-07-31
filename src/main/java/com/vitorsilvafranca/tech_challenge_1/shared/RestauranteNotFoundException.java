package com.vitorsilvafranca.tech_challenge_1.shared;

public class RestauranteNotFoundException extends RuntimeException {
    public RestauranteNotFoundException(String message) {
        super("Restaurante " + message + " não encontrado.");
    }
}