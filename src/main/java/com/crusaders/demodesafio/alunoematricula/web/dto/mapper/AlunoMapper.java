package com.crusaders.demodesafio.alunoematricula.web.dto.mapper;

import com.crusaders.demodesafio.alunoematricula.entities.Aluno;
import com.crusaders.demodesafio.alunoematricula.web.dto.AlunoResponseDto;
import org.modelmapper.ModelMapper;

public class AlunoMapper {
    public static Aluno toAluno(AlunoResponseDto createDto) {
        return new ModelMapper().map(createDto, Aluno.class);
    }

    public static AlunoResponseDto toDto(Aluno aluno) {
        return new ModelMapper().map(aluno, AlunoResponseDto.class);
    }
}