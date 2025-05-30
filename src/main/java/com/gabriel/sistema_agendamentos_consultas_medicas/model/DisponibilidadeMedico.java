package com.gabriel.sistema_agendamentos_consultas_medicas.model;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.DiaDaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "tb_disponibilidade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilidadeMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diaDaSemana", length = 15, nullable = false)
    @Enumerated
    private DiaDaSemana diaDaSemana;

    @Column(name = "horaInicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "horaTermino", nullable = false)
    private LocalTime horaTermino;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;



}
