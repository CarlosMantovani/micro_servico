package com.crusaders.demodesafio.curso.web.exception;

import com.crusaders.demodesafio.curso.exception.CursoIdDuplicadoException;
import com.crusaders.demodesafio.curso.exception.CursoIdNaoEncontrado;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CursoIdDuplicadoException.class)
    public ResponseEntity<ErrorMessage> CcursoIdDuplicadoException(RuntimeException ex,
                                                                        HttpServletRequest request
                                                                        ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(CursoIdNaoEncontrado.class)
    public ResponseEntity<ErrorMessage> cursoIdNaoEncontrado(RuntimeException ex,
                                                             HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }
}


