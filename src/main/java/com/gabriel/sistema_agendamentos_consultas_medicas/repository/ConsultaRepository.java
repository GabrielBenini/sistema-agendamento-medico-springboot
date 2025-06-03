package com.gabriel.sistema_agendamentos_consultas_medicas.repository;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.Consulta;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.Medico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndDataAndHora(Long medicoId, LocalDate data, LocalTime hora);

    List<Consulta> findByPacienteId(Long pacienteId);

    List<Consulta> findByMedicoId(Long medicoId);

    Long countByMedicoIdAndDataAndStatus(Long medicoId, LocalDate data, StatusConsulta status);

}
