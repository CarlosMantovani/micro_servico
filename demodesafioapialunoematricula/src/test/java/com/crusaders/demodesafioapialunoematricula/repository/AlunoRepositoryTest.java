package com.crusaders.demodesafioapialunoematricula.repository;

import com.crusaders.Enum.Status;
import com.crusaders.entities.Aluno;
import com.crusaders.repository.AlunoRepository;
import jakarta.persistence.EntityManager;
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
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Deve retornar true se o CPF existir no banco de dados")
    void existsByCpfCaso1_DeveRetornarTrueSeCpfExistirNoBD() {
        Aluno aluno = new Aluno();
        aluno.setNome("Maria");
        aluno.setCpf("96199911091");
        aluno.setDataNascimento("12/05/1989");
        aluno.setSexo("FEMININO");
        aluno.setStatus(Status.ATIVO);
        this.createAluno(aluno);

        boolean result = this.alunoRepository.existsByCpf("96199911091");
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false se o CPF não existir no banco de dados")
    void existsByCpfCaso2_DeveRetornarFalseSeCpfNaoExistirNoBD() {
        boolean result = this.alunoRepository.existsByCpf("CPF_QUE_NAO_EXISTE");
        assertThat(result).isFalse();
    }

    private Aluno createAluno(Aluno aluno) {
        this.entityManager.persist(aluno);
        return aluno;
    }
}