package com.crusaders.demodesafio.alunoematricula.web.dto;

import com.crusaders.demodesafio.curso.web.dto.CursoResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatriculaResponseDto {
    private CursoResponseDto curso;
    private List<AlunoResponseDto> alunos;
    private int totalAlunos;
}