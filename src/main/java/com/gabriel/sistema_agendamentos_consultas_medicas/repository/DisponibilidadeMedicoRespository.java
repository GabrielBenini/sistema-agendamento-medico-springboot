package com.gabriel.sistema_agendamentos_consultas_medicas.repository;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.DisponibilidadeMedico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.DiaDaSemana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DisponibilidadeMedicoRespository extends JpaRepository<DisponibilidadeMedico, Long> {

    List<DisponibilidadeMedico> findByMedicoId(Long medicoId);

    Optional<DisponibilidadeMedico> findByMedicoIdAndDiaDaSemana(Long medicoId, DiaDaSemana diaDaSemana);

}
