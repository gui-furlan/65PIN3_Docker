/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.controller;

//import com.udesc.pin3.util.JwtTokenUtil;
import com.udesc.pin3.model.dto.LoginRequest;
import com.udesc.pin3.model.*;
import com.udesc.pin3.model.dto.LoginResponse;
import com.udesc.pin3.repository.EstudanteRepository;
import com.udesc.pin3.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author guilh
 */
@RestController
@RequestMapping("/api")
public class AuthController {

//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenUtil jwtTokenUtil;
    
    private final UsuarioRepository usuarioRepository;
    private final EstudanteRepository estudanteRepository;

//    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }

//    public AuthController(AuthenticationManager authenticationManager) {;;;
//        this.authenticationManager = authenticationManager;
//    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login() {
//        return ResponseEntity.ok("Login removido para simplificação");
//    }  
    
//    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest request) {
//        System.out.println("request: " + request.toString());
//        try {
//            Authentication auth = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//            return jwtTokenUtil.generateToken(auth.getName());
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("Credenciais inválidas");
//        }
//    }

    public AuthController(UsuarioRepository usuarioRepository, EstudanteRepository estudanteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.estudanteRepository = estudanteRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsernameAndPassword(request.getCpf(), request.getSenha())
                .orElseThrow(() -> new IllegalArgumentException("CPF ou senha inválidos"));

        Estudante estudante = estudanteRepository.findByCpf(request.getCpf())
                .orElse(null); // Retorna null caso o usuário não seja um estudante

        LoginResponse response = new LoginResponse(usuario, estudante);
        return ResponseEntity.ok(response);
    }
    
}