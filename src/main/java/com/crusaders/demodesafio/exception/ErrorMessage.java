package com.crusaders.demodesafio.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorMessage {
    private final String timestamp;
    private final int status;
    private final String error;
    private final String mensagem;
    private final String path;

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String mensagem) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.mensagem = mensagem;
        this.path = request.getRequestURI();
    }
}