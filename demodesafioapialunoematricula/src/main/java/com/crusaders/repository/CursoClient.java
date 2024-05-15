package com.crusaders.repository;

import com.crusaders.entities.Aluno;
import com.crusaders.web.dto.AlunoResponseDto;
import com.crusaders.web.dto.CursoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "curso-service", url = "http://localhost:8081/api/v1")
public interface CursoClient {

    @GetMapping("/cursos/{id}")
    CursoRequestDto buscarPorId(@PathVariable("id") Long id);
}
