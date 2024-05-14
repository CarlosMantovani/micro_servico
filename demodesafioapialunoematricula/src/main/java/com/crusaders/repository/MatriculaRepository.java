package com.crusaders.repository;

import com.crusaders.Enum.Status;
import com.crusaders.entities.Aluno;
import com.crusaders.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    long countByCursoIdAndStatus(Long cursoId, Status status);
    List<Matricula> findByCursoId(Long cursoId);
    List<Matricula> findByAluno(Aluno aluno);
}