/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.model.dto;

/**
 *
 * @author guilh
 */
public class ResumoAtividadesDTO {

    private double creditosValidados;
    private double creditosPendentes;
    private double creditosFaltantes;

    public ResumoAtividadesDTO() {}

    public ResumoAtividadesDTO(double creditosValidados, double creditosPendentes, double creditosFaltantes) {
        this.creditosValidados = creditosValidados;
        this.creditosPendentes = creditosPendentes;
        this.creditosFaltantes = creditosFaltantes;
    }

    public double getCreditosValidados() {
        return creditosValidados;
    }

    public void setCreditosValidados(double creditosValidados) {
        this.creditosValidados = creditosValidados;
    }

    public double getCreditosPendentes() {
        return creditosPendentes;
    }

    public void setCreditosPendentes(double creditosPendentes) {
        this.creditosPendentes = creditosPendentes;
    }

    public double getCreditosFaltantes() {
        return creditosFaltantes;
    }

    public void setCreditosFaltantes(double creditosFaltantes) {
        this.creditosFaltantes = creditosFaltantes;
    }
}