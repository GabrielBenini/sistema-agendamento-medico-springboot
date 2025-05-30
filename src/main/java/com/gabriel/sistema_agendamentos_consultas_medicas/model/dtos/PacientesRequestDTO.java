package com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PacientesRequestDTO(

        @NotBlank
        String nome,
        @NotBlank
        @CPF
        String cpf,
        @NotNull
        @Past
        LocalDate dataNascimento,
        @NotBlank
        String telefone) {



}
