package com.crusaders.demodesafio.alunoematricula.services;

import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.demodesafio.alunoematricula.entities.Aluno;
import com.crusaders.demodesafio.alunoematricula.entities.Matricula;
import com.crusaders.demodesafio.alunoematricula.repository.MatriculaRepository;
import com.crusaders.demodesafio.alunoematricula.web.dto.MatriculaAlunoDto;
import com.crusaders.demodesafio.curso.entidade.Curso;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoService alunoService;

    public List<MatriculaAlunoDto> findByAluno(Aluno aluno) {
        return matriculaRepository.findByAluno(aluno).stream()
                .map(matricula -> {
                    MatriculaAlunoDto matriculaAlunoDto = new MatriculaAlunoDto();
                    matriculaAlunoDto.setId(matricula.getId());
                    matriculaAlunoDto.setNomeCurso(matricula.getCurso().getNome());
                    matriculaAlunoDto.setNomeAluno((matricula.getAluno().getNome()));
                    return matriculaAlunoDto;
                })
                .collect(Collectors.toList());
    }
    @Transactional
    public Matricula save(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }
    public Matricula buscarPorId(Long id) {
        return matriculaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id))
        );
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
        List<Matricula> matriculas = matriculaRepository.findByCurso(curso);
        if (matriculas.size() >= 10) {
            return false;
        }

        if (matriculas.stream().anyMatch(matricula -> matricula.getAluno().equals(aluno))) {
            return false;
        }

        Matricula matricula = new Matricula();
        matricula.setCurso(curso);
        matricula.setAluno(aluno);
        matricula.setStatus(Status.ATIVO);
        matriculaRepository.save(matricula);

        return true;
    }

    public List<Aluno> listarAlunosMatriculados(Curso curso) {
        List<Matricula> matriculas = matriculaRepository.findByCurso(curso);
        return matriculas.stream().map(Matricula::getAluno).collect(Collectors.toList());
    }

    public void inativarMatriculasPorAluno(Aluno aluno) {
        List<Matricula> matriculas = matriculaRepository.findByAluno(aluno);
        for (Matricula matricula : matriculas) {
            matricula.setStatus(Status.INATIVO);
            save(matricula);
        }
    }

    public List<MatriculaAlunoDto> listarMatriculasPorAluno(Long alunoId) {
        Aluno aluno = alunoService.buscarPorId(alunoId);
        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado");
        }

        return findByAluno(aluno);
    }
}