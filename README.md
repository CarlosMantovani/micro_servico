﻿# Desafio 2 micro serviço para gestão de cursos, matriculas e alunos. Programa de Bolsas Compass Uol

Este projeto consiste em dois microserviços distintos, um para a gestão de cursos e outro para a gestão de alunos e matrículas. Ambos os microserviços foram desenvolvidos utilizando Spring Boot com Java, estes microserviços fornecem uma maneira eficiente de gerenciar cursos, alunos e matrículas

## Tecnologias Utilizadas

- Spring Boot
- H2 Database
- Java
- Spring Data JPA
- RESTful Web Services
- OpenFeign
- Maven
- Trello
- Swagger
- Postman
- Lombok

## Instalação

```
git clone https://github.com/CarlosMantovani/micro_servico.git
```

## Como Usar

Instruções de uso: Apos o clone abri as duas pastas separadamente e rodar elas separadas.

## Autor
- Carlos Henrique Mantovani
- João Guilherme
- Gabriel Vieira
- João Vitor

## Status do Projeto

Em desenvolvimento

## Endpoints:
## Aluno
```
POST /api/v1/alunos: Cria um novo aluno. Retorna o aluno criado.
```
```
GET /api/v1/alunos: Lista todos os alunos. Retorna uma lista de alunos.
```
```
GET /api/v1/alunos/{id}: Busca um aluno pelo ID. Retorna o aluno encontrado.
```
```
PATCH /api/v1/alunos/{id}/status: Altera o status de um aluno. Retorna o aluno com o status alterado.
```
## Matricula
```
POST /api/v1/matriculas: Cria uma nova matrícula. Retorna informações sobre o curso e os alunos matriculados após a matrícula ser efetuada.
```
```
GET /api/v1/matriculas/aluno/{alunoId}: Busca todas as matrículas de um aluno específico. Retorna uma lista de matrículas
```
```
GET /api/v1/matriculas/{id}: Busca detalhes de uma matrícula específica. Retorna as informações da matrícula.
```
```
PATCH /api/v1/matriculas/{idMatricula}/status: Altera o status de uma matrícula. Retorna a matrícula com o status alterado.
```
## Curso
```
POST /api/v1/cursos: Cria um novo curso. Retorna o curso criado.
```
```
GET /api/v1/cursos/{id}: Busca um curso pelo ID. Retorna o curso encontrado.
```
```
PATCH /api/v1/cursos/{id}/status: Altera o status de um curso. 
```
```
PATCH /api/v1/cursos/{id}/professor: Altera o nome do professor de um curso.
```
```
GET /api/v1/cursos: Lista todos os cursos. Retorna uma lista de cursos.
```
## Para contribuir com micro_servico, siga estas etapas:

1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin micro_servico/ <local>`
5. Crie a solicitação de pull.
