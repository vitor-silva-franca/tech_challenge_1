package com.vitorsilvafranca.tech_challenge_1.domain.model.restaurante;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.shared.DomainException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100)
    private String nome;

    @NotBlank(message = "Endereço é obrigatório")
    @Size(min = 2, max = 100)
    private String endereco;

    @NotNull(message = "Tipo de cozinha deve ser BRASILEIRA ou ESTRANGEIRA")
    @Enumerated(EnumType.STRING)
    private TipoDeCozinha tipoDeCozinha;

    @NotNull(message = "Horário de abertura é obrigatório")
    private LocalTime horarioAbertura;

    @NotNull(message = "Horário de fechamento é obrigatório")
    private LocalTime horarioFechamento;

    @NotNull(message = "ID do dono do restaurante é obrigatório")
    private Long usuarioId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id")
    @JsonManagedReference
    private List<Item> itens = new ArrayList<>();

    public Restaurante(String nome, String endereco, TipoDeCozinha tipoDeCozinha, LocalTime horarioAbertura, LocalTime horarioFechamento, Long usuarioId) {
        validate(nome, endereco, tipoDeCozinha, horarioAbertura, horarioFechamento, usuarioId);
        this.nome = nome;
        this.endereco = endereco;
        this.tipoDeCozinha = tipoDeCozinha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.usuarioId = usuarioId;
    }

    public Restaurante() {
        //Uso exclusivo do JPA
    }

    private void validate(String nome, String endereco, TipoDeCozinha tipoDeCozinha, LocalTime horarioAbertura, LocalTime horarioFechamento, Long usuarioId) {
        if (nome == null || nome.trim().isEmpty() || nome.length() < 2) {
            throw new DomainException("Nome é obrigatório e deve ter ao menos 2 caracteres");
        }

        if (endereco == null || endereco.trim().isEmpty()) {
            throw new DomainException("Endereço é obrigatório");
        }

        if (horarioAbertura == null) {
            throw new DomainException("Horário de abertura é obrigatório");
        }

        if (horarioFechamento == null) {
            throw new DomainException("Horário de fechamento é obrigatório");
        }

        if (usuarioId == null) {
            throw new DomainException("ID do dono do restaurante é obrigatório");
        }

        if (!EnumSet.of(TipoDeCozinha.BRASILEIRA, TipoDeCozinha.ESTRANGEIRA).contains(tipoDeCozinha)) {
            throw new DomainException("Tipo de cozinha deve ser BRASILEIRA ou ESTRANGEIRA");
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

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}