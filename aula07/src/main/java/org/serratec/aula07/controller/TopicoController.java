package org.serratec.aula07.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.serratec.aula07.domain.Curso;
import org.serratec.aula07.domain.Topico;
import org.serratec.aula07.repository.CursoRepository;
import org.serratec.aula07.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/cursos")
public class TopicoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping("/{idCurso}/topicos")
    public List<Topico> listar(@PathVariable Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        return curso.getTopicos(); 
    }
  

    @PostMapping("/{idCurso}/topicos")
    @ResponseStatus(HttpStatus.CREATED)
    public Topico inserir(@PathVariable Long idCurso,
                          @RequestBody Topico topico) {

        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }
}