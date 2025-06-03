package com.gabriel.sistema_agendamentos_consultas_medicas.controller;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.ConsultaRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.ConsultaResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> agendarConsulta(@RequestBody ConsultaRequestDTO consultaRequestDTO){

        ConsultaResponseDTO consultaResponseDTO = consultaService.agendarConsulta(consultaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaResponseDTO);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<ConsultaResponseDTO> cancelarConsulta(@PathVariable("id") Long id){

        ConsultaResponseDTO consultaResponseDTO = consultaService.cancelarConsulta(id);
        return ResponseEntity.status(HttpStatus.OK).body(consultaResponseDTO);
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<ConsultaResponseDTO> finalizarConsulta(@PathVariable("id") Long id){
        ConsultaResponseDTO consultaResponseDTO = consultaService.finalizarConsulta(id);
        return ResponseEntity.status(HttpStatus.OK).body(consultaResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarConsultaPeloId(@PathVariable("id") Long id){

        ConsultaResponseDTO consultaResponseDTO = consultaService.buscarConsultaPeloId(id);
        return ResponseEntity.status(HttpStatus.OK).body(consultaResponseDTO);

    }

    @GetMapping
    public ResponseEntity<Page<ConsultaResponseDTO>> listarConsultasPaginadas(Pageable pageable){

        Page<ConsultaResponseDTO> consultaResponseDTO = consultaService.listarConsultasPaginadas(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(consultaResponseDTO);

    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultasPorPacienteId(@PathVariable("id") Long id){

        List<ConsultaResponseDTO> consultaResponseDTOS = consultaService.listarConsultasPorPacienteId(id);
        return ResponseEntity.status(HttpStatus.OK).body(consultaResponseDTOS);

    }

    @GetMapping("/medicos/{id}")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultasPorMedicoId(@PathVariable("id") Long id){

        List<ConsultaResponseDTO> consultaResponseDTO = consultaService.listarConsultasPorMedicoId(id);
        return ResponseEntity.status(HttpStatus.OK).body(consultaResponseDTO);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarConsulta(@PathVariable("id") Long id){
        consultaService.deletarConsultaPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
