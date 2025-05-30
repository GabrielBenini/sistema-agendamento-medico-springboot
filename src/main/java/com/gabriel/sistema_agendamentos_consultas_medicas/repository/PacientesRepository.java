package com.gabriel.sistema_agendamentos_consultas_medicas.repository;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientesRepository extends JpaRepository<Pacientes, Long> {
}
