package com.crusaders.demodesafio.alunoematricula.web.controller;

import com.crusaders.demodesafio.alunoematricula.entities.Aluno;
import com.crusaders.demodesafio.alunoematricula.services.AlunoService;
import com.crusaders.demodesafio.alunoematricula.web.dto.AlunoResponseDto;
import com.crusaders.demodesafio.alunoematricula.web.dto.mapper.AlunoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;

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

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> getById(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        return ResponseEntity.ok(AlunoMapper.toDto(aluno));
    }

    @PatchMapping("/{id}/aluno")
    public ResponseEntity<AlunoResponseDto> alterarStatusAluno(@PathVariable Long id, @RequestBody AlunoResponseDto alunoResponseDto) {
        Aluno aluno = alunoService.alterarStatusAluno(id, alunoResponseDto.getStatus());
        return ResponseEntity.ok(AlunoMapper.toDto(aluno));
    }
}
