package org.serratec.aula07.repository;

import org.springframework.stereotype.Repository;
import org.serratec.aula07.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
