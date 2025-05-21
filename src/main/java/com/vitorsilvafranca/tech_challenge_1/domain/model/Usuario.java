package com.vitorsilvafranca.tech_challenge_1.domain.model;

import com.vitorsilvafranca.tech_challenge_1.shared.DomainException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.EnumSet;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100)
    private String nome;

    @Email
    @NotBlank(message = "Email é obrigatório")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Login é obrigatório")
    @Column(unique = true, nullable = false)
    private String login;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 100)
    private String senha;


    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotNull(message = "Role é obrigatória e deve ser 'ADMIN' ou 'CLIENTE'")
    @Enumerated(EnumType.STRING)
    private RoleUsuario role;

    //Construtor completo
    public Usuario(String nome, String email, String login, String senha, Date dataAlteracao, String endereco, RoleUsuario role) {
        validate(nome, email, login, senha, dataAlteracao, endereco, role);
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataAlteracao = dataAlteracao;
        this.endereco = endereco;
        this.role = role;
    }

    //Construtor para login
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    //Construtor para alteração de senha
    public Usuario(String email, String login, String senha) {
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    @Deprecated
    public Usuario(){
        //Uso exclusivo do JPA
    }

    private void validate(String nome, String email, String login, String senha, Date dataAlteracao, String endereco, RoleUsuario role) {
        if (nome == null || nome.trim().isEmpty() || nome.length() < 2) {
            throw new DomainException("Nome é obrigatório e deve ter ao menos 2 caracteres");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new DomainException("Email é obrigatório");
        }

        if (login == null || login.trim().isEmpty()) {
            throw new DomainException("Login é obrigatório");
        }

        if (senha == null || senha.length() < 6) {
            throw new DomainException("Senha deve conter ao menos 8 caracteres");
        }

        if (endereco == null || endereco.trim().isEmpty()) {
            throw new DomainException("Endereço é obrigatório");
        }

        if (role == null) {
            throw new DomainException("Role do usuário é obrigatória");
        }

        if (!EnumSet.of(RoleUsuario.ADMIN, RoleUsuario.CLIENTE).contains(role)) {
            throw new DomainException("Role deve ser ADMIN ou CLIENTE");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
