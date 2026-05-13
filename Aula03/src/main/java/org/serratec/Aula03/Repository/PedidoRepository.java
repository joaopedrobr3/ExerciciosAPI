package org.serratec.Aula03.Repository;

import org.serratec.Aula03.Domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Anotação @Repository para indicar que esta interface é um repositório do Spring Data JPA, 
// responsável por fornecer métodos de acesso a dados para a entidade Pedido
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
