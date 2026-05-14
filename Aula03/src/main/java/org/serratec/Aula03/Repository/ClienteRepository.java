package org.serratec.Aula03.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.serratec.Aula03.Domain.Cliente;
import org.serratec.Aula03.enumerated.StatusCliente;
import org.serratec.Aula03.enumerated.TipoCliente;

// Anotação @Repository para indicar que esta interface é um repositório do Spring Data JPA, 
// responsável por fornecer métodos de acesso a dados para a entidade Cliente
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
   List<Cliente> findByStatus(StatusCliente status);
   List<Cliente> findByTipo(TipoCliente tipo);
}

