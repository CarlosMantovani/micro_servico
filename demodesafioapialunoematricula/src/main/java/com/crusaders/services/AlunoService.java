package com.crusaders.services;

import com.crusaders.Enum.Status;
import com.crusaders.entities.Aluno;
import com.crusaders.entities.Matricula;
import com.crusaders.exception.IdAlunoNaoEncontradoException;
import com.crusaders.repository.AlunoRepository;
import com.crusaders.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    @Autowired
    private final AlunoRepository alunoRepository;

    @Autowired
    private final MatriculaRepository matriculaRepository;


    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Iterable<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }
    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElseThrow(
                () -> new IdAlunoNaoEncontradoException(String.format("Usuário id=%s não encontrado", id))
        );
    }
    @Transactional
    public Aluno alterarStatusAluno(Long id, Status status) {
        Aluno aluno = buscarPorId(id);
        aluno.setStatus(status);
        alunoRepository.save(aluno);

        if (status == Status.INATIVO) {
            inativarMatriculasPorAluno(aluno);
        }

        return aluno;
    }

    @Transactional
    public void inativarMatriculaDoAluno(Long alunoId, Long matriculaId) {
        Aluno aluno = buscarPorId(alunoId);
        Matricula matricula = matriculaRepository.findByIdAndAluno(matriculaId, aluno);

        matricula.setStatus(Status.INATIVO);
        matriculaRepository.save(matricula);
    }

    @Transactional
    public void inativarMatriculasPorAluno(Aluno aluno) {
        List<Matricula> matriculas = matriculaRepository.findByAluno(aluno);
        for (Matricula matricula : matriculas) {
            matricula.setStatus(Status.INATIVO);
            matriculaRepository.save(matricula);
        }
    }
}
