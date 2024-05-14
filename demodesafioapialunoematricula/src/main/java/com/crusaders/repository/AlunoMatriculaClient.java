package com.crusaders.repository;

import com.crusaders.entities.Aluno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "aluno-matricula-service", url = "http://localhost:8082")
public interface AlunoMatriculaClient {

    @PostMapping("/alunos")
    void cadastrarAluno(@RequestBody Aluno aluno);

    @PostMapping("/alunos/{cpf}/matricular")
    void matricularAluno(@PathVariable("cpf") String cpf, @RequestParam("cursoId") Long cursoId);

    @PutMapping("/alunos/{cpf}/inativar-matricula")
    void inativarMatricula(@PathVariable("cpf") String cpf, @RequestParam("cursoId") Long cursoId);

    @GetMapping("/cursos/{id}/alunos")
    List<Aluno> consultarAlunosMatriculados(@PathVariable("id") Long id);
}
