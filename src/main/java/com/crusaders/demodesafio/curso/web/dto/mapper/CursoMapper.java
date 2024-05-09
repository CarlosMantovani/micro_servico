package com.crusaders.demodesafio.curso.web.dto.mapper;

import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.web.dto.CursoResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class CursoMapper {
        public static Curso toCurso(CursoResponseDto createDto) {
            return new ModelMapper().map(createDto, Curso.class);
        }

        public static CursoResponseDto toDto(Curso curso) {
            return new ModelMapper().map(curso, CursoResponseDto.class);
        }
    }

