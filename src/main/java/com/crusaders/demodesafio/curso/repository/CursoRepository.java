package com.crusaders.demodesafio.curso.repository;

import com.crusaders.demodesafio.curso.entidade.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
