package org.serratec.Aula03.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.serratec.Aula03.Domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}