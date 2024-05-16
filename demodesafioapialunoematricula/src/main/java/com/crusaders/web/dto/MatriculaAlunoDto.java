package com.crusaders.web.dto;

import com.crusaders.Enum.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaAlunoDto {
    private Long id;
    private String nomeAluno;
    private String nomeCurso;
    private Status status;
}