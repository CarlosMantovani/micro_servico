package com.crusaders.services;

import com.crusaders.Enum.Status;
import com.crusaders.entities.Aluno;
import com.crusaders.entities.Matricula;
import com.crusaders.repository.CursoClient;
import com.crusaders.repository.MatriculaRepository;
import com.crusaders.web.dto.CursoRequestDto;
import com.crusaders.web.dto.MatriculaAlunoDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoService alunoService;
    private final CursoClient cursoClient;

    public MatriculaService(MatriculaRepository matriculaRepository, AlunoService alunoService, CursoClient cursoClient) {
        this.matriculaRepository = matriculaRepository;
        this.alunoService = alunoService;
        this.cursoClient = cursoClient;
    }

    public List<MatriculaAlunoDto> findByAluno(Aluno aluno) {
        return matriculaRepository.findByAluno(aluno).stream()
                .map(matricula -> {
                    MatriculaAlunoDto matriculaAlunoDto = new MatriculaAlunoDto();
                    matriculaAlunoDto.setId(matricula.getId());
                    CursoRequestDto curso = cursoClient.buscarPorId(matricula.getCursoId());
                    matriculaAlunoDto.setNomeCurso(curso.getNome());
                    matriculaAlunoDto.setNomeAluno(matricula.getAluno().getNome());
                    return matriculaAlunoDto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public Matricula save(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    public MatriculaAlunoDto buscarPorId(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Matricula id=%s não encontrada", id)));

        CursoRequestDto curso = cursoClient.buscarPorId(matricula.getCursoId());

        MatriculaAlunoDto matriculaAlunoDto = new MatriculaAlunoDto();
        matriculaAlunoDto.setId(matricula.getId());
        matriculaAlunoDto.setNomeAluno(matricula.getAluno().getNome());
        matriculaAlunoDto.setNomeCurso(curso.getNome());

        return matriculaAlunoDto;
    }

    @Transactional(readOnly = true)
    public long countByCursoAndStatus(CursoRequestDto curso, Status status) {
        return matriculaRepository.countByCursoIdAndStatus(curso.getId(), status);
    }

    @Transactional(readOnly = true)
    public List<Matricula> findByCurso(CursoRequestDto curso) {
        return matriculaRepository.findByCursoId(curso.getId());
    }

    @Transactional
    public boolean matricularAluno(Long cursoId, Long idAluno) {
        CursoRequestDto curso = cursoClient.buscarPorId(cursoId);
        Aluno aluno = alunoService.buscarPorId(idAluno);
        if (curso == null) {
            throw new RuntimeException("Curso não encontrado");
        }

        List<Matricula> matriculas = matriculaRepository.findByCursoId(cursoId);
        if (matriculas.size() >= 10) {
            return false;
        }

        if (matriculas.stream().anyMatch(matricula -> matricula.getAluno().equals(aluno))) {
            return false;
        }

        Matricula matricula = new Matricula();
        matricula.setCursoId(cursoId);
        matricula.setAluno(aluno);
        matricula.setStatus(Status.ATIVO);
        matriculaRepository.save(matricula);

        return true;
    }

    public List<Aluno> listarAlunosMatriculados(CursoRequestDto curso) {
        List<Matricula> matriculas = matriculaRepository.findByCursoId(curso.getId());
        return matriculas.stream().map(Matricula::getAluno).collect(Collectors.toList());
    }

    public void inativarMatriculasPorAluno(Aluno aluno) {
        List<Matricula> matriculas = matriculaRepository.findByAluno(aluno);
        for (Matricula matricula : matriculas) {
            matricula.setStatus(Status.INATIVO);
            save(matricula);
        }
    }

    public List<MatriculaAlunoDto> listarMatriculasPorAluno(Long alunoId) {
        Aluno aluno = alunoService.buscarPorId(alunoId);
        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado");
        }

        return findByAluno(aluno);
    }
}
