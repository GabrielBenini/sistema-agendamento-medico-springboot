package com.gabriel.sistema_agendamentos_consultas_medicas.model;


import com.gabriel.sistema_agendamentos_consultas_medicas.model.enumerates.EspecialidadeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

import java.util.List;

@Entity
@Table(name = "tb_medicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "crm", nullable = false, length = 50)
    private String crm;

    @Column(name = "especialidade", nullable = false, length = 30)
    @Enumerated
    private EspecialidadeEnum especialidade;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<DisponibilidadeMedico> disponibilidadeMedicos;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas;




}

