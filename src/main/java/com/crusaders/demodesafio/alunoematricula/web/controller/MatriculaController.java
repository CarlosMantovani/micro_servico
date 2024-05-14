package com.crusaders.web.controller;

import com.crusaders.demodesafio.alunoematricula.entities.Aluno;
import com.crusaders.demodesafio.alunoematricula.services.AlunoService;
import com.crusaders.demodesafio.alunoematricula.entities.Matricula;
import com.crusaders.demodesafio.alunoematricula.services.MatriculaService;
import com.crusaders.demodesafio.alunoematricula.web.dto.MatriculaAlunoDto;
import com.crusaders.demodesafio.alunoematricula.web.dto.MatriculaRequestDto;
import com.crusaders.demodesafio.alunoematricula.web.dto.MatriculaResponseDto;
import com.crusaders.demodesafio.alunoematricula.web.dto.mapper.AlunoMapper;
import com.crusaders.demodesafio.alunoematricula.web.dto.mapper.MatriculaMapper;
import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.service.CursoService;
import com.crusaders.demodesafio.curso.web.dto.mapper.CursoMapper;
import com.crusaders.demodesafio.curso.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Tag(name = "Matricula", description = "Contem as operações necessarias para o micro serviço matricula.")
@RestController
@RequestMapping("/api/v1/matriculas")
@AllArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final AlunoService alunoService;
    private final CursoService cursoService;
    @Operation(summary = "Criar uma nova matricula", description = "Recurso para criar uma nova matricula",
            responses = {
                    @ApiResponse(responseCode = "201",description = "Curso criado com sucesso", content = @Content(mediaType =
                            "application/json", schema =@Schema(implementation = MatriculaRequestDto.class))),
                    @ApiResponse(responseCode = "409", description = "Matricula ja cadastrada no sistema",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Aluno ou matricula não encontrado",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<Void> matricularAluno(@RequestBody MatriculaRequestDto requestDto) {
        Curso curso = cursoService.buscarPorId(requestDto.getCursoId());
        Aluno aluno = alunoService.buscarPorId(requestDto.getAlunoId());

        if (curso == null || aluno == null) {
            return ResponseEntity.notFound().build();
        }

        boolean matriculado = matriculaService.matricularAluno(curso, aluno);

        if (matriculado) {
            List<Aluno> alunosMatriculados = matriculaService.listarAlunosMatriculados(curso);
            int totalAlunos = alunosMatriculados.size();

            MatriculaResponseDto responseDto = new MatriculaResponseDto();
            responseDto.setCurso(CursoMapper.toDto(curso));
            responseDto.setAlunos(alunosMatriculados.stream().map(AlunoMapper::toDto).collect(Collectors.toList()));
            responseDto.setTotalAlunos(totalAlunos);

            return ResponseEntity.status(HttpStatus.CREATED).body(null);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Buscar aluno", description = "Recurso para encontrar um aluno",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Recurso encontrado", content = @Content(mediaType =
                            "application/json", schema =@Schema(implementation = MatriculaAlunoDto.class))),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<MatriculaAlunoDto>> listarMatriculasPorAluno(@PathVariable Long alunoId) {
        List<MatriculaAlunoDto> responseDtoList = matriculaService.listarMatriculasPorAluno(alunoId);
        return ResponseEntity.ok(responseDtoList);
    }
    @Operation(summary = "Buscar Matriculado no curso", description = "Recurso para encontrar um Matricula",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Recurso encontrado", content = @Content(mediaType =
                            "application/json", schema =@Schema(implementation = MatriculaAlunoDto.class))),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaAlunoDto> getById(@PathVariable Long id) {
        Matricula matricula = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(MatriculaMapper.toDtoId(matricula));
    }
}