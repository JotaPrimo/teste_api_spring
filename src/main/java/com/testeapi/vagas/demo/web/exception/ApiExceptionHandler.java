package com.testeapi.vagas.demo.web.exception;

import com.testeapi.vagas.demo.exceptions.EntityNotFoundException;
import com.testeapi.vagas.demo.exceptions.TodoAlreadyCompletedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                        HttpServletRequest request,
                                                                        BindingResult result) {
        log.error("class ApiExceptionHandler - ", exception);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Erro de cadatro", "Campo(s) inválido(s)", result));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException exception,
                                                                HttpServletRequest request) {
        log.error("class EntityNotFoundException - ", exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, "Recurso não encontrado", exception.getMessage()));
    }

    @ExceptionHandler(TodoAlreadyCompletedException.class)
    public ResponseEntity<ErrorMessage> todoAlreadyCompleted(TodoAlreadyCompletedException exception,
                                                             HttpServletRequest request) {
        log.error("class TodoAlreadyCompletedException - ", exception);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, "Todo já completada", exception.getMessage()));
    }

}
