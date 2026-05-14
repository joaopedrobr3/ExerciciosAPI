package org.serratec.Aula03.Controller;

import java.util.List;
import java.util.Optional;

import org.serratec.Aula03.Domain.ClientePremium;
import org.serratec.Aula03.Repository.ClientePremiumRepository;
import org.serratec.Aula03.exeption.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente-premium")
public class ClientePremiumController {

    @Autowired
    private ClientePremiumRepository clientePremiumRepository;

    @GetMapping
    public List<ClientePremium> listar() {
        return clientePremiumRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientePremium> buscarPorId(@PathVariable Long id) {
        Optional<ClientePremium> clientePremium = clientePremiumRepository.findById(id);
        return clientePremium.map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente Premium não encontrado com o ID: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientePremium inserir(@Valid @RequestBody ClientePremium clientePremium) {
        return clientePremiumRepository.save(clientePremium);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientePremium> atualizar(@Valid @RequestBody ClientePremium clientePremium,
            @PathVariable Long id) {
        if (!clientePremiumRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clientePremium.setId(id);
        clientePremium = clientePremiumRepository.save(clientePremium);
        return ResponseEntity.ok(clientePremium);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!clientePremiumRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clientePremiumRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/limite-credito/{limite}")
    public List<ClientePremium> buscarPorLimiteCredito(@PathVariable Double limite) {
        return clientePremiumRepository.findByLimiteCreditoGreaterThanEqual(limite);
    }

    @GetMapping("/nivel-fidelidade/{nivel}")
    public List<ClientePremium> buscarPorNivelFidelidade(@PathVariable String nivel) {
        return clientePremiumRepository.findByNivelFidelidadeIgnoreCase(nivel);
    }
}

