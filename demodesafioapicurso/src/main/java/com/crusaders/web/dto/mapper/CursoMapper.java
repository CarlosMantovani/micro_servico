package com.crusaders.web.dto.mapper;

import com.crusaders.entidade.Curso;
import com.crusaders.web.dto.CursoResponseDto;
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

