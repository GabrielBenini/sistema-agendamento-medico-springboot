package com.gabriel.sistema_agendamentos_consultas_medicas.service;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.Paciente;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.PacientesRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.PacientesResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.repository.PacientesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacientesRepository pacientesRepository;

    public PacientesResponseDTO cadastroPaciente(PacientesRequestDTO pacientesRequestDTO){

        Paciente paciente = new Paciente();
        paciente.setNome(pacientesRequestDTO.nome());
        paciente.setCpf(pacientesRequestDTO.cpf());
        paciente.setDataNascimento(pacientesRequestDTO.dataNascimento());
        paciente.setTelefone(pacientesRequestDTO.telefone());

        paciente = pacientesRepository.save(paciente);

        return new PacientesResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getTelefone()
        );
    }

    public PacientesResponseDTO atualizarPacientePeloID(Long id, PacientesRequestDTO pacientesRequestDTO){

        Paciente paciente = pacientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente nao encontrado com o id: " + id));

        paciente.setNome(pacientesRequestDTO.nome());
        paciente.setCpf(pacientesRequestDTO.cpf());
        paciente.setDataNascimento(pacientesRequestDTO.dataNascimento());
        paciente.setTelefone(pacientesRequestDTO.telefone());

        paciente = pacientesRepository.save(paciente);

        return new PacientesResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getTelefone()
        );
    }

    public List<PacientesResponseDTO> listagemPacientes(){

        return pacientesRepository.findAll()
                .stream()
                .map(paciente -> new PacientesResponseDTO(
                        paciente.getId(),
                        paciente.getNome(),
                        paciente.getCpf(),
                        paciente.getDataNascimento(),
                        paciente.getTelefone()
                )).toList();
    }

    public PacientesResponseDTO buscarPacientePeloId(Long id){

        Paciente paciente = pacientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente nao encontrado com o id: " + id));

        return new PacientesResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getTelefone()
        );
    }

    public void deletarPacientePeloId(Long id){

        Paciente paciente = pacientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente nao encontrado com o id: " + id));

        pacientesRepository.deleteById(id);
    }
}
