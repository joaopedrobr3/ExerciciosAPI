package org.serratec.Aula04.domain;

import org.springframework.stereotype.Component;

@Component
public class Exame {
  
    public Double calcularExame(Double valor){
        return valor =  valor * 0.05;
    }
}
