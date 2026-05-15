package org.serratec.Aula06.controller;

import java.util.List;

import org.serratec.Aula06.domain.Editora;
import org.serratec.Aula06.domain.Livro;
import org.serratec.Aula06.repository.EditoraRepository;
import org.serratec.Aula06.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {
     
    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private LivroRepository livroRepository;
      
    @GetMapping()
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }
    
    
    @GetMapping("/{id}")
    public Livro obterLivro(@PathVariable Long id) {
        return livroRepository.findById(id).orElse(null);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro inserir(@Valid @RequestBody Livro livro) {

    Long id = livro.getEditora().getId();

    Editora editora = editoraRepository.findById(id).orElse(null);

    livro.setEditora(editora);

    return livroRepository.save(livro);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        livroRepository.deleteById(id);
    }
}

