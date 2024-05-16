package com.crusaders.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlunoCreateDto {
    @NonNull
    private String nome;
    @NonNull
    private String cpf;
    @NonNull
    private String dataNascimento;
    @NonNull
    private String sexo;
}
