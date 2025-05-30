package com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos;

import java.time.LocalDate;

public record PacientesResponseDTO(Long id,String nome, String cpf, LocalDate dataNascimento, String telefone) {
}
