package com.crusaders.demodesafio.alunoematricula.web.exception;

import com.crusaders.demodesafio.alunoematricula.exception.AlunoIdDuplicadoException;
import com.crusaders.demodesafio.alunoematricula.exception.IdAlunoNaoEncontrado;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler1 {

    @ExceptionHandler(IdAlunoNaoEncontrado.class)
    public ResponseEntity<ErrorMessage> IdAlunoNaoEncontrado(RuntimeException ex,
                                                                  HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_ACCEPTABLE, ex.getMessage()));
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> EntityNotFoundException(RuntimeException ex,
                                                                HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }
    @ExceptionHandler(AlunoIdDuplicadoException.class)
    public ResponseEntity<ErrorMessage> AlunoIdDuplicadoException(RuntimeException ex,
                                                                  HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }
}


