
# 📚 Sistema de Agendamentos de Consultas Médicas – API REST

Este é um projeto Java com Spring Boot que simula um sistema de agendamentos de consultas medicas. Ele permite o cadastro, listagem, atualização e remoção, entre outros métodos de Pacientes, Medicos, Disponibilidades médicas e Consultas, com algumas regras de negócio aplicadas.

## 🚀 Tecnologias Utilizadas


- Java 17

- Spring Boot

- Spring Data JPA

- PostgreSQL

- Maven

- Lombok

- Postman (para testes)

- Docker (para banco local, opcional)

## 🧠 Regras de Negócio

- Um paciente pode agendar uma consulta com um médico.

- Não pode haver dois agendamentos para o mesmo médico no mesmo horário.

- O sistema deve permitir listar todas as consultas de um paciente ou médico.

- Deve mostrar: nome do paciente, nome do médico, especialidade, data e hora da consulta.

- Datas e horários devem ser validadas para não permitir agendamento no passado.

- Um médico pode atender no máximo 5 consultas por dia.

- Paginação e ordenação nas listagens.


## 🏛️ Entidades

### 👨‍⚕️ Médico

- id: Long

- nome: String

- crm: String

- especialidade: EspecialidadeEnum

- disponibilidadeMedicos: DisponibilidadeMedico (relacionamento OneToMany)

- consultas: Consulta (relacionamento OneToMany)

### 📓 DisponibilidadeMedico

- id: Long

- diaDaSemana: DiaDaSemana

- horaInicio: LocalTime

- horaTermino: LocalTime

medico: Medico (relacionamento ManyToOne)

### 👤 Paciente

- id: Long

- nome: String

- cpf: String

- dataNascimento: LocalDate

- telefone: String

- consultas: Consulta (relacionamento OneToMany)


### 📒 Consulta

- id: Long

- data: LocalDate

- hora: LocalTime

- status: StatusConsulta

- observacoes: String

- medico: Medico (relacionamento ManyToOne)

- paciente: Paciente (relacionamento ManyToOne)


## 📂 Estrutura do Projeto

- model: Entidades JPA (Paciente, Medico, DisponibilidadeMedico, Consulta)

- repository: Interfaces do Spring Data JPA

- service: Regras de negócio implementadas

- controller: Endpoints da API REST

- dto: Camada de transferência de dados (Request e Response)

- exceptions: Excessões personalizadas

- enumerates: Implementadas strings padroes do tipo Enum

## 🔄 Endpoints Principais

### Paciente
- POST /pacientes: Cadastrar Pacientes

- GET /pacientes: Listar todos pacientes

- GET /pacientes/{id}: Buscar pacientes por ID

- PUT /pacientes/{id}: Atualizar paciente

- DELETE /pacientes/{id}: Remover pacientes

### Médico
- POST /medicos: Cadastrar Médicos

- GET /medicos: Listar todos Médicos

- GET /medicos/{id}: Buscar Médicos por ID

- PUT /medicos/{id}: Atualizar Médicos por ID

- DELETE /medicos/{id}: Remover Médicos pelo ID

### Disponibilidades Médicas
- POST /disponibilidades: Cadastrar Disponibilidades

- GET /disponibilidades: Listar todas Disponibilidades

- GET /disponibilidades/{id}: Buscar Disponibilidades por ID

- GET /disponibilidades/medicos/{id}: Buscar Disponibilidades pelo Médicos ID

- PUT /disponibilidades/{id}: Atualizar Disponibilidades pelo seu ID

- DELETE /disponibilidades/{id}: Remover Disponibilidade pelo ID

### Consultas
- POST /consultas: Agendar Consultas

- GET /consultas/{id}: Listar Consulta pelo ID

- GET /consultas?page=1&size=5&sort=status,asc: Buscar Consulta Paginada, passando Keys como page, size, sort...

- GET /consultas/pacientes/{id}: Buscar Consultas pelo Paciente ID

- GET /consultas/medicos/{id}: Buscar Consultas pelo Medico ID

- PUT /consultas/cancelar/{id}: Atualizar Consulta para o status "CANCELADA"

- PUT /consultas/finalizar/{id}: Atualizar Consulta para o status "REALIZADA"

- DELETE /consultas/{id}: Remover Consulta pelo ID


## 🧪 Testes

Os testes foram realizados via Postman, utilizando dados simulados.

## 🧾 Observações
- A API utiliza o padrão DTO para comunicação.

- Padrão SOLID

- Exceções são tratadas com mensagens claras de erro.

- Projeto com foco em boas práticas de arquitetura em camadas.

