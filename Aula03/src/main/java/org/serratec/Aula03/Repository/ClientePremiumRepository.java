package org.serratec.Aula03.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.serratec.Aula03.Domain.ClientePremium;

public interface ClientePremiumRepository extends JpaRepository<ClientePremium, Long> {

    List<ClientePremium> findByLimiteCreditoGreaterThanEqual(Double limiteCredito);

    List<ClientePremium> findByNivelFidelidadeIgnoreCase(String nivelFidelidade);
}

