package com.crusaders.demodesafio.aluno.web.dto;

import lombok.NonNull;

import java.util.Date;

public class AlunoCreateDto {
    @NonNull
    private String nome;
    @NonNull
    private String cpf;
    @NonNull
    private Date dataNascimento;
    private String professor;
}
