package com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.EspecialidadeEnum;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.StatusConsulta;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(Long id, String nomePaciente, String nomeMedico, EspecialidadeEnum especialidade, LocalDateTime dataHoraConsulta,
                                  StatusConsulta status, String observacoes)
{

}
