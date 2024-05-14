package com.crusaders.repository;

import com.crusaders.entities.Aluno;
import com.crusaders.web.dto.AlunoResponseDto;
import com.crusaders.web.dto.CursoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "curso-service", url = "http://localhost:8081/api/v1")
public interface CursoClient {

    @PostMapping("/cursos")
    void cadastrarCurso(@RequestBody CursoRequestDto curso);

    @PutMapping("/cursos/{id}/status")
    void alterarStatus(@PathVariable("id") Long id);

    @PutMapping("/cursos/{id}/professor")
    void editarProfessor(@PathVariable("id") Long id, @RequestParam("novoProfessor") String novoProfessor);

    @GetMapping("/cursos/{id}/alunos")
    List<AlunoResponseDto> consultarAlunosMatriculados(@PathVariable("id") Long id);

    @GetMapping("/cursos/{id}")
    CursoRequestDto buscarPorId(@PathVariable("id") Long id);
}
