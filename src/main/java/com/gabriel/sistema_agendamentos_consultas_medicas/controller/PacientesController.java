package com.gabriel.sistema_agendamentos_consultas_medicas.controller;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.PacientesRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.PacientesResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.service.PacientesService;
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

    private final PacientesService pacientesService;

    @PostMapping
    public ResponseEntity<PacientesResponseDTO> cadastroPaciente(@RequestBody @Valid PacientesRequestDTO pacientesRequestDTO){
        PacientesResponseDTO pacientesResponseDTO = pacientesService.cadastroPaciente(pacientesRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacientesResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacientesResponseDTO> atualizarPacientePeloID(@PathVariable("id") Long id, @RequestBody @Valid PacientesRequestDTO pacientesRequestDTO){
        PacientesResponseDTO pacientesResponseDTO = pacientesService.atualizarPacientePeloID(id, pacientesRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(pacientesResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PacientesResponseDTO>> listagemPacientes(){
        List<PacientesResponseDTO> pacientesResponseDTOS = pacientesService.listagemPacientes();
        return ResponseEntity.status(HttpStatus.OK).body(pacientesResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacientesResponseDTO> buscarPacientePeloId(@PathVariable("id") Long id){

        PacientesResponseDTO pacientesResponseDTO = pacientesService.buscarPacientePeloId(id);
        return ResponseEntity.status(HttpStatus.OK).body(pacientesResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPacientePeloId(@PathVariable("id") Long id){

        pacientesService.deletarPacientePeloId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }




}
