package org.serratec.Aula03.exeption;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 
 // Anotação @ControllerAdvice para indicar que esta classe é um manipulador global de exceções para os controladores da aplicação
 @ControllerAdvice
 public class ExeptionHandlerController extends ResponseEntityExceptionHandler {
    
  
  //Sobrescrever o método handleMethodArgumentNotValid para personalizar a resposta de erro quando ocorrer uma validação de argumento inválido,
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
          HttpHeaders headers, HttpStatusCode status, WebRequest request) {
          
            List<String> erros = new ArrayList<>();
            for(FieldError erro: ex.getBindingResult().getFieldErrors()){
                erros.add(erro.getField() + ": " + erro.getDefaultMessage());
            }
        // Criar um objeto de resposta de erro personalizado (ErroResposta) contendo uma mensagem de erro, o status HTTP, 
        // a data e hora do erro e uma lista de mensagens de validação dos campos inválidos
            ErroResposta erroResposta = new ErroResposta("Existem campos inválidos", status.value(), 
            LocalDateTime.now(), erros);
            
      // Retornar a resposta de erro personalizada com o status HTTP apropriado
      return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
  }
   
    
  
    
  
    //   Adicionar um método para lidar com a exceção personalizada RecursoNaoEncontradoException,
   //   que é lançada quando um recurso (como um cliente ou pedido) não é encontrado
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    // Criar um método que recebe a exceção e o contexto da requisição, 
    // e retorna uma resposta personalizada de erro (ErroResposta) com uma mensagem de erro, o status HTTP 404 Not Found, a data e hora do erro e a mensagem da exceção
    public ResponseEntity<ErroResposta> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex, WebRequest request) {
        
        // Criar um objeto de resposta de erro personalizado (ErroResposta) contendo uma mensagem de erro, o status HTTP 404 Not Found,
        // a data e hora do erro e a mensagem da exceção
        ErroResposta erroResposta = new ErroResposta( ex.getMessage(), HttpStatus.NOT_FOUND.value(), 
            LocalDateTime.now(), new ArrayList<>());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta);
    }
  }
 

