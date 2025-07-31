package com.vitorsilvafranca.tech_challenge_1.interfaces.dto.restaurante;

import com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante.TipoDeCozinha;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;

public class RestauranteRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100)
    private String nome;

    @NotBlank(message = "Endereço é obrigatório")
    @Size(min = 2, max = 100)
    private String endereco;

    @NotNull(message = "Tipo de cozinha é obrigatório e deve ser: Brasileira ou Estrangeira")
    private TipoDeCozinha tipoDeCozinha;

    @NotNull(message = "Horário de abertura é obrigatório")
    private LocalTime horarioAbertura;

    @NotNull(message = "Horário de fechamento é obrigatório")
    private LocalTime horarioFechamento;

    @NotNull(message = "ID do dono do restaurante é obrigatório")
    private Long usuarioId;

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
}