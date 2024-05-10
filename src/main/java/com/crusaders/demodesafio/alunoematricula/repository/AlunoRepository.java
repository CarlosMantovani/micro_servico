package com.crusaders.demodesafio.alunoematricula.repository;

import com.crusaders.demodesafio.alunoematricula.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
