package com.gabriel.sistema_agendamentos_consultas_medicas.model;

import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.StatusConsulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_consultas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Pacientes pacientes;

    @Column(name = "data-hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusConsulta status;

    @Column(name = "observacoes")
    private String observacoes;

}
