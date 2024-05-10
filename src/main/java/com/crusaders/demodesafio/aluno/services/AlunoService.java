package com.crusaders.demodesafio.aluno.services;

import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.demodesafio.aluno.entities.Aluno;
import com.crusaders.demodesafio.aluno.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id))
        );
    }
    public Aluno alterarStatusAluno(Long id, Status status) {
        Aluno aluno = buscarPorId(id);
        aluno.setStatus(status);
        return alunoRepository.save(aluno);
    }

}
