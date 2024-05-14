package com.crusaders.demodesafioapialunoematricula.services;

import com.crusaders.Enum.Status;
import com.crusaders.entities.Aluno;
import com.crusaders.repository.AlunoRepository;
import com.crusaders.services.AlunoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DataJpaTest
@ActiveProfiles("test")
class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AlunoRepository alunoRepository;

    @BeforeEach
    void setUp() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João");
        aluno.setCpf("12345678900");
        aluno.setDataNascimento("01/01/2000");
        aluno.setSexo("Masculino");
        aluno.setStatus(Status.ATIVO);
        alunoRepository.save(aluno);
    }

    @Test
    @DisplayName("Retornar se está salvando um novo aluno no banco de dados")
    void cadastrarAluno_DeveSalvarNovoAluno() {
        Aluno aluno = new Aluno();
        aluno.setId(2L);
        aluno.setNome("Maria");
        aluno.setCpf("12345678901");
        aluno.setDataNascimento("02/02/2002");
        aluno.setSexo("Feminino");
        aluno.setStatus(Status.ATIVO);

        Aluno alunoSalvo = alunoService.cadastrarAluno(aluno);

        assertThat(alunoSalvo).isNotNull();
        assertThat(alunoSalvo.getId()).isNotNull();
    }

    @Test
    @DisplayName("Retornar uma lista não vazia de alunos")
    void listarAlunos_DeveRetornarListaNaoVazia() {
      List<Aluno> alunos = (List<Aluno>) alunoService.listarAlunos();

        assertThat(alunos).isNotEmpty();
    }

    private Aluno criarAluno(Long id) {
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setNome("João");
        aluno.setCpf("12345678900");
        aluno.setDataNascimento("01/01/2000");
        aluno.setSexo("Masculino");
        aluno.setStatus(Status.ATIVO);
        return aluno;
    }

    @Test
    @DisplayName("Retorna se está alterando corretamente o status de um aluno")
    void alterarStatusAluno_DeveAlterarStatusCorretamente() {
        Long alunoId = 2L;
        Status novoStatus = Status.INATIVO;

        Aluno alunoAtualizado = alunoService.alterarStatusAluno(alunoId, novoStatus);

        assertThat(alunoAtualizado).isNotNull();
        assertThat(alunoAtualizado.getStatus()).isEqualTo(novoStatus);

        Aluno alunoNoRepositorio = alunoRepository.findById(alunoId)
                .orElseThrow(EntityNotFoundException::new);
        assertThat(alunoNoRepositorio.getStatus()).isEqualTo(novoStatus);
    }
}