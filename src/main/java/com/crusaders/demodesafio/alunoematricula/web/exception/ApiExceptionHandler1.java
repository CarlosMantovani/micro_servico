package com.crusaders.web.exception;

import com.crusaders.demodesafioalunoematricula.exception.*;
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

    @ExceptionHandler(IdAlunoNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> IdAlunoNaoEncontrado(RuntimeException ex,
                                                                  HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }


    @ExceptionHandler(MatriculaIdNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> MatriculaIdNaoEncontradoException(RuntimeException ex,
                                                                HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }
    @ExceptionHandler(MatriculaIdDuplicadoException.class)
    public ResponseEntity<ErrorMessage> MatriculaIdDuplicadoException(RuntimeException ex,
                                                                  HttpServletRequest request
    ) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }
    @ExceptionHandler(NumeroMaximoException.class)
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


