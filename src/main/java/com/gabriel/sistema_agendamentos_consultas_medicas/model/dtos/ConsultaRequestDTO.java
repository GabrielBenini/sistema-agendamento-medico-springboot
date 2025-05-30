package com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(

        @NotNull
        Long pacienteId,
        @NotNull
        Long medicoId,
        @NotNull
        LocalDateTime dataHoraConsulta,
        String observacoes

) {
}
