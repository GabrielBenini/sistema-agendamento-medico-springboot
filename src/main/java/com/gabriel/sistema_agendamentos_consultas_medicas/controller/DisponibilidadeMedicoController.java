package com.gabriel.sistema_agendamentos_consultas_medicas.controller;


import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.DisponibilidadeMedicoRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.DisponibilidadeMedicoResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.service.DisponibilidadeMedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadeMedicoController {

    private final DisponibilidadeMedicoService disponibilidadeMedico;

    @PostMapping
    public ResponseEntity<DisponibilidadeMedicoResponseDTO> criarDisponibilidade(@RequestBody @Valid DisponibilidadeMedicoRequestDTO medicoRequestDTO){

        DisponibilidadeMedicoResponseDTO medicoResponseDTO = disponibilidadeMedico.criarDisponibilidade(medicoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDisponibilidade(@PathVariable("id") Long id){

        disponibilidadeMedico.deletarDisponibilidade(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<DisponibilidadeMedicoResponseDTO>> listarTodasDisponibilidades(){
        List<DisponibilidadeMedicoResponseDTO> medicoResponseDTO = disponibilidadeMedico.listarTodasDisponibilidades();
        return ResponseEntity.status(HttpStatus.OK).body(medicoResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadeMedicoResponseDTO> listarDisponibilidadePorId(@PathVariable("id") Long id){
        DisponibilidadeMedicoResponseDTO disponibilidadeMedicoResponseDTO = disponibilidadeMedico.listarDisponibilidadePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(disponibilidadeMedicoResponseDTO);
    }

    @GetMapping("/medicos/{medicoId}")
    public ResponseEntity<List<DisponibilidadeMedicoResponseDTO>> listarDisponibilidadePorMedicoId(@PathVariable("medicoId") Long medicoId){

        List<DisponibilidadeMedicoResponseDTO> disponibilidadeMedicoResponseDTO = disponibilidadeMedico.listarDisponibilidadePorMedicoId(medicoId);
        return ResponseEntity.status(HttpStatus.OK).body(disponibilidadeMedicoResponseDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadeMedicoResponseDTO> atualizarDisponibilidadePorId(@PathVariable("id") Long id, @RequestBody @Valid DisponibilidadeMedicoRequestDTO disponibilidadeMedicoRequestDTO){
        DisponibilidadeMedicoResponseDTO disponibilidadeMedicoResponseDTO = disponibilidadeMedico.atualizarDisponibilidadePorId(id, disponibilidadeMedicoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(disponibilidadeMedicoResponseDTO);
    }


}
