package com.crusaders.demodesafio.alunoematricula.web.controller;

import com.crusaders.demodesafio.alunoematricula.entities.Aluno;
import com.crusaders.demodesafio.alunoematricula.services.AlunoService;
import com.crusaders.demodesafio.alunoematricula.entities.Matricula;
import com.crusaders.demodesafio.alunoematricula.services.MatriculaService;
import com.crusaders.demodesafio.alunoematricula.web.dto.MatriculaAlunoDto;
import com.crusaders.demodesafio.alunoematricula.web.dto.MatriculaRequestDto;
import com.crusaders.demodesafio.alunoematricula.web.dto.MatriculaResponseDto;
import com.crusaders.demodesafio.alunoematricula.web.dto.mapper.AlunoMapper;
import com.crusaders.demodesafio.alunoematricula.web.dto.mapper.MatriculaMapper;
import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.service.CursoService;
import com.crusaders.demodesafio.curso.web.dto.mapper.CursoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/matriculas")
@AllArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final AlunoService alunoService;
    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<Void> matricularAluno(@RequestBody MatriculaRequestDto requestDto) {
        Curso curso = cursoService.buscarPorId(requestDto.getCursoId());
        Aluno aluno = alunoService.buscarPorId(requestDto.getAlunoId());

        if (curso == null || aluno == null) {
            return ResponseEntity.notFound().build();
        }

        boolean matriculado = matriculaService.matricularAluno(curso, aluno);

        if (matriculado) {
            List<Aluno> alunosMatriculados = matriculaService.listarAlunosMatriculados(curso);
            int totalAlunos = alunosMatriculados.size();

            MatriculaResponseDto responseDto = new MatriculaResponseDto();
            responseDto.setCurso(CursoMapper.toDto(curso));
            responseDto.setAlunos(alunosMatriculados.stream().map(AlunoMapper::toDto).collect(Collectors.toList()));
            responseDto.setTotalAlunos(totalAlunos);

            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<MatriculaAlunoDto>> listarMatriculasPorAluno(@PathVariable Long alunoId) {
        List<MatriculaAlunoDto> responseDtoList = matriculaService.listarMatriculasPorAluno(alunoId);
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaAlunoDto> getById(@PathVariable Long id) {
        Matricula matricula = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(MatriculaMapper.toDtoId(matricula));
    }
}