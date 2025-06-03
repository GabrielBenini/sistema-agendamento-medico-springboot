package com.gabriel.sistema_agendamentos_consultas_medicas.service;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.Consulta;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.DisponibilidadeMedico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.Medico;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.Paciente;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.ConsultaRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.ConsultaResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.DiaDaSemana;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.StatusConsulta;
import com.gabriel.sistema_agendamentos_consultas_medicas.repository.ConsultaRepository;
import com.gabriel.sistema_agendamentos_consultas_medicas.repository.DisponibilidadeMedicoRespository;
import com.gabriel.sistema_agendamentos_consultas_medicas.repository.MedicoRepository;
import com.gabriel.sistema_agendamentos_consultas_medicas.repository.PacientesRepository;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final DisponibilidadeMedicoRespository disponibilidadeRespository;
    private final MedicoRepository medicoRepository;
    private final PacientesRepository pacientesRepository;
    private final ConsultaRepository consultaRepository;

    public ConsultaResponseDTO agendarConsulta(ConsultaRequestDTO consultaRequestDTO){

        Medico medico = medicoRepository.findById(consultaRequestDTO.medicoId())
                .orElseThrow(()-> new RuntimeException("Medico nao existente com o id: " + consultaRequestDTO.medicoId()));

        Paciente paciente = pacientesRepository.findById(consultaRequestDTO.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente nao encontrado com o id: " + consultaRequestDTO.pacienteId()));

        LocalDate dataConsulta = consultaRequestDTO.data();

        DayOfWeek diaJava = dataConsulta.getDayOfWeek();
        DiaDaSemana diaEnum = mapearDiaDaSemana(diaJava);

        DisponibilidadeMedico disponibilidade = disponibilidadeRespository
                .findByMedicoIdAndDiaDaSemana(medico.getId(), diaEnum)
                .orElseThrow(() -> new RuntimeException("O medico nao atende nesse dia da semana"));

        LocalTime horaConsulta = consultaRequestDTO.hora();

        if(horaConsulta.isBefore(disponibilidade.getHoraInicio()) ||
        horaConsulta.isAfter(disponibilidade.getHoraTermino())){
            throw new RuntimeException("O medico nao esta disponivel nesse horario");
        }

        boolean consultaExiste = consultaRepository.existsByMedicoIdAndDataAndHora(
                medico.getId(),
                consultaRequestDTO.data(),
                consultaRequestDTO.hora());

        if (consultaExiste) {
            throw new RuntimeException("Ja existe uma consulta agendada com o medico "
                    + medico.getNome() + "no dia " + consultaRequestDTO.data() + " as " + consultaRequestDTO.hora());
        }

        long totalConsultas = consultaRepository.countByMedicoIdAndDataAndStatus(
                medico.getId(),
                consultaRequestDTO.data(),
                StatusConsulta.AGENDADA
        );

        if (totalConsultas == 5){

            throw new RuntimeException("O medico ja possui o maximo de 5 consultas agendadas para esse dia!");

        }



        Consulta novaConsulta = new Consulta();
        novaConsulta.setMedico(medico);
        novaConsulta.setPaciente(paciente);
        novaConsulta.setData(consultaRequestDTO.data());
        novaConsulta.setHora(consultaRequestDTO.hora());
        novaConsulta.setStatus(StatusConsulta.AGENDADA);
        novaConsulta.setObservacoes(consultaRequestDTO.observacoes());

        consultaRepository.save(novaConsulta);

        return new ConsultaResponseDTO(

                novaConsulta.getId(),
                novaConsulta.getPaciente().getNome(),
                novaConsulta.getMedico().getNome(),
                novaConsulta.getMedico().getEspecialidade(),
                novaConsulta.getData(),
                novaConsulta.getHora(),
                novaConsulta.getStatus(),
                novaConsulta.getObservacoes()

        );
    }


    public ConsultaResponseDTO cancelarConsulta(Long id){

        Consulta consultas = consultaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Consulta nao encontrada com o id: " + id));

        if (consultas.getStatus() != StatusConsulta.AGENDADA){
            throw new RuntimeException("A consulta so pode ser cancelada se estiver com o status AGENDADA!");
        }

        consultas.setStatus(StatusConsulta.CANCELADA);
        consultaRepository.save(consultas);

        return new ConsultaResponseDTO(
                consultas.getId(),
                consultas.getPaciente().getNome(),
                consultas.getMedico().getNome(),
                consultas.getMedico().getEspecialidade(),
                consultas.getData(),
                consultas.getHora(),
                consultas.getStatus(),
                consultas.getObservacoes()
        );
    }

    public ConsultaResponseDTO finalizarConsulta(Long id){

        Consulta consultas = consultaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Consulta nao encontrada com o id: " + id));

        if (consultas.getStatus() != StatusConsulta.AGENDADA){
            throw new RuntimeException("A consulta ja foi finalizada!");
        }

        consultas.setStatus(StatusConsulta.REALIZADA);
        consultaRepository.save(consultas);

        return new ConsultaResponseDTO(
                consultas.getId(),
                consultas.getPaciente().getNome(),
                consultas.getMedico().getNome(),
                consultas.getMedico().getEspecialidade(),
                consultas.getData(),
                consultas.getHora(),
                consultas.getStatus(),
                consultas.getObservacoes()
        );


    }

    public ConsultaResponseDTO buscarConsultaPeloId(Long id){

        Consulta consultas = consultaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Consulta nao encontrada com o id: " + id));

        return new ConsultaResponseDTO(
                consultas.getId(),
                consultas.getPaciente().getNome(),
                consultas.getMedico().getNome(),
                consultas.getMedico().getEspecialidade(),
                consultas.getData(),
                consultas.getHora(),
                consultas.getStatus(),
                consultas.getObservacoes()
        );
    }

    public List<ConsultaResponseDTO> listarConsultasGeral(){

        return consultaRepository.findAll()
                .stream()
                .map(consultas -> new ConsultaResponseDTO(
                        consultas.getId(),
                        consultas.getPaciente().getNome(),
                        consultas.getMedico().getNome(),
                        consultas.getMedico().getEspecialidade(),
                        consultas.getData(),
                        consultas.getHora(),
                        consultas.getStatus(),
                        consultas.getObservacoes()
                )).toList();
    }

    public List<ConsultaResponseDTO> listarConsultasPorPacienteId(Long pacienteId){

        Paciente pacientes = pacientesRepository.findById(pacienteId)
                .orElseThrow(()-> new RuntimeException("Paciente nao encontrado com o id: " + pacienteId));

        List<Consulta> consultas = consultaRepository.findByPacienteId(pacienteId);

        if(consultas.isEmpty()){
            throw new RuntimeException("Esse paciente nao possui consultas cadastradas");
        }

        return consultas.stream()
                .map(consulta -> new ConsultaResponseDTO(
                        consulta.getId(),
                        consulta.getPaciente().getNome(),
                        consulta.getMedico().getNome(),
                        consulta.getMedico().getEspecialidade(),
                        consulta.getData(),
                        consulta.getHora(),
                        consulta.getStatus(),
                        consulta.getObservacoes()
                )).toList();
    }

    public List<ConsultaResponseDTO> listarConsultasPorMedicoId(Long medicoId){

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico nao encontrado com o id: " + medicoId));

        List<Consulta> consultas = consultaRepository.findByMedicoId(medicoId);

        if (consultas.isEmpty()){
            throw new RuntimeException("Esse medico nao possui consultas cadastradas!");
        }

        return consultas.stream()
                .map(consulta -> new ConsultaResponseDTO(
                        consulta.getId(),
                        consulta.getPaciente().getNome(),
                        consulta.getMedico().getNome(),
                        consulta.getMedico().getEspecialidade(),
                        consulta.getData(),
                        consulta.getHora(),
                        consulta.getStatus(),
                        consulta.getObservacoes()
                )).toList();
    }


















    public void deletarConsultaPorId(Long id){

        Consulta consultas = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta nao encontrada com o id: " + id));

        consultaRepository.delete(consultas);

    }

    private DiaDaSemana mapearDiaDaSemana(DayOfWeek diaJava){
        return switch (diaJava) {

            case MONDAY -> DiaDaSemana.SEGUNDA_FEIRA;
            case TUESDAY -> DiaDaSemana.TERCA_FEIRA;
            case WEDNESDAY -> DiaDaSemana.QUARTA_FEIRA;
            case THURSDAY -> DiaDaSemana.QUINTA_FEIRA;
            case FRIDAY -> DiaDaSemana.SEXTA_FEIRA;
            case SATURDAY -> DiaDaSemana.SABADO;
            case SUNDAY -> DiaDaSemana.DOMINGO;
        };
    }


}
