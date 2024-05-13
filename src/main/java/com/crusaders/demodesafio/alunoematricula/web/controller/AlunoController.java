package com.crusaders.demodesafio.alunoematricula.web.controller;

import com.crusaders.demodesafio.alunoematricula.entities.Aluno;
import com.crusaders.demodesafio.alunoematricula.services.AlunoService;
import com.crusaders.demodesafio.alunoematricula.web.dto.AlunoCreateDto;
import com.crusaders.demodesafio.alunoematricula.web.dto.AlunoResponseDto;
import com.crusaders.demodesafio.alunoematricula.web.dto.mapper.AlunoMapper;
import com.crusaders.demodesafio.curso.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    @Operation(summary = "Criar um novo aluno", description = "Recurso para criar uma novo aluno",
            responses = {
                    @ApiResponse(responseCode = "201",description = "Curso criado com sucesso", content = @Content(mediaType =
                            "application/json", schema =@Schema(implementation = AlunoCreateDto.class))),
                    @ApiResponse(responseCode = "409", description = "Aluno ja cadastrado no sistema",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        Aluno alunoCadastrado = alunoService.cadastrarAluno(aluno);
        return new ResponseEntity<>(alunoCadastrado, HttpStatus.CREATED);
    }
    @Operation(summary = "Listar todos os alunos", description = "Recurso para listar todos os alunos",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK", content = @Content(mediaType =
                            "application/json", schema =@Schema(implementation = AlunoCreateDto.class))),
            })
    @GetMapping
    public ResponseEntity<Iterable<Aluno>> listarAlunos() {
        Iterable<Aluno> alunos = alunoService.listarAlunos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
    @Operation(summary = "Busca por aluno", description = "Recurso para busca por aluno",
            responses = {
                    @ApiResponse(responseCode = "204",description = "Aluno encotrado com sucesso", content = @Content(mediaType =
                            "application/json", schema =@Schema(implementation = AlunoResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Id do curso não encontrado",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> getById(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        return ResponseEntity.ok(AlunoMapper.toDto(aluno));
    }
    @Operation(summary = "Alterar o status do aluno", description = "Recurso para alterar o status do aluno",
            responses = {
                    @ApiResponse(responseCode = "204",description = "Aluno alterado com sucesso", content = @Content(mediaType =
                            "application/json", schema =@Schema(implementation = AlunoResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Id do curso não encontrado",content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/{id}/status")
    public ResponseEntity<AlunoResponseDto> alterarStatusAluno(@PathVariable Long id, @RequestBody AlunoResponseDto alunoResponseDto) {
        Aluno aluno = alunoService.alterarStatusAluno(id, alunoResponseDto.getStatus());
        return ResponseEntity.ok(AlunoMapper.toDto(aluno));
    }
}