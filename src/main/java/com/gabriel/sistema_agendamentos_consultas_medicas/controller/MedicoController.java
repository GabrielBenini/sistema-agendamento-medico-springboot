package com.gabriel.sistema_agendamentos_consultas_medicas.controller;


import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.MedicoRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.MedicoResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> cadastrarMedico(@RequestBody @Valid MedicoRequestDTO medicoRequestDTO){
        MedicoResponseDTO medicoResponseDTO = medicoService.cadastrarMedico(medicoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> atualizarMedicoPeloId(@PathVariable("id") Long id, @RequestBody @Valid MedicoRequestDTO medicoRequestDTO){
        MedicoResponseDTO medicoResponseDTO = medicoService.atualizarMedicoPeloId(id, medicoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(medicoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listarMedicos(){
        List<MedicoResponseDTO> medicoResponseDTO = medicoService.listarMedicos();
        return ResponseEntity.status(HttpStatus.OK).body(medicoResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> buscarMedicoPorId(@PathVariable Long id){
        MedicoResponseDTO medicoResponseDTO = medicoService.buscarMedicoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(medicoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedicoPorId(@PathVariable Long id){

        medicoService.deletarMedicoPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
