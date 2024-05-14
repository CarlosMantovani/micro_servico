//package com.crusaders.demodesafioapialunoematricula.repository;
//
//import com.crusaders.Enum.Status;
//import com.crusaders.entities.Matricula;
//import com.crusaders.repository.CursoClient;
//import com.crusaders.repository.MatriculaRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Transactional
//@DataJpaTest
//@ActiveProfiles("test")
//class MatriculaRepositoryTest {
//
//    @Autowired
//    private MatriculaRepository matriculaRepository;
//
//    @Autowired
//    private CursoClient cursoClient;
//
//    @Test
//    @DisplayName("Deve contar o número de matrículas ativas para um curso específico")
//    void countByCursoAndStatus_DeveContarMatriculasAtivasParaCursoEspecifico() {
//        Curso curso = new Curso();
//        curso.setId(1L);
//        curso.setNome("Programação Java");
//        curso.setQtdHoras("150");
//        curso.setProfessor("Claudio");
//        curso.setAreaConhecimento("Técnico");
//        curso.setStatus(Status.ATIVO);
//        curso = cursoRepository.save(curso);
//
//        Matricula matriculaAtiva = new Matricula();
//        matriculaAtiva.setCursoId(curso.getId());
//        matriculaAtiva.setStatus(Status.ATIVO);
//        matriculaRepository.save(matriculaAtiva);
//
//        Matricula matriculaInativa = new Matricula();
//        matriculaInativa.setCursoId(curso.getId());
//        matriculaInativa.setStatus(Status.INATIVO);
//        matriculaRepository.save(matriculaInativa);
//
//        long countAtivo = matriculaRepository.countByCursoIdAndStatus(curso.getId(), Status.ATIVO);
//        assertThat(countAtivo).isEqualTo(1);
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma lista de matrículas para um curso específico")
//    void findByCurso_DeveRetornarListaDeMatriculasParaCursoEspecifico() {
//
//        Curso curso = new Curso();
//        curso.setId(1L);
//        curso.setNome("Programação Java");
//        curso.setQtdHoras("150");
//        curso.setProfessor("Claudio");
//        curso.setAreaConhecimento("Técnico");
//        curso.setStatus(Status.ATIVO);
//        curso = cursoRepository.save(curso);
//
//        Matricula matricula = new Matricula();
//        matricula.setCursoId(curso.getId());
//        matriculaRepository.save(matricula);
//
//        Matricula matricula2 = new Matricula();
//        matricula2.setCursoId(curso.getId());
//        matriculaRepository.save(matricula2);
//
//        List<Matricula> matriculasEncontradas = matriculaRepository.findByCursoId(curso.getId());
//
//        assertThat(matriculasEncontradas).hasSize(2);
//    }
//}