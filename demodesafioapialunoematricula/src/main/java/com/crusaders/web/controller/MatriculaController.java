package com.crusaders.web.controller;

import com.crusaders.entities.Aluno;
import com.crusaders.repository.CursoClient;
import com.crusaders.services.AlunoService;
import com.crusaders.services.MatriculaService;
import com.crusaders.web.dto.*;
import com.crusaders.web.dto.mapper.AlunoMapper;
import com.crusaders.web.exception.ErrorMessage;
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
@Tag(name = "Matriculas ", description = "Contem as operações necessarias para o micro serviço matricula.")
@RestController
@RequestMapping("/api/v1/matriculas")
@AllArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final AlunoService alunoService;

    private final CursoClient cursoClient;
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
    public ResponseEntity<?> matricularAluno(@RequestBody MatriculaRequestDto requestDto) {
        Aluno aluno = alunoService.buscarPorId(requestDto.getAlunoId());
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        boolean matriculado = matriculaService.matricularAluno(requestDto.getCursoId(), requestDto.getAlunoId());

        if (matriculado) {
            CursoRequestDto curso = cursoClient.buscarPorId(requestDto.getCursoId());
            List<Aluno> alunosMatriculados = matriculaService.listarAlunosMatriculados(curso);
            int totalAlunos = alunosMatriculados.size();

            MatriculaResponseDto responseDto = new MatriculaResponseDto();
            responseDto.setCurso(curso);
            responseDto.setAlunos(alunosMatriculados.stream().map(AlunoMapper::toDto).collect(Collectors.toList()));
            responseDto.setTotalAlunos(totalAlunos);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } else {
            return ResponseEntity.badRequest().build();
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
                    @ApiResponse(responseCode = "404", description = "ID da matricula não encontrado",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaAlunoDto> getById(@PathVariable Long id) {
        MatriculaAlunoDto matriculaAlunoDto = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matriculaAlunoDto);
    }
}
