package org.serratec.Aula04.domain;

import org.springframework.stereotype.Component;

@Component
public class Consulta {
   
    public Double calcularConsulta(Double valor){
        return valor = valor + valor * 0.1;
    }
}
