package org.serratec.Aula03.Controller;

import java.util.List;
import java.util.Optional;

import org.serratec.Aula03.Domain.Pedido;
import org.serratec.Aula03.Repository.PedidoRepository;
import org.serratec.Aula03.exeption.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
     
	@Autowired
     private PedidoRepository pedidoRepository;

     @GetMapping
     public List<Pedido> listar(){
          return pedidoRepository.findAll();
     }

     @GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
		Pedido pedido = pedidoRepository.findById(id).
		orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado com id: " + id));
		
			return ResponseEntity.ok(pedido);
	}

     @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido inserir(@Valid @RequestBody Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

     @PutMapping("/{id}")
	public ResponseEntity<Pedido> atualizar(@Valid @RequestBody Pedido pedido,
			@PathVariable Long id) {
		
		if(!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		pedido.setId(id);
		pedido = pedidoRepository.save(pedido);
		return ResponseEntity.ok(pedido);
	}

     @DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		pedidoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
    
	@GetMapping("/buscar")
	public ResponseEntity<Pedido> buscarPorNome(@RequestParam String descricao) {
		Optional<Pedido> pedido = pedidoRepository.findAll()
		        .stream()
				.filter(c -> c.getDescricao().equalsIgnoreCase(descricao))
				.findFirst();
		
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
	}


     
}

