package com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaRequestDTO(

        @NotNull
        Long pacienteId,
        @NotNull
        Long medicoId,
        @NotNull
        @Future
        LocalDate data,
        @NotNull
        LocalTime hora,
        String observacoes

) {
}
