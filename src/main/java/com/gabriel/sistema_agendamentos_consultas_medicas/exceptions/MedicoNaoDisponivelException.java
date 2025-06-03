package com.gabriel.sistema_agendamentos_consultas_medicas.exceptions;

public class MedicoNaoDisponivelException extends RuntimeException {
    public MedicoNaoDisponivelException(String message) {
        super(message);
    }
}
