package com.gabriel.sistema_agendamentos_consultas_medicas.service;


import com.gabriel.sistema_agendamentos_consultas_medicas.exceptions.IdNaoEncontradoException;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.Medico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.MedicoRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.MedicoResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoResponseDTO cadastrarMedico(MedicoRequestDTO medicoRequestDTO){

        Medico medico = new Medico();
        medico.setNome(medicoRequestDTO.nome());
        medico.setCrm(medicoRequestDTO.crm());
        medico.setEspecialidade(medicoRequestDTO.especialidade());

        medico = medicoRepository.save(medico);

        return new MedicoResponseDTO(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }

    public MedicoResponseDTO atualizarMedicoPeloId(Long id, MedicoRequestDTO medicoRequestDTO){

        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Medico nao encontrado com o id: " + id));

        medico.setNome(medicoRequestDTO.nome());
        medico.setCrm(medicoRequestDTO.crm());
        medico.setEspecialidade(medicoRequestDTO.especialidade());

        medico = medicoRepository.save(medico);

        return new MedicoResponseDTO(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }

    public List<MedicoResponseDTO> listarMedicos(){

        return medicoRepository.findAll()
                .stream()
                .map(medicos -> new MedicoResponseDTO(
                        medicos.getId(),
                        medicos.getNome(),
                        medicos.getCrm(),
                        medicos.getEspecialidade()
                )).toList();
    }

    public MedicoResponseDTO buscarMedicoPorId(Long id){

        Medico medico = medicoRepository.findById(id)
                .orElseThrow(()-> new IdNaoEncontradoException("Medico nao encontrado com o id: " + id));

        return new MedicoResponseDTO(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }

    public void deletarMedicoPorId(Long id){

        Medico medico = medicoRepository.findById(id)
                .orElseThrow(()-> new IdNaoEncontradoException("Medico nao encontrado com o id: " + id));

        medicoRepository.deleteById(id);

    }

}
