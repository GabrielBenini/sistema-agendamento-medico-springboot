package com.gabriel.sistema_agendamentos_consultas_medicas.repository;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
