package com.crusaders.demodesafio.curso.service;

import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Curso cadastrarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
    @Transactional
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id))
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
