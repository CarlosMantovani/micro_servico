package com.crusaders.demodesafio.curso.service;

import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.exception.CursoIdNaoEncontrado;
import com.crusaders.demodesafio.curso.repository.CursoRepository;
import com.crusaders.demodesafio.curso.exception.CursoIdDuplicadoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    @Transactional
    public Curso cadastrarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
    @Transactional
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow(
                () -> new CursoIdNaoEncontrado(String.format("Curso id=%s não encontrado", id))
        );
    }
    public Curso alterarStatusCurso(Long id, Status status) {
        Curso curso = buscarPorId(id);
        curso.setStatus(status);
        return cursoRepository.save(curso);
    }

    public Curso editarProfessor (Long id, String nomeProfessor) {
        Curso curso = buscarPorId(id);
        curso.setProfessor(nomeProfessor);
        return cursoRepository.save(curso);
    }
}
