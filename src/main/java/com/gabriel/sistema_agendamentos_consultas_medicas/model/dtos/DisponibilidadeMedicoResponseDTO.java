package com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.DiaDaSemana;

import java.time.LocalTime;

public record DisponibilidadeMedicoResponseDTO(Long id, Long medicoId,String medicoNome, DiaDaSemana diaDaSemana, LocalTime horaInicio, LocalTime horaTermino) {
}
