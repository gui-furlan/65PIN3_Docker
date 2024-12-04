/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.controller;

import com.udesc.pin3.model.dto.AtividadeRequest;
import com.udesc.pin3.model.Atividade;
import com.udesc.pin3.model.Estudante;
import com.udesc.pin3.model.TipoAtividade;
import com.udesc.pin3.model.dto.ResumoAtividadesDTO;
import com.udesc.pin3.repository.AtividadeRepository;
import com.udesc.pin3.repository.EstudanteRepository;
import com.udesc.pin3.repository.TipoAtividadeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author guilh
 */
@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    private final AtividadeRepository atividadeRepository;
    private final EstudanteRepository estudanteRepository;
    private final TipoAtividadeRepository tipoAtividadeRepository;

    public AtividadeController(AtividadeRepository atividadeRepository,
                                EstudanteRepository estudanteRepository,
                                TipoAtividadeRepository tipoAtividadeRepository) {
        this.atividadeRepository = atividadeRepository;
        this.estudanteRepository = estudanteRepository;
        this.tipoAtividadeRepository = tipoAtividadeRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> recuperarAtividade(@PathVariable Long id) {
        return atividadeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estudante/{estudanteId}")
    public ResponseEntity<List<Atividade>> listarAtividadesDeEstudante(@PathVariable Long estudanteId) {
        List<Atividade> atividades = atividadeRepository.findByEstudanteId(estudanteId);
        for (Atividade atividade : atividades) {
            System.out.println(atividade.toString());
        }
        return ResponseEntity.ok(atividades);
    }

    @PostMapping
    public ResponseEntity<Atividade> cadastrarAtividade(@RequestBody AtividadeRequest request) {
        System.out.println("request:" + request.toString());

        Estudante estudante = estudanteRepository.findById(request.getEstudanteId())
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado"));

        TipoAtividade tipoAtividade = tipoAtividadeRepository.findById(request.getTipoAtividadeId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de Atividade não encontrado"));

        Atividade atividade = new Atividade();
        atividade.setTitulo(request.getTitulo());
        atividade.setDescricao(request.getDescricao());
        atividade.setDataInicio(request.getDataInicio());
        atividade.setDataFinal(request.getDataFinal());
        atividade.setQuantidade(request.getQuantidade());
        atividade.setStatus(request.getStatus());
        atividade.setTipoAtividade(tipoAtividade);
        atividade.setEstudante(estudante);

        Atividade novaAtividade = atividadeRepository.save(atividade);
        return ResponseEntity.ok(novaAtividade);
    }
    
    @GetMapping("/resumo/estudante/{estudanteId}")
    public ResponseEntity<ResumoAtividadesDTO> obterResumoAtividades(@PathVariable Long estudanteId) {
        List<Atividade> atividades = atividadeRepository.findByEstudanteId(estudanteId);

        double creditosValidados = 0;
        double creditosPendentes = 0;
        final double metaCreditos = 17;

        for (Atividade atividade : atividades) {
            double totalCreditos = atividade.getQuantidade() * atividade.getTipoAtividade().getCreditosPorQuantidade();

            if ("Validada".equalsIgnoreCase(atividade.getStatus())) {
                creditosValidados += totalCreditos;
            } else if ("Pendente".equalsIgnoreCase(atividade.getStatus())) {
                creditosPendentes += totalCreditos;
            }
        }

        double creditosFaltantes = Math.max(0, metaCreditos - creditosValidados);

        ResumoAtividadesDTO resumo = new ResumoAtividadesDTO(creditosValidados, creditosPendentes, creditosFaltantes);

        return ResponseEntity.ok(resumo);
    }

}