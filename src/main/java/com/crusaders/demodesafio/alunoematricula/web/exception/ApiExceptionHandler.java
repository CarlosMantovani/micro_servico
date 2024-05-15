package com.crusaders.demodesafio.alunoematricula.web.exception;

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

    @ExceptionHandler(com.crusaders.exception.IdAlunoNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> IdAlunoNaoEncontrado(RuntimeException ex,
                                                                  HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }


    @ExceptionHandler(com.crusaders.exception.MatriculaIdNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> MatriculaIdNaoEncontradoException(RuntimeException ex,
                                                                HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }
    @ExceptionHandler(com.crusaders.exception.MatriculaIdDuplicadoException.class)
    public ResponseEntity<ErrorMessage> MatriculaIdDuplicadoException(RuntimeException ex,
                                                                  HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }
    @ExceptionHandler(com.crusaders.exception.NumeroMaximoException.class)
    public ResponseEntity<ErrorMessage> NumeroMaximoException(RuntimeException ex,
                                                                  HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

}


