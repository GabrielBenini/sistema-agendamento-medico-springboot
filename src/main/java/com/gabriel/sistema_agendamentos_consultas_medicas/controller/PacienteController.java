package com.gabriel.sistema_agendamentos_consultas_medicas.controller;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.PacientesRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.PacientesResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacientesController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacientesResponseDTO> cadastroPaciente(@RequestBody @Valid PacientesRequestDTO pacientesRequestDTO){
        PacientesResponseDTO pacientesResponseDTO = pacienteService.cadastroPaciente(pacientesRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacientesResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacientesResponseDTO> atualizarPacientePeloID(@PathVariable("id") Long id, @RequestBody @Valid PacientesRequestDTO pacientesRequestDTO){
        PacientesResponseDTO pacientesResponseDTO = pacienteService.atualizarPacientePeloID(id, pacientesRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(pacientesResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PacientesResponseDTO>> listagemPacientes(){
        List<PacientesResponseDTO> pacientesResponseDTOS = pacienteService.listagemPacientes();
        return ResponseEntity.status(HttpStatus.OK).body(pacientesResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacientesResponseDTO> buscarPacientePeloId(@PathVariable("id") Long id){

        PacientesResponseDTO pacientesResponseDTO = pacienteService.buscarPacientePeloId(id);
        return ResponseEntity.status(HttpStatus.OK).body(pacientesResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPacientePeloId(@PathVariable("id") Long id){

        pacienteService.deletarPacientePeloId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }




}
