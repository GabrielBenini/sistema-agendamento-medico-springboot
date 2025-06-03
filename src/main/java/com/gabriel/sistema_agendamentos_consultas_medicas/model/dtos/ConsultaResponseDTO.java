package com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.EspecialidadeEnum;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.StatusConsulta;

import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaResponseDTO(Long id, String nomePaciente, String nomeMedico, EspecialidadeEnum especialidade, LocalDate data, LocalTime hora,
                                  StatusConsulta status, String observacoes)
{
}
