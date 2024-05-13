package com.crusaders.demodesafio.alunoematricula.repository;

import com.crusaders.demodesafio.alunoematricula.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByCpf(String cpf);
}
