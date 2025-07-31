package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item;

import java.math.BigDecimal;

public class ItemResponse {

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Boolean delivery; // true == faz delivery, false == somente restaurante

    private String foto;

    private Long restauranteId;

    public ItemResponse(String nome, String descricao, BigDecimal preco, Boolean delivery, String foto, Long restauranteId) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.delivery = delivery;
        this.foto = foto;
        this.restauranteId = restauranteId;
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

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }
}