package com.crusaders.web.dto;

import com.crusaders.Enum.Status;
import lombok.*;

@Getter
@Setter
public class CursoResponseDto {
    private Long id;
    private String nome;
    private String qtdHoras;
    private String areaConhecimento;
    private String professor;
    private Status status;
}
