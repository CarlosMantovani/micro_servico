package com.crusaders.demodesafioapicurso.repository;

import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.entidade.Curso;
import com.crusaders.repository.CursoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DataJpaTest
@ActiveProfiles("test")
class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    @DisplayName("Deve salvar um curso corretamente")
    void saveCurso_DeveRetornarCursoSalvo() {
        Curso curso = new Curso();
        curso.setNome("Programação Java");
        curso.setQtdHoras("150");
        curso.setProfessor("Claudio");
        curso.setAreaConhecimento("Técnico");
        curso.setStatus(Status.ATIVO);

        Curso savedCurso = cursoRepository.save(curso);

        assertThat(savedCurso).isNotNull();
        assertThat(savedCurso.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve encontrar um curso pelo ID")
    void findCursoById_DeveRetornarCursoEncontrado() {
        Curso curso = new Curso();
        curso.setNome("Programação Java");
        curso.setQtdHoras("150");
        curso.setProfessor("Claudio");
        curso.setAreaConhecimento("Técnico");
        curso.setStatus(Status.ATIVO);

        Curso savedCurso = cursoRepository.save(curso);

        Curso foundCurso = cursoRepository.findById(savedCurso.getId()).orElse(null);

        assertThat(foundCurso).isNotNull();
        assertThat(foundCurso.getId()).isEqualTo(savedCurso.getId());
    }

    @Test
    @DisplayName("Deve atualizar um curso corretamente")
    void updateCurso_DeveRetornarCursoAtualizado() {
        Curso curso = new Curso();
        curso.setNome("Programação Java");
        curso.setQtdHoras("150");
        curso.setProfessor("Claudio");
        curso.setAreaConhecimento("Técnico");
        curso.setStatus(Status.ATIVO);

        Curso savedCurso = cursoRepository.save(curso);

        savedCurso.setProfessor("Matheus");

        Curso updatedCurso = cursoRepository.save(savedCurso);

        assertThat(updatedCurso).isNotNull();
        assertThat(updatedCurso.getProfessor()).isEqualTo("Matheus");
    }

    @Test
    @DisplayName("Deve excluir um curso corretamente")
    void deleteCurso_DeveExcluirCurso() {
        Curso curso = new Curso();
        curso.setNome("Programação Java");
        curso.setQtdHoras("150");
        curso.setProfessor("Claudio");
        curso.setAreaConhecimento("Técnico");
        curso.setStatus(Status.ATIVO);

        Curso savedCurso = cursoRepository.save(curso);

        cursoRepository.delete(savedCurso);

        assertThat(cursoRepository.findById(savedCurso.getId())).isEmpty();
    }
}