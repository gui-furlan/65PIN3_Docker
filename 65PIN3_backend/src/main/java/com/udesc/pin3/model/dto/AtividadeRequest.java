package com.udesc.pin3.model.dto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Date;

/**
 *
 * @author guilh
 */
public class AtividadeRequest {
    private String titulo;
    private String descricao;
    private Date dataInicio;
    private Date dataFinal;
    private double quantidade; // Unidade de medição
    private String status; 
    private Long tipoAtividadeId;
    private Long estudanteId;

    // Getters e Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    
    
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Long getTipoAtividadeId() {
        return tipoAtividadeId;
    }

    public void setTipoAtividadeId(Long tipoAtividadeId) {
        this.tipoAtividadeId = tipoAtividadeId;
    }

    public Long getEstudanteId() {
        return estudanteId;
    }

    public void setEstudanteId(Long estudanteId) {
        this.estudanteId = estudanteId;
    }

    @Override
    public String toString() {
        return "AtividadeRequest{" + "titulo=" + titulo + ", descricao=" + descricao + ", dataInicio=" + dataInicio + ", dataFinal=" + dataFinal + ", quantidade=" + quantidade + ", tipoAtividadeId=" + tipoAtividadeId + ", estudanteId=" + estudanteId + '}';
    }
}