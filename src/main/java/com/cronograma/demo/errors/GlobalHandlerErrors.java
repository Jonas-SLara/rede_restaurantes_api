package com.cronograma.demo.errors;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerErrors {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        return ResponseEntity.internalServerError().body(
            Map.of(
                "error", "Erro Interno em uma exceção não tratada",
                "message", ex.getMessage()
            )
        );
    }
}
