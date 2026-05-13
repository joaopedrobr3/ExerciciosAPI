package org.serratec.Aula04;

import org.serratec.Aula04.domain.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula04Application implements CommandLineRunner {
    
	@Autowired
	private Pagamento pagamento;
	public static void main(String[] args) {
		SpringApplication.run(Aula04Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Valor total do procedimento: " 
		+ pagamento.calcularProcedimento
		   (100.0, 
			50.0));
	}

}
