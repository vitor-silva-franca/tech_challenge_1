package com.vitorsilvafranca.tech_challenge_1.shared;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super("Item " + message + " não encontrado.");
    }
}