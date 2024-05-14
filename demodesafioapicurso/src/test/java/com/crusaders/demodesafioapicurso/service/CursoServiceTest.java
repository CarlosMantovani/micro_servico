package com.crusaders.demodesafioapicurso.service;

import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.entidade.Curso;
import com.crusaders.repository.CursoRepository;
import com.crusaders.service.CursoService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DataJpaTest
@ActiveProfiles("test")
class CursoServiceTest {

    @Autowired
    CursoRepository cursoRepository;

    private CursoService cursoService;

    @BeforeEach
    void setUp() {
        cursoService = new CursoService(cursoRepository);
    }

    private Curso criarCurso(String nome, String qtdHoras, String professor, String areaConhecimento) {
        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setQtdHoras(qtdHoras);
        curso.setProfessor(professor);
        curso.setAreaConhecimento(areaConhecimento);
        return curso;
    }

    @Test
    @DisplayName("Teste para criar curso")
    void cadastrarCurso() {
        Curso curso = new Curso();
        curso.setNome("Psicologia");
        curso.setQtdHoras("200");
        curso.setProfessor("Hermes");
        curso.setAreaConhecimento("Ciências Humanas");

        Curso cursoCriado = cursoService.cadastrarCurso(curso);

        assertThat(cursoCriado).isNotNull();
        assertThat(cursoCriado.getId()).isNotNull();

        assertThat(cursoCriado.getId()).isNotNull();
        assertThat(cursoCriado.getNome()).isEqualTo(curso.getNome());
        assertThat(cursoCriado.getQtdHoras()).isEqualTo(curso.getQtdHoras());
        assertThat(cursoCriado.getProfessor()).isEqualTo(curso.getProfessor());
        assertThat(cursoCriado.getAreaConhecimento()).isEqualTo(curso.getAreaConhecimento());
    }

    @Test
    @DisplayName("Teste para listar todos os cursos")
    void listarCursos() {

        List<Curso> cursosSalvos = new ArrayList<>();
        Curso curso1 = criarCurso("Curso 1", "100", "Hermes", "Ciências Humanas");
        cursosSalvos.add(cursoRepository.save(curso1));

        Curso curso2 = criarCurso("Curso 2", "200", "Carlos", "Ciências Humanas");
        cursosSalvos.add(cursoRepository.save(curso2));

        Curso curso3 = criarCurso("Curso 3", "100", "Thomas", "Ciências Humanas");
        cursosSalvos.add(cursoRepository.save(curso3));

        List<Curso> cursos = cursoService.listarCursos();

        assertThat(cursos).isNotNull().hasSize(3).containsExactlyInAnyOrderElementsOf(cursosSalvos);
    }

    @Test
    @DisplayName("Teste para buscar por id")
    void buscarPorId() {
        Curso curso = criarCurso("Curso 1", "100", "Hermes", "Ciências Humanas");
        Curso cursoSalvo = cursoRepository.save(curso);

        Curso cursoEncontrado = cursoService.buscarPorId(cursoSalvo.getId());

        assertThat(cursoEncontrado).isNotNull();
        assertThat(cursoEncontrado.getId()).isEqualTo(cursoSalvo.getId());
        assertThat(cursoEncontrado.getNome()).isEqualTo(cursoSalvo.getNome());
    }

    @Test
    @DisplayName("Teste para alterar status do curso")
    void alterarStatusCurso() {
        Curso curso = criarCurso("Curso 1", "100", "Hermes", "Cieências Humanas");
        Curso cursoSalvo = cursoRepository.save(curso);
        Status novoStatus = Status.INATIVO;

        Curso cursoAlterado = cursoService.alterarStatusCurso(cursoSalvo.getId(), novoStatus);

        assertThat(cursoAlterado).isNotNull();
        assertThat(cursoAlterado.getId()).isEqualTo(cursoSalvo.getId());
        assertThat(cursoAlterado.getStatus()).isEqualTo(novoStatus);
    }

    @Test
    @DisplayName("Teste para editar professor do curso")
    void editarProfessor() {
        Curso curso = criarCurso("Curso 1", "100", "Hermes", "Cieências Humanas");
        Curso cursoSalvo = cursoRepository.save(curso);
        String novoProfessor = "Novo Professor";

        Curso cursoEditado = cursoService.editarProfessor(cursoSalvo.getId(), novoProfessor);

        assertThat(cursoEditado).isNotNull();
        assertThat(cursoEditado.getId()).isEqualTo(cursoSalvo.getId());
        assertThat(cursoEditado.getProfessor()).isEqualTo(novoProfessor);
    }
}