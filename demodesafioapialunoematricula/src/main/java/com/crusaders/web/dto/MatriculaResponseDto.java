package com.crusaders.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatriculaResponseDto {
    private CursoRequestDto curso;
    private List<AlunoResponseDto> alunos;
    private int totalAlunos;
}