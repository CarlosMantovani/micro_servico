package com.crusaders.demodesafio.aluno.web.dto.mapper;

import com.crusaders.demodesafio.aluno.entities.Aluno;
import com.crusaders.demodesafio.aluno.web.dto.AlunoResponseDto;
import org.modelmapper.ModelMapper;

public class AlunoMapper {
    public static Aluno toAluno(AlunoResponseDto createDto) {
        return new ModelMapper().map(createDto, Aluno.class);
    }

    public static AlunoResponseDto toDto(Aluno aluno) {
        return new ModelMapper().map(aluno, AlunoResponseDto.class);
    }
}