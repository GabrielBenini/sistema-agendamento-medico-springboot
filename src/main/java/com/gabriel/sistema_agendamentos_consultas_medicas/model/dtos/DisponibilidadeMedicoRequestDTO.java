package com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.DiaDaSemana;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record DisponibilidadeMedicoRequestDTO(


        @NotNull
        Long medicoId,
        @NotNull
        DiaDaSemana diaDaSemana,
        @NotNull
        LocalTime horaInicio,
        @NotNull
        LocalTime horaTermino


) {
}
