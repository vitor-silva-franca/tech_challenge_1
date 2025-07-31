package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class ItemRequest {

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

    @NotNull(message = "ID do restaurante é obrigatório")
    private Long restauranteId;

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