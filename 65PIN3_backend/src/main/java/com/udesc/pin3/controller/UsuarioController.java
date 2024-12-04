/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.controller;

import com.udesc.pin3.model.*;
import com.udesc.pin3.model.dto.*;
import com.udesc.pin3.repository.CursoRepository;
import com.udesc.pin3.services.CursoService;
import com.udesc.pin3.services.UsuarioService;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guilh
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final CursoService cursoService;
    
    public UsuarioController(UsuarioService usuarioService, CursoService cursoService) {
        this.usuarioService = usuarioService;
        this.cursoService = cursoService;
    }

    @PostMapping("/estudantes")
    public ResponseEntity<String> cadastrarEstudante(@RequestBody CadastroEstudanteRequest request) {
        return usuarioService.cadastrarEstudante(request);
    }
}