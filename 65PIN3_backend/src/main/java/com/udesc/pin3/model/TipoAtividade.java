/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author guilh
 */
@Entity
public class TipoAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sigla;
    private int maximoCreditos;
    private double creditosPorQuantidade;

    @Enumerated(EnumType.STRING)
    private Categoria categoria; // Categoria da atividade (Ensino, Extensão, etc.)

    // Getters e Setters
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getMaximoCreditos() {
        return maximoCreditos;
    }

    public void setMaximoCreditos(int maximoCreditos) {
        this.maximoCreditos = maximoCreditos;
    }

    public double getCreditosPorQuantidade() {
        return creditosPorQuantidade;
    }

    public void setCreditosPorQuantidade(double creditosPorQuantidade) {
        this.creditosPorQuantidade = creditosPorQuantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}