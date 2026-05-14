package org.serratec.Aula03.exeption;



public class RecursoNaoEncontradoException extends RuntimeException {
    // Construtor que recebe uma mensagem de erro personalizada 
    // e a passa para a classe pai (RuntimeException)
    public RecursoNaoEncontradoException(String message) {
        super(message);
    }

    

    
   

}
