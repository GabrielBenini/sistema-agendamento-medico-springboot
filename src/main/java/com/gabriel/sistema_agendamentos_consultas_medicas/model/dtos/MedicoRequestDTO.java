package com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.EspecialidadeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicoRequestDTO(

        @NotBlank
        String nome,
        @NotBlank
        String crm,
        @NotNull
        EspecialidadeEnum especialidade) {
}
