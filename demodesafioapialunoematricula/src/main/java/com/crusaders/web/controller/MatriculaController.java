package com.crusaders.web.controller;

import com.crusaders.entities.Aluno;
import com.crusaders.repository.CursoClient;
import com.crusaders.services.AlunoService;
import com.crusaders.entities.Matricula;
import com.crusaders.services.MatriculaService;
import com.crusaders.web.dto.CursoRequestDto;
import com.crusaders.web.dto.MatriculaAlunoDto;
import com.crusaders.web.dto.MatriculaRequestDto;
import com.crusaders.web.dto.MatriculaResponseDto;
import com.crusaders.web.dto.mapper.AlunoMapper;
import com.crusaders.web.dto.mapper.MatriculaMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/matriculas")
@AllArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final AlunoService alunoService;

    private final CursoClient cursoClient;

    @PostMapping
    public ResponseEntity<?> matricularAluno(@RequestBody MatriculaRequestDto requestDto) {
        Aluno aluno = alunoService.buscarPorId(requestDto.getAlunoId());

        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        boolean matriculado = matriculaService.matricularAluno(requestDto.getCursoId(), requestDto.getAlunoId());

        if (matriculado) {
            CursoRequestDto curso = cursoClient.buscarPorId(requestDto.getCursoId());
            List<Aluno> alunosMatriculados = matriculaService.listarAlunosMatriculados(curso);
            int totalAlunos = alunosMatriculados.size();

            MatriculaResponseDto responseDto = new MatriculaResponseDto();
            responseDto.setCurso(curso);
            responseDto.setAlunos(alunosMatriculados.stream().map(AlunoMapper::toDto).collect(Collectors.toList()));
            responseDto.setTotalAlunos(totalAlunos);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<MatriculaAlunoDto>> listarMatriculasPorAluno(@PathVariable Long alunoId) {
        List<MatriculaAlunoDto> responseDtoList = matriculaService.listarMatriculasPorAluno(alunoId);
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaAlunoDto> getById(@PathVariable Long id) {
        MatriculaAlunoDto matriculaAlunoDto = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matriculaAlunoDto);
    }
}
