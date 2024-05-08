package com.crusaders.demodesafio.curso.web.controller;

import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody Curso curso) {
        Curso cursoCadastrado = cursoService.cadastrarCurso(curso);
        return new ResponseEntity<>(cursoCadastrado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Curso>> listarCursos() {
        Iterable<Curso> cursos = cursoService.listarCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }
}
