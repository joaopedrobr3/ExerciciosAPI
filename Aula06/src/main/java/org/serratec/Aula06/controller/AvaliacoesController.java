package org.serratec.Aula06.controller;

import java.util.List;

import org.serratec.Aula06.domain.Avaliacoes;
import org.serratec.Aula06.repository.AvaliacoesRepository;
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
@RequestMapping("/avaliacoes")
public class AvaliacoesController {
    
    @Autowired
    private AvaliacoesRepository avaliacoesRepository;


    @GetMapping()
    public List<Avaliacoes> listarAvaliacoes() {
        return avaliacoesRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Avaliacoes obterAvaliacao(@PathVariable Long id) {
        return avaliacoesRepository.findById(id).orElse(null);
    }

    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Avaliacoes inserir(@Valid @RequestBody Avaliacoes avaliacao) {
		return avaliacoesRepository.save(avaliacao);
	}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        avaliacoesRepository.deleteById(id);
    }
}
