package com.udesc.pin3.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.udesc.pin3.model.Estudante;
import com.udesc.pin3.model.TipoAtividade;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;


/**
 *
 * @author guilh
 */
@Entity
public class Atividade {

    @Id
    @GeneratedValue
    private Long id;

    private String titulo;
    private String descricao;

    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    private Date dataFinal;

    private double quantidade; // Unidade de medição (horas, eventos, semestres, etc.)
    
    private String status;

    @ManyToOne
    @JoinColumn(name = "tipo_atividade_id", nullable = false)
    private TipoAtividade tipoAtividade;

    @ManyToOne
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    // Método para calcular o total de créditos
    public double getTotalCreditos() {
        return Math.min(quantidade * tipoAtividade.getCreditosPorQuantidade(), tipoAtividade.getMaximoCreditos());
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    @Override
    public String toString() {
        return "Atividade{" + "id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", dataInicio=" + dataInicio + ", dataFinal=" + dataFinal + ", quantidade=" + quantidade + ", tipoAtividade=" + tipoAtividade + ", estudante=" + estudante + '}';
    }
    
    
}