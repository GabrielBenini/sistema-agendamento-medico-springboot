package com.gabriel.sistema_agendamentos_consultas_medicas.repository;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
