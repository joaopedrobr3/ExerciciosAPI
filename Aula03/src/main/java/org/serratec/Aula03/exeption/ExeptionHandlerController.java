package org.serratec.Aula03.exeption;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

 @ControllerAdvice
 public class ExeptionHandlerController extends ResponseEntityExceptionHandler {
    

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
          HttpHeaders headers, HttpStatusCode status, WebRequest request) {
      
            ErroResposta erroResposta = new ErroResposta("Existem campos inválidos", status.value(), 
            LocalDateTime.now(), ex.getBindingResult().getFieldErrors().stream().map(e -> e.getDefaultMessage()).toList());
            
      return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
  }

  @ExceptionHandler(RecursoNaoEncontradoException.class)

    public ResponseEntity<Object> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex, WebRequest request) {
        ErroResposta erroResposta = new ErroResposta("Não existe um cliente com esse ID", HttpStatus.NOT_FOUND.value(), 
            LocalDateTime.now(), List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta);
    }
  }
 

