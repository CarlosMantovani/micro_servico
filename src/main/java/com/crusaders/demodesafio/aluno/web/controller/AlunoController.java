package com.crusaders.demodesafio.aluno.web.controller;

import com.crusaders.demodesafio.aluno.entities.Aluno;
import com.crusaders.demodesafio.aluno.services.AlunoService;
import com.crusaders.demodesafio.curso.entidade.Curso;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        Aluno alunoCadastrado = alunoService.cadastrarAluno(aluno);
        return new ResponseEntity<>(alunoCadastrado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Aluno>> listarAlunos() {
        Iterable<Aluno> alunos = alunoService.listarAlunos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
}
