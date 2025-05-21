package com.vitorsilvafranca.tech_challenge_1.interfaces.dto;

public class UsuarioResponse {

    private Long id;

    private String nome;

    private String email;

    private String login;

    private String endereco;

    public UsuarioResponse(Long id, String nome, String email, String login, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getEndereco() {
        return endereco;
    }
}
