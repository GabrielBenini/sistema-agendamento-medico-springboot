package com.gabriel.sistema_agendamentos_consultas_medicas.controller;


import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.DisponibilidadeMedicoRequestDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.model.dtos.DisponibilidadeMedicoResponseDTO;
import com.gabriel.sistema_agendamentos_consultas_medicas.service.DisponibilidadeMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadeMedicoController {

    private final DisponibilidadeMedicoService medicoService;

    @PostMapping
    public ResponseEntity<DisponibilidadeMedicoResponseDTO> criarDisponibilidade(@RequestBody DisponibilidadeMedicoRequestDTO medicoRequestDTO){

        DisponibilidadeMedicoResponseDTO medicoResponseDTO = medicoService.criarDisponibilidade(medicoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoResponseDTO);
    }


}
