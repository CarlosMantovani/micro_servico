package com.crusaders.demodesafio.aluno.controller;

import com.crusaders.demodesafio.aluno.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {

    private AlunoService alunoService;
}
