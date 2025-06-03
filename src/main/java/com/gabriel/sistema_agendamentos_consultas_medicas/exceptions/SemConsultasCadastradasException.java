package com.gabriel.sistema_agendamentos_consultas_medicas.exceptions;

public class SemConsultasCadastradasException extends RuntimeException {
    public SemConsultasCadastradasException(String message) {
        super(message);
    }
}
