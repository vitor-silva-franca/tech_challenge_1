package com.vitorsilvafranca.tech_challenge_1.interfaces.dto;

import com.vitorsilvafranca.tech_challenge_1.domain.model.RoleUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

public class UsuarioRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100)
    private String nome;

    @Email
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Login é obrigatório")
    private String login;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 100)
    private String senha;

    @Schema(hidden = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotNull(message = "Role é obrigatória e deve ser 'ADMIN' ou 'CLIENTE'")
    private RoleUsuario role;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public RoleUsuario getRole() {
        return role;
    }

    public void setRole(RoleUsuario role) {
        this.role = role;
    }

}
