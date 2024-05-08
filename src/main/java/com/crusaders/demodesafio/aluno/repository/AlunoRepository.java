package com.crusaders.demodesafio.aluno.repository;

import com.crusaders.demodesafio.aluno.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
