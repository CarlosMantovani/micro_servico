package com.crusaders.web.dto.mapper;

import com.crusaders.entities.Aluno;
import com.crusaders.web.dto.AlunoResponseDto;
import org.modelmapper.ModelMapper;

public class AlunoMapper {
    public static Aluno toAluno(AlunoResponseDto createDto) {
        return new ModelMapper().map(createDto, Aluno.class);
    }

    public static AlunoResponseDto toDto(Aluno aluno) {
        return new ModelMapper().map(aluno, AlunoResponseDto.class);
    }
}