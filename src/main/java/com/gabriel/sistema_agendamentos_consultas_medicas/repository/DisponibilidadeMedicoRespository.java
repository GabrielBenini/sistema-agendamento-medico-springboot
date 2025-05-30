package com.gabriel.sistema_agendamentos_consultas_medicas.repository;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.DisponibilidadeMedico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisponibilidadeMedicoRespository extends JpaRepository<DisponibilidadeMedico, Long> {
}
