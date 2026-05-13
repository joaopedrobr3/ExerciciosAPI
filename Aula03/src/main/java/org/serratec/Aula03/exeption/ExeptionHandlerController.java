package org.serratec.Aula03.exeption;


 import java.lang.module.ResolutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

 @ControllerAdvice
 public class ExeptionHandlerController extends ResolutionException {

         @ExceptionHandler(ResolutionException.class)
         public ResponseEntity<String> handleResolutionException(ResolutionException ex) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        
        


 }
