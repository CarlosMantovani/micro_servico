package com.crusaders.demodesafioapicurso.repository;

import com.crusaders.Enum.Status;
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

        Curso cursoSalvo = cursoRepository.save(curso);

        assertThat(cursoSalvo).isNotNull();
        assertThat(cursoSalvo.getId()).isNotNull();
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

        Curso cursoSalvo = cursoRepository.save(curso);

        Curso encontrarCurso = cursoRepository.findById(cursoSalvo.getId()).orElse(null);

        assertThat(encontrarCurso).isNotNull();
        assertThat(encontrarCurso.getId()).isEqualTo(cursoSalvo.getId());
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

        Curso cursoSalvo = cursoRepository.save(curso);

        cursoSalvo.setProfessor("Matheus");

        Curso cursoAtualizado = cursoRepository.save(cursoSalvo);

        assertThat(cursoAtualizado).isNotNull();
        assertThat(cursoAtualizado.getProfessor()).isEqualTo("Matheus");
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

        Curso cursoSalvo = cursoRepository.save(curso);

        cursoRepository.delete(cursoSalvo);

        assertThat(cursoRepository.findById(cursoSalvo.getId())).isEmpty();
    }
}