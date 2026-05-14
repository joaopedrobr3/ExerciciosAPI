package org.serratec.Aula03.Controller;

import java.util.List;
import java.util.Optional;

import org.serratec.Aula03.Domain.ClienteVip;
import org.serratec.Aula03.Repository.ClienteVipRepository;
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
@RequestMapping("/cliente-vip")
public class ClienteVipController {

    @Autowired
    private ClienteVipRepository clienteVipRepository;

    @GetMapping
    public List<ClienteVip> listar() {
        return clienteVipRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteVip> buscarPorId(@PathVariable Long id) {
        Optional<ClienteVip> clienteVip = clienteVipRepository.findById(id);
        return clienteVip.map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente VIP não encontrado com o ID: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteVip inserir(@Valid @RequestBody ClienteVip clienteVip) {
        return clienteVipRepository.save(clienteVip);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteVip> atualizar(@Valid @RequestBody ClienteVip clienteVip,
            @PathVariable Long id) {
        if (!clienteVipRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteVip.setId(id);
        clienteVip = clienteVipRepository.save(clienteVip);
        return ResponseEntity.ok(clienteVip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!clienteVipRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteVipRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consultor/{consultor}")
    public List<ClienteVip> buscarPorConsultor(@PathVariable String consultor) {
        return clienteVipRepository.findByConsultorResponsavelIgnoreCase(consultor);
    }
}
