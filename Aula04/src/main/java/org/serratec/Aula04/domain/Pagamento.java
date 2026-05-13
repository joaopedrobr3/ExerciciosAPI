package org.serratec.Aula04.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Gerenciado pelo Spring, permitindo a injeção de dependências
// Classe de serviço para calcular o valor total do procedimento, utilizando os métodos de Consulta e Exame
@Component
public class Pagamento {
    
    // Injeção de dependência para Consulta e Exame
    @Autowired
    private Consulta consulta;

    @Autowired
    private Exame exame;


    public Double calcularProcedimento(Double valorConsulta, Double valorExame){
        return consulta.calcularConsulta(valorConsulta) 
             + exame.calcularExame(valorExame);
    }
}
