package org.serratec.Aula06.repository;

import org.serratec.Aula06.domain.Avaliacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacoesRepository extends JpaRepository<Avaliacoes, Long> {

}
