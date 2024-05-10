package com.crusaders.demodesafio.alunoematricula.services;
import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.demodesafio.alunoematricula.entities.Aluno;
import com.crusaders.demodesafio.alunoematricula.entities.Matricula;
import com.crusaders.demodesafio.alunoematricula.repository.MatriculaRepository;
import com.crusaders.demodesafio.curso.entidade.Curso;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    @Transactional
    public Matricula save(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Transactional(readOnly = true)
    public long countByCursoAndStatus(Curso curso, Status status) {
        return matriculaRepository.countByCursoAndStatus(curso, status);
    }

    @Transactional(readOnly = true)
    public List<Matricula> findByCurso(Curso curso) {
        return matriculaRepository.findByCurso(curso);
    }

    @Transactional
    public boolean matricularAluno(Curso curso, Aluno aluno) {
        long countMatriculas = countByCursoAndStatus(curso, Status.ATIVO);
        if (countMatriculas < 10) {
            Matricula matricula = new Matricula();
            matricula.setCurso(curso);
            matricula.setAluno(aluno);
            matricula.setStatus(Status.ATIVO);
            save(matricula);
            return true;
        }
        return false;
    }
}
