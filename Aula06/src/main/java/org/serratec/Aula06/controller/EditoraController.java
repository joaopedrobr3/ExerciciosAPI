package org.serratec.Aula06.controller;

import java.util.List;

import org.serratec.Aula06.domain.Editora;
import org.serratec.Aula06.repository.EditoraRepository;
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
@RequestMapping ("/editoras")
public class EditoraController {
   

    @Autowired
    private EditoraRepository editoraRepository;
   

   @GetMapping()
    public List<Editora> listarEditoras() {
        return editoraRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Editora obterEditora(@PathVariable Long id) {
        return editoraRepository.findById(id).orElse(null);
    }

    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Editora inserir(@Valid @RequestBody Editora editora) {
		return editoraRepository.save(editora);
	}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        editoraRepository.deleteById(id);
    }
}
