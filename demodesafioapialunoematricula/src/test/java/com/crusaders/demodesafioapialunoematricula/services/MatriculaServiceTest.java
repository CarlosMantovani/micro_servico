package com.crusaders.demodesafioapialunoematricula.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@DataJpaTest
@ActiveProfiles("test")
class MatriculaServiceTest {

    @Test
    void save() {
    }

    @Test
    void countByCursoAndStatus() {
    }

    @Test
    void findByCurso() {
    }

    @Test
    void matricularAluno() {
    }
}