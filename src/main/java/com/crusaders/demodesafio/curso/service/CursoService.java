package com.crusaders.demodesafio.curso.service;

import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Curso cadastrarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Iterable<Curso> listarCursos() {
        return cursoRepository.findAll();
    }
}