package com.crusaders.demodesafio.curso.service;

import com.crusaders.demodesafio.curso.repository.CursoRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@DataJpaTest
@ActiveProfiles("test")
class CursoServiceTest {

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Teste para criar curso")
    void cadastrarCurso() {
    }

    @Test
    @DisplayName("Teste para listar todos os cursos")
    void listarCursos() {
    }

    @Test
    @DisplayName("Teste para buscar por id")
    void buscarPorId() {
    }

    @Test
    @DisplayName("Teste para alterar status do curso")
    void alterarStatusCurso() {
    }

    @Test
    @DisplayName("Teste para editar professor do curso")
    void editarProfessor() {
    }
}