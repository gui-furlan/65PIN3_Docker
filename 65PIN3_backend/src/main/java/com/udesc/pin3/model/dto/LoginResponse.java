/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.model.dto;

import com.udesc.pin3.model.Estudante;
import com.udesc.pin3.model.Usuario;

/**
 *
 * @author guilh
 */
public class LoginResponse {

    private Usuario usuario;
    private Estudante estudante;

    // Construtores
    public LoginResponse() {}

    public LoginResponse(Usuario usuario, Estudante estudante) {
        this.usuario = usuario;
        this.estudante = estudante;
    }

    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }
}