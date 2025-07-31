package com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.Restaurante;
import com.vitorsilvafranca.tech_challenge_1.shared.DomainException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100)
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 2, max = 100)
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    private BigDecimal preco;

    @NotNull(message = "Faz Delivery (true) ou somente Restaurante (false) é obrigatório")
    private Boolean delivery; // true == faz delivery, false == somente restaurante

    private String foto;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    @JsonBackReference
    private Restaurante restaurante;

    public Item(String nome, String descricao, BigDecimal preco, Boolean delivery, String foto, Restaurante restaurante) {
        validate(nome, descricao, preco, delivery, foto, restaurante);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.delivery = delivery;
        this.foto = foto;
        this.restaurante = restaurante;
    }

    public Item() {
        //Uso exclusivo do JPA
    }

    private void validate(String nome, String descricao, BigDecimal preco, Boolean delivery, String foto, Restaurante restaurante) {
        if (nome == null || nome.trim().isEmpty() || nome.length() < 2) {
            throw new DomainException("Nome é obrigatório e deve ter ao menos 2 caracteres");
        }

        if (descricao == null || descricao.trim().isEmpty()) {
            throw new DomainException("Descrição é obrigatória");
        }

        if (preco == null) {
            throw new DomainException("Preço é obrigatório");
        }

        if (restaurante == null) {
            throw new DomainException("Restaurante é obrigatório");
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}