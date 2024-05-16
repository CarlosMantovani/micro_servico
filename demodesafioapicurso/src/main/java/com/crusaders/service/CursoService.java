package com.crusaders.service;

import com.crusaders.Enum.Status;
import com.crusaders.entidade.Curso;
import com.crusaders.exception.CursoIdNaoEncontrado;
import com.crusaders.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

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
