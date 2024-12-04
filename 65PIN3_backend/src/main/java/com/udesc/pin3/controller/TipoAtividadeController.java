/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.controller;

import com.udesc.pin3.model.TipoAtividade;
import com.udesc.pin3.repository.TipoAtividadeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 *
 * @author guilh
 */
@RestController
@RequestMapping("/api/tipos-atividade")
public class TipoAtividadeController {

    private final TipoAtividadeRepository tipoAtividadeRepository;

    public TipoAtividadeController(TipoAtividadeRepository tipoAtividadeRepository) {
        this.tipoAtividadeRepository = tipoAtividadeRepository;
    }

    @GetMapping
    public ResponseEntity<List<TipoAtividade>> listarTodos() {
        List<TipoAtividade> tiposAtividade = tipoAtividadeRepository.findAll();
        return ResponseEntity.ok(tiposAtividade);
    }
}