package com.crusaders.demodesafio.curso.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CursoCreateDto {
    @NonNull
    private String nome;
    @NonNull
    private String qtdHoras;
    @NonNull
    private String areaConhecimento;
    @NonNull
    private String professor;
}
