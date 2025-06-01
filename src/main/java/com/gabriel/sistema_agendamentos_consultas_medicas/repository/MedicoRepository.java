package com.gabriel.sistema_agendamentos_consultas_medicas.repository;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.Medico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.DisponibilidadeMedicoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
