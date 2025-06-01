package com.gabriel.sistema_agendamentos_consultas_medicas.service;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.DisponibilidadeMedico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.Medico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.DisponibilidadeMedicoRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.DisponibilidadeMedicoResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.DiaDaSemana;
import com.gabriel.sistema_agendamentos_consultas_medicas.repository.DisponibilidadeMedicoRespository;
import com.gabriel.sistema_agendamentos_consultas_medicas.repository.MedicoRepository;
import jakarta.persistence.EnumType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibilidadeMedicoService {

    private final DisponibilidadeMedicoRespository disponibilidadeRepository;
    private final MedicoRepository medicoRepository;

    public DisponibilidadeMedicoResponseDTO criarDisponibilidade(DisponibilidadeMedicoRequestDTO medicoRequest){

        Medico medico = medicoRepository.findById(medicoRequest.medicoId())
                .orElseThrow(()-> new RuntimeException("Medico nao encontrado com o id: " + medicoRequest.medicoId()));


        DisponibilidadeMedico disponibilidade = new DisponibilidadeMedico();

        disponibilidade.setMedico(medico);
        disponibilidade.setDiaDaSemana(medicoRequest.diaDaSemana());
        disponibilidade.setHoraInicio(medicoRequest.horaInicio());
        disponibilidade.setHoraTermino(medicoRequest.horaTermino());

        disponibilidade = disponibilidadeRepository.save(disponibilidade);

        return new DisponibilidadeMedicoResponseDTO(
                disponibilidade.getId(),
                disponibilidade.getMedico().getId(),
                disponibilidade.getDiaDaSemana(),
                disponibilidade.getHoraInicio(),
                disponibilidade.getHoraTermino()
        );
    }


}
