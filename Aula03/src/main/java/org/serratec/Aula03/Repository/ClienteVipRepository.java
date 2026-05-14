package org.serratec.Aula03.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.serratec.Aula03.Domain.ClienteVip;

@Repository
public interface ClienteVipRepository extends JpaRepository<ClienteVip, Long> {
    List<ClienteVip> findByConsultorResponsavelIgnoreCase(String consultorResponsavel);
}
