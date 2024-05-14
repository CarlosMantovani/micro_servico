package com.crusaders.web.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CursoRequestDto {
    @NonNull
    private Long id;
    @NonNull
    private String nome;
    @NonNull
    private String qtdHoras;
    @NonNull
    private String areaConhecimento;
    @NonNull
    private String professor;
}
