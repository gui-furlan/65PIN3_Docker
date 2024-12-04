/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.repository;

import com.udesc.pin3.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 *
 * @author guilh
 */
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    List<Atividade> findByEstudanteId(Long estudanteId);
}
