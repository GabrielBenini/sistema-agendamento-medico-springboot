package com.gabriel.sistema_agendamentos_consultas_medicas.repository;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.DisponibilidadeMedico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.DisponibilidadeMedicoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisponibilidadeMedicoRespository extends JpaRepository<DisponibilidadeMedico, Long> {
}
