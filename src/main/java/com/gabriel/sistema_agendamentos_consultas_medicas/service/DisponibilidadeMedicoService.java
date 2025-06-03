package com.gabriel.sistema_agendamentos_consultas_medicas.service;

import com.gabriel.sistema_agendamentos_consultas_medicas.exceptions.IdNaoEncontradoException;
import com.gabriel.sistema_agendamentos_consultas_medicas.exceptions.MedicoNaoDisponivelException;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.DisponibilidadeMedico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.Medico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.DisponibilidadeMedicoRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.DisponibilidadeMedicoResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.repository.DisponibilidadeMedicoRespository;
import com.gabriel.sistema_agendamentos_consultas_medicas.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibilidadeMedicoService {

    private final DisponibilidadeMedicoRespository disponibilidadeRepository;
    private final MedicoRepository medicoRepository;

    public DisponibilidadeMedicoResponseDTO criarDisponibilidade(DisponibilidadeMedicoRequestDTO medicoRequest){

        Medico medico = medicoRepository.findById(medicoRequest.medicoId())
                .orElseThrow(()-> new IdNaoEncontradoException("Medico nao encontrado com o id: " + medicoRequest.medicoId()));


        DisponibilidadeMedico disponibilidade = new DisponibilidadeMedico();

        disponibilidade.setMedico(medico);
        disponibilidade.setDiaDaSemana(medicoRequest.diaDaSemana());
        disponibilidade.setHoraInicio(medicoRequest.horaInicio());
        disponibilidade.setHoraTermino(medicoRequest.horaTermino());

        disponibilidade = disponibilidadeRepository.save(disponibilidade);

        return new DisponibilidadeMedicoResponseDTO(
                disponibilidade.getId(),
                disponibilidade.getMedico().getId(),
                disponibilidade.getMedico().getNome(),
                disponibilidade.getDiaDaSemana(),
                disponibilidade.getHoraInicio(),
                disponibilidade.getHoraTermino()
        );
    }

    public void deletarDisponibilidade(Long id){

        DisponibilidadeMedico disponibilidade = disponibilidadeRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Disponibilidade medica nao encontrada com o id: " + id));
        disponibilidadeRepository.delete(disponibilidade);
    }

    public List<DisponibilidadeMedicoResponseDTO> listarTodasDisponibilidades(){

        List<DisponibilidadeMedico> disponibilidades = disponibilidadeRepository.findAll();

        return disponibilidades.stream()
                .map(disponibilidadeMedico -> new DisponibilidadeMedicoResponseDTO(
                        disponibilidadeMedico.getId(),
                        disponibilidadeMedico.getMedico().getId(),
                        disponibilidadeMedico.getMedico().getNome(),
                        disponibilidadeMedico.getDiaDaSemana(),
                        disponibilidadeMedico.getHoraInicio(),
                        disponibilidadeMedico.getHoraTermino()
                )).toList();
    }

    public DisponibilidadeMedicoResponseDTO listarDisponibilidadePorId(Long id){

        DisponibilidadeMedico disponibilidades = disponibilidadeRepository.findById(id)
                .orElseThrow(()-> new IdNaoEncontradoException("Disponibilidade nao encontrada com o id: " + id));

        return new DisponibilidadeMedicoResponseDTO(
                disponibilidades.getId(),
                disponibilidades.getMedico().getId(),
                disponibilidades.getMedico().getNome(),
                disponibilidades.getDiaDaSemana(),
                disponibilidades.getHoraInicio(),
                disponibilidades.getHoraTermino()
        );
    }

    public List<DisponibilidadeMedicoResponseDTO> listarDisponibilidadePorMedicoId(Long medicoId){

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(()-> new IdNaoEncontradoException("Medico nao encontrado com o id: " + medicoId));

        List<DisponibilidadeMedico> disponibilidades = disponibilidadeRepository.findByMedicoId(medicoId);

        if(disponibilidades.isEmpty()){
            throw new MedicoNaoDisponivelException("Esse medico ainda nao possui disponibilidades cadastradas!");
        }

        return disponibilidades.stream()
                .map(disponibilidadeMedico -> new DisponibilidadeMedicoResponseDTO(
                        disponibilidadeMedico.getId(),
                        disponibilidadeMedico.getMedico().getId(),
                        disponibilidadeMedico.getMedico().getNome(),
                        disponibilidadeMedico.getDiaDaSemana(),
                        disponibilidadeMedico.getHoraInicio(),
                        disponibilidadeMedico.getHoraTermino()
                )).toList();
    }

    public DisponibilidadeMedicoResponseDTO atualizarDisponibilidadePorId(Long id, DisponibilidadeMedicoRequestDTO disponibilidadeMedicoRequestDTO){

        DisponibilidadeMedico disponibilidades = disponibilidadeRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Disponibilidade nao encontrada com o id: " + id));


        Medico medico = medicoRepository.findById(disponibilidadeMedicoRequestDTO.medicoId())
                .orElseThrow(()-> new IdNaoEncontradoException("Medico nao encontrado com o id: " + disponibilidadeMedicoRequestDTO.medicoId()));

        disponibilidades.setMedico(medico);
        disponibilidades.setDiaDaSemana(disponibilidadeMedicoRequestDTO.diaDaSemana());
        disponibilidades.setHoraInicio(disponibilidadeMedicoRequestDTO.horaInicio());
        disponibilidades.setHoraTermino(disponibilidadeMedicoRequestDTO.horaTermino());

        disponibilidadeRepository.save(disponibilidades);

        return new DisponibilidadeMedicoResponseDTO(
                disponibilidades.getId(),
                disponibilidades.getMedico().getId(),
                disponibilidades.getMedico().getNome(),
                disponibilidades.getDiaDaSemana(),
                disponibilidades.getHoraInicio(),
                disponibilidades.getHoraTermino()
        );
    }
}
