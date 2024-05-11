package com.crusaders.demodesafio.alunoematricula.repository;

import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.demodesafio.alunoematricula.entities.Aluno;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class AlunoRepositoryTest {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Deve retornar true se o CPF existir no BD")
    void alunoExisteCaso1() {
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
    @DisplayName("Deve retornar false se o CPF não existir no BD")
    void alunoExisteCaso2() {
        boolean result = this.alunoRepository.existsByCpf("CPF_QUE_NAO_EXISTE");
        assertThat(result).isFalse();
    }

    private Aluno createAluno(Aluno aluno) {
        this.entityManager.persist(aluno);
        return aluno;
    }
}