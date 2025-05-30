package com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.EspecialidadeEnum;

public record MedicoResponseDTO(Long id, String nome, String crm, EspecialidadeEnum especialidade) {
}
