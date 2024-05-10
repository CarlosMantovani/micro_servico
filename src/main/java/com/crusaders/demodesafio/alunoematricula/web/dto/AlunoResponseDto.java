package com.crusaders.demodesafio.alunoematricula.web.dto;

import com.crusaders.demodesafio.Enum.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlunoResponseDto {
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String sexo;
    private Status status;
}
