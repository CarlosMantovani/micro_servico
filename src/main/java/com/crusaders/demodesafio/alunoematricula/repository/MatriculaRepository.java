package com.crusaders.demodesafio.alunoematricula.repository;

import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.demodesafio.alunoematricula.entities.Matricula;
import com.crusaders.demodesafio.curso.entidade.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    long countByCursoAndStatus(Curso curso, Status status);
    List<Matricula> findByCurso(Curso curso);
}
