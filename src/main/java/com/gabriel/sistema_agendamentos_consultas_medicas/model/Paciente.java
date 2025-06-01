package com.gabriel.sistema_agendamentos_consultas_medicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_pacientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 15)
    private String cpf;

    @Column(name = "dataNascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Consulta> consultas;

}
