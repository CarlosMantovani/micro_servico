package com.crusaders.demodesafio.curso.web.controller;

import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.service.CursoService;
import com.crusaders.demodesafio.curso.web.dto.CursoResponseDto;
import com.crusaders.demodesafio.curso.web.dto.mapper.CursoMapper;
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
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDto> getById(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        return ResponseEntity.ok(CursoMapper.toDto(curso));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CursoResponseDto> alterarStatusCurso(@PathVariable Long id, @RequestBody CursoResponseDto cursoResponseDto) {
        Curso curso = cursoService.alterarStatusCurso(id, cursoResponseDto.getStatus());
        return ResponseEntity.ok(CursoMapper.toDto(curso));
    }
}
