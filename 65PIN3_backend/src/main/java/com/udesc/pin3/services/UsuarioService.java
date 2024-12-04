/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.services;

import com.udesc.pin3.model.TipoUsuario;
import com.udesc.pin3.model.Estudante;
import com.udesc.pin3.model.Curso;
import com.udesc.pin3.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.udesc.pin3.model.Usuario;
import com.udesc.pin3.model.dto.CadastroEstudanteRequest;
import com.udesc.pin3.repository.CursoRepository;
import com.udesc.pin3.repository.EstudanteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author guilh
 */
@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final EstudanteRepository estudanteRepository;
    private final CursoRepository cursoRepository;
//    private final PasswordEncoder passwordEncoder;

//    public UsuarioService(UsuarioRepository usuarioRepository, EstudanteRepository estudanteRepository,
//                          CursoRepository cursoRepository, PasswordEncoder passwordEncoder) {
//        this.usuarioRepository = usuarioRepository;
//        this.estudanteRepository = estudanteRepository;
//        this.cursoRepository = cursoRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public UsuarioService(UsuarioRepository usuarioRepository, EstudanteRepository estudanteRepository, CursoRepository cursoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.estudanteRepository = estudanteRepository;
        this.cursoRepository = cursoRepository;
    }

    public ResponseEntity<String> cadastrarEstudante(CadastroEstudanteRequest request) {
        Optional<Curso> curso = cursoRepository.findById(request.getCursoId());
        
        if (!curso.isPresent())
            return ResponseEntity.badRequest().body("O curso informado (" + request.getCursoId() + ") não existe");
        
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getCpf());
        usuario.setPassword(request.getPassword());
        usuario.setTipoUsuario(TipoUsuario.ESTUDANTE);
        usuario = usuarioRepository.save(usuario);
        
        Estudante estudante = new Estudante();
        estudante.setAnoInicio(request.getAnoInicio());
        estudante.setCpf(request.getCpf());
        estudante.setCurso(curso.get());
        estudante.setNome(request.getNome());
        estudante.setSemestreInicio(request.getSemestreInicio());
        estudante.setSobrenome(request.getSobrenome());
        estudante.setUsuario(usuario);
        estudante = estudanteRepository.save(estudante);
        
        return ResponseEntity.ok().body("O cadastro foi realizado com sucesso.");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getTipoUsuario().name())
                .build();
    }
}
