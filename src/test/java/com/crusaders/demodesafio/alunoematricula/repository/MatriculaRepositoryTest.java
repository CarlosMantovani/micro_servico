package com.crusaders.demodesafio.alunoematricula.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class MatriculaRepositoryTest {

    @Test
    void countByCursoAndStatus() {
    }

    @Test
    void findByCurso() {
    }
}