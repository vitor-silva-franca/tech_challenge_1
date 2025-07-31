package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import com.vitorsilvafranca.tech_challenge_1.interfaces.dto.item.ItemResponse;
import java.time.LocalTime;
import java.util.List;

public class RestauranteResponse {

    private String nome;

    private String endereco;

    private TipoDeCozinha tipoDeCozinha;

    private LocalTime horarioAbertura;

    private LocalTime horarioFechamento;

    private Long usuarioId;

    private List<ItemResponse> itens;

    public RestauranteResponse(String nome, String endereco, TipoDeCozinha tipoDeCozinha, LocalTime horarioAbertura, LocalTime horarioFechamento, Long usuarioId, List<ItemResponse> itens) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipoDeCozinha = tipoDeCozinha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.usuarioId = usuarioId;
        this.itens = itens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public TipoDeCozinha getTipoDeCozinha() {
        return tipoDeCozinha;
    }

    public void setTipoDeCozinha(TipoDeCozinha tipoDeCozinha) {
        this.tipoDeCozinha = tipoDeCozinha;
    }

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(LocalTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(LocalTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<ItemResponse> getItens() {
        return itens;
    }

    public void setItens(List<ItemResponse> itens) {
        this.itens = itens;
    }
}