package com.crusaders.demodesafio.aluno.services;

import com.crusaders.demodesafio.aluno.entities.Aluno;
import com.crusaders.demodesafio.aluno.repository.AlunoRepository;
import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {

    @Autowired
    private final AlunoRepository alunoRepository;

    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Iterable<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

}
