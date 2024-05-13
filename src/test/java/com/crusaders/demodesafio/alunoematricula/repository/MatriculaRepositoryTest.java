package com.crusaders.demodesafio.alunoematricula.repository;

import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.demodesafio.alunoematricula.entities.Matricula;
import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.repository.CursoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class MatriculaRepositoryTest {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    @DisplayName("Deve contar o número de matrículas ativas para um curso específico")
    void countByCursoAndStatus_DeveContarMatriculasAtivasParaCursoEspecifico() {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Programação Java");
        curso.setQtdHoras("150");
        curso.setProfessor("Claudio");
        curso.setAreaConhecimento("Técnico");
        curso.setStatus(Status.ATIVO);
        curso = cursoRepository.save(curso);

        Matricula matriculaAtiva = new Matricula();
        matriculaAtiva.setCurso(curso);
        matriculaAtiva.setStatus(Status.ATIVO);
        matriculaRepository.save(matriculaAtiva);

        Matricula matriculaInativa = new Matricula();
        matriculaInativa.setCurso(curso);
        matriculaInativa.setStatus(Status.INATIVO);
        matriculaRepository.save(matriculaInativa);

        long countAtivo = matriculaRepository.countByCursoAndStatus(curso, Status.ATIVO);
        assertThat(countAtivo).isEqualTo(1);
    }

    @Test
    @DisplayName("Deve retornar uma lista de matrículas para um curso específico")
    void findByCurso_DeveRetornarListaDeMatriculasParaCursoEspecifico() {

        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Programação Java");
        curso.setQtdHoras("150");
        curso.setProfessor("Claudio");
        curso.setAreaConhecimento("Técnico");
        curso.setStatus(Status.ATIVO);
        curso = cursoRepository.save(curso);

        Matricula matricula = new Matricula();
        matricula.setCurso(curso);
        matriculaRepository.save(matricula);

        Matricula matricula2 = new Matricula();
        matricula2.setCurso(curso);
        matriculaRepository.save(matricula2);

        List<Matricula> matriculasEncontradas = matriculaRepository.findByCurso(curso);

        assertThat(matriculasEncontradas).hasSize(2);
    }
}