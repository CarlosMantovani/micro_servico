package com.crusaders.demodesafio.curso.web.controller;

import com.crusaders.demodesafio.curso.entidade.Curso;
import com.crusaders.demodesafio.curso.service.CursoService;
import com.crusaders.demodesafio.curso.web.dto.CursoProfessorDto;
import com.crusaders.demodesafio.curso.web.dto.CursoResponseDto;
import com.crusaders.demodesafio.curso.web.dto.CursoStatusDto;
import com.crusaders.demodesafio.curso.web.dto.mapper.CursoMapper;
import com.crusaders.demodesafio.curso.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cursos", description = "Contem as operações necessarias para o micro serviço curso.")
@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;
    @Operation(summary = "Criar um novo curso", description = "Recurso para criar um novo curso",
            responses = {
                    @ApiResponse(responseCode = "201",description = "Curso criado com sucesso", content = @Content(mediaType =
                            "application/json", schema =@Schema(implementation = CursoStatusDto.class))),
                    @ApiResponse(responseCode = "409", description = "Curso ja cadsstrado no sistema",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody Curso curso) {
        Curso cursoCadastrado = cursoService.cadastrarCurso(curso);
        return new ResponseEntity<>(cursoCadastrado, HttpStatus.CREATED);
    }

    public ResponseEntity<CursoResponseDto> getById(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        return ResponseEntity.ok(CursoMapper.toDto(curso));
    }
    @Operation(summary = "Alterar o status do curso", description = "Recurso para alterar o status do curso",
            responses = {
                    @ApiResponse(responseCode = "204",description = "Curso alterado com sucesso", content = @Content(mediaType =
                            "application/json", schema =@Schema(implementation = CursoStatusDto.class))),
                    @ApiResponse(responseCode = "404", description = "Id do curso não encontrado",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> alterarStatusCurso(@PathVariable Long id, @RequestBody CursoStatusDto cursoStatusDto) {
        Curso curso = cursoService.alterarStatusCurso(id, cursoStatusDto.getStatus());
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Alterar o nome do professor", description = "Recurso para alterar o nome de um determinado professor em um curso",
            responses = {
                    @ApiResponse(responseCode = "204",description = "Nome do professor alterado com sucesso", content = @Content(mediaType =
                            "application/json", schema =@Schema(implementation = CursoProfessorDto.class))),
                    @ApiResponse(responseCode = "404", description = "Curso não encontrado",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/{id}/professor")
    public ResponseEntity<Void> editarProfessor(@PathVariable Long id, @RequestBody CursoProfessorDto cursoProfessorDto) {
        Curso curso = cursoService.editarProfessor(id, cursoProfessorDto.getNomeProfessor());
        return ResponseEntity.noContent().build();
    }
}
